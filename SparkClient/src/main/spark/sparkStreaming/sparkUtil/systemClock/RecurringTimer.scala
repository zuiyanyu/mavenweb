package sparkStreaming.sparkUtil.systemClock

import org.apache.spark.internal.Logging

/**
  * 批次时间定时器
  * 每次当前批次时间到达后，就重新计算下一个批次定时时间，并且会调用用户自定义的回调函数 callback。
  * @param clock
  * @param period
  * @param callback
  * @param name
  */
private[sparkStreaming]
class RecurringTimer(clock: Clock, period: Long, callback: (Long) => Unit, name: String)
        extends Logging {

    private val thread = new Thread("RecurringTimer - " + name) {
        //设置为守护线程
        setDaemon(true)
        override def run() { loop }
    }

    @volatile private var prevTime = -1L
    @volatile private var nextTime = -1L
    @volatile private var stopped = false

    /**
      * Get the time when this timer will fire if it is started right now.
      * The time will be a multiple of this timer's period and more than
      * current system time.
   在定时时间开始前，要获取定时触发时间。
   开始时间/定时触发时间 必须是 period的倍数，并且开始时间要大于当期的系统时间。
      */
    def getStartTime(): Long = {
        // 类似于： clock.getTimeMillis() + period
        //下面这样做是为了保证 连续的时间是 period 倍数，将连续的时间变成离散的时间。
        (math.floor(clock.getTimeMillis().toDouble / period) + 1).toLong * period
    }

    /**
      * Get the time when the timer will fire if it is restarted right now.
      * This time depends on when the timer was started the first time, and was stopped
      * for whatever reason. The time must be a multiple of this timer's period and
      * more than current time.
      */
    def getRestartTime(originalStartTime: Long): Long = {
        //从原始定时开始时间到 当前时间经过的时间。 originalStartTime 已经是批次时间的整数倍
        val gap = clock.getTimeMillis() - originalStartTime
        //从原始定时开始时间到 当前时间经过的时间，经历了有多少了批次时间。  originalStartTime + 经过的N个批次时间 + 1个批次时间  = 重新开始的第一个批次时间结束点。
        (math.floor(gap.toDouble / period).toLong + 1) * period + originalStartTime
    }

    /**
      * Start at the given start time.
   从给定的 开始时间/定时触发时间 开始定时
      */
    def start(startTime: Long): Long = synchronized {
        //先设置第一次的批次结束时间
        nextTime = startTime
        //开始启动定时器，每次当前批次时间到达后，就重新计算下一个批次定时时间，并且会调用用户自定义的回调函数 callback。
        thread.start()
        logInfo("Started timer for " + name + " at time " + nextTime)
        nextTime
    }

    /**
      * Start at the earliest time it can start based on the period.
      */
    def start(): Long = {
        start(getStartTime())
    }

    /**
      * Stop the timer, and return the last time the callback was made.
      *
      * @param interruptTimer True will interrupt the callback if it is in progress (not guaranteed to
      *                       give correct time in this case). False guarantees that there will be at
      *                       least one callback after `stop` has been called.
      */
    def stop(interruptTimer: Boolean): Long = synchronized {
        if (!stopped) {
            stopped = true
            if (interruptTimer) {
                thread.interrupt()
            }
            thread.join()
            logInfo("Stopped timer for " + name + " after time " + prevTime)
        }
        prevTime
    }

    private def triggerActionForNextInterval(): Unit = {
        //等待到达下次的批次时间
        clock.waitTillTime(nextTime)

        //批次时间到达后，调用用户自定义的回调函数 callback，并传递本次的批次时间。
        callback(nextTime)
        // 记录上次的批次时间
        prevTime = nextTime
        //计算下次的批次时间
        nextTime += period
        logDebug("Callback for " + name + " called at time " + prevTime)
    }

    /**
      * Repeatedly call the callback every interval.
    每次间隔重复调用回调函数
      */
    private def loop() {
        try {
            while (!stopped) {
                triggerActionForNextInterval()
            }
            triggerActionForNextInterval()
        } catch {
            case e: InterruptedException =>
        }
    }
}

private[sparkStreaming]
object RecurringTimer extends Logging {

    def main(args: Array[String]) {
        var lastRecurTime = 0L
        val period = 1000

        def onRecur(time: Long) {
            val currentTime = System.currentTimeMillis()
            logInfo("" + currentTime + ": " + (currentTime - lastRecurTime))
            lastRecurTime = currentTime
        }
        val timer = new  RecurringTimer(new SystemClock(), period, onRecur, "Test")
        timer.start()
        Thread.sleep(30 * 1000)
        timer.stop(true)
    }
}
