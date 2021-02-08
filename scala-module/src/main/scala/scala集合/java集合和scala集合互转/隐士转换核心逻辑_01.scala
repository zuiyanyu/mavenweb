package scala集合.java集合和scala集合互转

/**
  *
  * TODO  java和scala的互转，需要引入JavaConversions 类，类声明 object JavaConversions extends WrapAsScala with WrapAsJava
  * TODO 从类声明可以看出看出，JavaConversions 继承了WrapAsScala ，WrapAsJava 两个包装类(封装了类型隐士转换规则)：
  * TODO WrapAsScala ：将 java集合转换为 scala集合
  * TODO WrapAsJava  ：将scala集合转为java集合
  *
  * 所以我们可以从 WrapAsScala 和 WrapAsJava 两个类中查找我们可以使用的隐士转换功能
  *实例：
  * WrapAsScala 类中定义了如下一个隐士转换方法，表示  可以将 java.util.Set  隐士转换为  mutable.Set
  * implicit def asScalaSet[A](s: ju.Set[A]): mutable.Set[A] = s match {
  * * case MutableSetWrapper(wrapped) => wrapped
  * case _ =>new JSetWrapper(s)
  * }
  *
  * WrapAsJava 类似。
  *
  *
  */
object 隐士转换核心逻辑_01 {
    def main(args: Array[String]): Unit = {
        //TODO
    }
}
