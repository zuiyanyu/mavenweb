package sparkStreaming.sparkUtil.systemClock

/**
  * An interface to represent clocks, so that they can be mocked out in unit tests.
  */
private[sparkStreaming] trait Clock {
    def getTimeMillis(): Long
    def waitTillTime(targetTime: Long): Long
}
