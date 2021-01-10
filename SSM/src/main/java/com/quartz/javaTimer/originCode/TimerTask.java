/*
 * Copyright (c) 1999, 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.quartz.javaTimer.originCode;

import com.quartz.javaTimer.TimeUtil;

import java.util.Date;
import java.util.Timer;

/**
 * A task that can be scheduled for one-time or repeated execution by a Timer.
 * 一个要执行的任务，可以被Timer执行一次，或者重复的执行。
 *
 * @author  Josh Bloch
 * @see     Timer
 * @since   1.3
 */

public abstract class TimerTask implements Runnable {
    /**
     * This object is used to control access to the TimerTask internals.
     */
    final Object lock = new Object();

    /**
     * The state of this task, chosen from the constants below.
     * 任务的状态：0-未被调度过；
     */
    int state = VIRGIN;

    /**
     * This task has not yet been scheduled.
     */
    static final int VIRGIN = 0;

    /**
     *
     * This task is scheduled for execution.  If it is a non-repeating task,
     * it has not yet been executed.
     * 任务就绪状态：任务被调度，待执行，但是还未被执行。如果是一个非重复的任务，它尚未被执行
     */
    static final int SCHEDULED   = 1;

    /**
     *
     * This non-repeating task has already executed (or is currently
     * executing) and has not been cancelled.
     * 如果是非重复调度任务：表示任务已经被执行，或者正在被执行
     */
    static final int EXECUTED    = 2;

    /**
     * This task has been cancelled (with a call to TimerTask.cancel).
     * 任务被取消（通过调用TimerTask.cancel()）
     */
    static final int CANCELLED   = 3;

    /**
     * Next execution time for this task in the format returned by
     * System.currentTimeMillis, assuming this task is scheduled for execution.
     * For repeating tasks, this field is updated prior to each task execution.
     *
     * 任务下次的执行时间:System.currentTimeMillis
     * 如果是周期执行的任务，值会被周期性更新：当任务下次执行时候，这个字段值会被优先更新
     */
    long nextExecutionTime;

    /**
     * Period in milliseconds for repeating tasks.  A positive value indicates
     * fixed-rate execution.  A negative value indicates fixed-delay execution.
     * A value of 0 indicates a non-repeating task.
     * 任务重复执行的周期/间隔时间.(毫秒为单位)
     *
     * 值不同，表示任务执行类型不同：
     * 1）一个大于0的值表示固定频率的执行任务；
     * 2）一个小于0表示固定延时执行；
     * 3）0值表示是非重复执行的任务。
     *
     *
     */
    long period = 0;

    /**
     * Creates a new timer task.
     */
    protected TimerTask() {
    }

    /**
     * The action to be performed by this timer task.
     */
    public abstract void run();

    /**
     * Cancels this timer task.  If the task has been scheduled for one-time
     * execution and has not yet run, or has not yet been scheduled, it will
     * never run.  If the task has been scheduled for repeated execution, it
     * will never run again.  (If the task is running when this call occurs,
     * the task will run to completion, but will never run again.)
     *
     * <p>Note that calling this method from within the <tt>run</tt> method of
     * a repeating timer task absolutely guarantees that the timer task will
     * not run again.
     *
     * <p>This method may be called repeatedly; the second and subsequent
     * calls have no effect.
     * 撤销任务；
     * 但是任务已经被执行的任务会继续执行完毕。
     *
     * @return
     *    true : 表示任务处于就绪状态(任务被调度将要执行，但是还未执行)
     *    false: 1.任务已经被调度并执行了；2.或者任务从来未被调度过；3.或者任务已经被撤销了；
     *
     * true if this task is scheduled for one-time execution and has
     *         not yet run, or this task is scheduled for repeated execution.
     *         Returns false if the task was scheduled for one-time execution
     *         and has already run, or if the task was never scheduled, or if
     *         the task was already cancelled.  (Loosely speaking, this method
     *         returns <tt>true</tt> if it prevents one or more scheduled
     *         executions from taking place.)
     */
    public boolean cancel() {
        synchronized(lock) {
            boolean result = (state == SCHEDULED);
            state = CANCELLED;
            return result;
        }
    }

    /**
     * Returns the <i>scheduled</i> execution time of the most recent
     * <i>actual</i> execution of this task.  (If this method is invoked
     * while task execution is in progress, the return value is the scheduled
     * execution time of the ongoing task execution.)
     *
     * <p>This method is typically invoked from within a task's run method, to
     * determine whether the current execution of the task is sufficiently
     * timely to warrant performing the scheduled activity:
     * <pre>{@code
     *   public void run() {
     *       if (System.currentTimeMillis() - scheduledExecutionTime() >=
     *           MAX_TARDINESS)
     *               return;  // Too late; skip this execution.
     *       // Perform the task
     *   }
     * }</pre>
     * This method is typically <i>not</i> used in conjunction with
     * <i>fixed-delay execution</i> repeating tasks, as their scheduled
     * execution times are allowed to drift over time, and so are not terribly
     * significant.
     *
     * @return the time at which the most recent execution of this task was
     *         scheduled to occur, in the format returned by Date.getTime().
     *         The return value is undefined if the task has yet to commence
     *         its first execution.
     * @see Date#getTime()
     */
    public long scheduledExecutionTime() {
        synchronized(lock) {
            System.out.println("period = "+ period);
             System.out.println("nextExecutionTime="+TimeUtil.getDate(nextExecutionTime));
            //period 小于0：固定延迟时间 ；  大于0：固定频率执行
            //nextExecutionTime 存储的是任务下次的运行时间点； 这里是计算任务当前运行的时间点
            //无论是 固定延迟时间 还是 固定频率执行 ， 当前任务执行时间点 = nextExecutionTime - period的绝对值
            //System.out.println("nextExecutionTime="+nextExecutionTime);
            return period < 0 ? nextExecutionTime + period
                    : nextExecutionTime - period;
        }
    }
}
