package 累加器

import java.util

import org.apache.spark.util.AccumulatorV2



/**
  * 自定义累加器类型的功能在1.X版本中就已经提供了，但是使用起来比较麻烦，
  * 在2.0版本后，官方提供了一个新的抽象类：AccumulatorV2来提供更加友好的自定义类型累加器的实现方式。
  *
  *
  */
//下面这个累加器可以用于在程序运行过程中收集一些文本类信息，最终以Set[String]的形式返回。1

//输入类型是T,输出类型是java.util.Set[T]
class CustomAccumulator[T]  extends AccumulatorV2[T, java.util.Set[T]] {
    private val _logSet: java.util.Set[T] = new util.HashSet();
    /**
      *返回这个累加器是否是零值：
      *比如： 计数器的累加器 的零值是0 ； 集合的累加器的零值为 Nil
      *
      * 各个executor和driver都执行
      */
    override def isZero: Boolean = _logSet.isEmpty


    /**
      * Creates a new copy of this accumulator.
      * 复制一个新的累加器并返回   各个executor执行
      * dirver端拉取各个executor的累加结果的时候执行，拉取完毕后，driver会将结果进行merge.
      * 为了防止driver修改executor端的数据，所以要复制一份本executor的累加器以及结果给driver端
      */
    override def copy(): AccumulatorV2[T, java.util.Set[T]] = {
        val newAcc = new CustomAccumulator[T]()
        //复制值即可
        this._logSet.synchronized{
            newAcc._logSet.addAll(this._logSet)
        }
        newAcc
    }

    /**
      * Merges another same-type accumulator into this one and update its state, i.e. this should be
      * merge-in-place.
      * 将各个executor的聚合结果进行聚合到driver端   driver执行
      */
    override def merge(other: AccumulatorV2[T, java.util.Set[T]]): Unit ={
        other match {
            case o: CustomAccumulator[T] =>
                this._logSet.synchronized {
                    val otherValue: util.Set[T] = other.value
                    val thisValue: util.Set[T] = this.value
                    thisValue.addAll(otherValue);
                }
            case _ => Unit
        }
    }

    /**
      * 重置累加器， 重置后， isZero()要返回true   各个executor和driver都执行
      */
    override def reset(): Unit =_logSet.clear()

    //添加元素   各个executor执行
    override def add(v: T): Unit = _logSet.add(v)

    //返回累加器的累加结果 各个executor和driver都执行
    override def value: java.util.Set[T] = _logSet
}
