package com.quartz.javaTimer.originCode;


import com.quartz.javaTimer.TimeUtil;

/**
     * This "helper class" implements the timer's task execution thread, which
     * waits for tasks on the timer queue, executions them when they fire,
     * reschedules repeating tasks, and removes cancelled tasks and spent
     * non-repeating tasks from the queue.
     */
 public  class TimerThread extends Thread {
        /**
         * This flag is set to false by the reaper to inform us that there
         * are no more live references to our Timer object.  Once this flag
         * is true and there are no more tasks in our queue, there is no
         * work left for us to do, so we terminate gracefully.  Note that
         * this field is protected by queue's monitor!
         */
        boolean newTasksMayBeScheduled = true;

        /**
         * Our Timer's queue.  We store this reference in preference to
         * a reference to the Timer so the reference graph remains acyclic.
         * Otherwise, the Timer would never be garbage-collected and this
         * thread would never go away.
         */
        private TaskQueue queue;

        TimerThread(TaskQueue queue) {
            this.queue = queue;
        }

        public void run() {
            try {
                mainLoop();
            } finally {
                //timer的背景线程被kill掉了，相当于 调用了timer.cancel()方法
                // Someone killed this Thread, behave as if Timer cancelled
                synchronized(queue) {
                    newTasksMayBeScheduled = false;
                    queue.clear();  // Eliminate obsolete references
                }
            }
        }

        /**
         * The main timer loop.  (See class comment.)
         */
        private void mainLoop() {
            //一直循环监听任务对列是否有新的任务
            while (true) {
                try {
                    TimerTask task;
                    boolean taskFired;
                    //进行从任务对列中获取任务进行执行的时候，不允许timer对queue进行操作 。
                    synchronized(queue) {
                        // Wait for queue to become non-empty
                        //等待对列非空-即等待有任务添加到任务对列中
                        while (queue.isEmpty() && newTasksMayBeScheduled) {
                            //在执行同步代码块的过程中,执行了锁所属对象的wait()方法,这个线程会释放锁,进行对象的等待池。
                            //notify唤醒这个线程后，需要重新获取锁才能继续执行下面的步骤。
                            queue.wait();
                        }
                        //如果对列为空，说明 newTasksMayBeScheduled 为false,说明调用了timer.cancel()方法进行任务调度的取消
                        if (queue.isEmpty())
                            break; // Queue is empty and will forever remain; die    //跳出最外层while循环，不再监听，结束调度任务

                        // Queue nonempty; look at first evt and do the right thing
                        long currentTime, executionTime;
                        //获取调度时间点最近的那个任务
                        task = queue.getMin();
                        //锁定任务进行执行，对任务进行状态的变更，但是任务还未执行
                        synchronized(task.lock) {
                            //如果此任务已经被取消了，那么就将此还未执行的任务移除
                            if (task.state == TimerTask.CANCELLED) {
                                queue.removeMin();
                                //重新进行下一次循环，需要重新获取锁
                                continue;  // No action required, poll queue again
                            }
                            currentTime = System.currentTimeMillis();
                            executionTime = task.nextExecutionTime;
                            if (taskFired = (executionTime<=currentTime)) {
                                if (task.period == 0) { // Non-repeating, remove   进行一次任务调度
                                    queue.removeMin();
                                    task.state = TimerTask.EXECUTED;
                                } else { // Repeating task, reschedule   固定延时 或者 固定频率的任务调度
                                    //重新设置任务的下一个调度点，并将任务对列中的任务根据调度点进行重新排序
                                    //可以看出，每次任务执行前，都会重新调整下次的调度时间点
                                    // task.period<0 : 固定延时执行任务   当前时间 + task.period 的绝对值
                                    // task.period>0 : 固定频率执行任务   当前调度时间点 + task.period 的绝对值
                                  long newTime =  task.period<0 ? currentTime   - task.period
                                            : executionTime + task.period ;

                                    System.out.println("task.period："+task.period);
                                    System.out.println("executionTime："+TimeUtil.getDate(executionTime));
                                    System.out.println("currentTime："+TimeUtil.getDate(currentTime));
                                    System.out.println("任务调整后的时间点为："+TimeUtil.getDate(newTime));
                                    queue.rescheduleMin(newTime);
                                }
                            }
                        }

                        //如果任务还未达到点火的条件，就进行等待一段时间
                        if (!taskFired) // Task hasn't yet fired; wait
                        {
                            queue.wait(executionTime - currentTime);
                            System.out.println("睡眠时间："+ (executionTime - currentTime));
                        }

                    }
                    //达到任务执行的条件，释放对列锁，开始执行任务
                    //如果执行任务时间过长，固定频率的任务就会产生时间飘移，即 任务记录的下一时间调度时间点和实际任务执行的时间不一致。
                    // 这里可以看到， 固定频率并非是严格意义上的固定频率，当执行时间超过固定周期的大小，就会产生时间漂移
                    //如果执行时间小于任务固定周期的大小，那么就不会产生时间漂移
                    if (taskFired)  // Task fired; run it, holding no locks
                        task.run();
                } catch(InterruptedException e) {
                }
            }
        }
    }
