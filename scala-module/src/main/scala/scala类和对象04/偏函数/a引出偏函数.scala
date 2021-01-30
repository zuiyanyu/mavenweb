package scala类和对象04.偏函数

/**
  * 偏函数
  * 需求：
  * 给你一个集合val list = List(1, 2, 3, 4, "abc") ，请完成如下要求:
  * 将集合list中的所有数字+1，并返回一个新的集合
  * 要求忽略掉 非数字 的元素，即返回的 新的集合 形式为 (2, 3, 4, 5)
  */
object a引出偏函数 {
    def main(args: Array[String]): Unit = {
        method3
    }

    //TODO 解决方式3  使用偏函数
    /**
      * 在对符合某个条件，而不是所有情况进行逻辑操作时，使用偏函数是一个不错的选择
      *
      * 将包在大括号内的一组case语句封装为函数，我们称之为偏函数，
      * 它只对会作用于指定类型的参数或指定范围值的参数实施计算，超出范围的值会忽略.
      *
      * 偏函数在Scala中是一个特质PartialFunction
      * 1. 使用构建特质的实现类(使用的方式是PartialFunction的匿名子类)
      * 2. PartialFunction 是个特质(看源码)
      * 3. 构建偏函数时，参数形式   [Any, Int]是泛型，第一个表示传入参数类型，第二个  表示返回参数
      * 4. 当使用偏函数时，会遍历集合的所有元素，编译器执行流程时先执行isDefinedAt()如果为true ,就会执行 apply, 构建一个新的Int 对象返回
      * 5. 执行isDefinedAt() 为false 就过滤掉这个元素，即不构建新的Int对象.
      * 6. map函数不支持偏函数，因为map底层的机制就是所有循环遍历，无法过滤处理原来集合的元素
      * 7. collect函数支持偏函数
      */
    def method3(): Unit ={

        val add: PartialFunction[Any, Int] =  new PartialFunction[Any, Int]() {
            override def isDefinedAt(x: Any) = {
                x match {
                    case _: Int => true;
                    case _ => false
                }
            }
            //TODO 这里可以看到，先执行isDefinedAt(x)，结果为true时候，就会调用 apply(x)函数进行计算。
//            def applyOrElse[A1 <: A, B1 >: B](x: A1, default: A1 => B1): B1 =
//                if (isDefinedAt(x)) apply(x) else default(x)

            override def apply(x: Any): Int = {
                val i: Int = x.asInstanceOf[Int]
                i + 1
            }
        }
        val list = List(1, 2, 3, 4, "abc")
        list
                .collect(add)
                .foreach(x=>print(x+"\t"))  // 2	3	4	5

        //简化形式1
        def f2: PartialFunction[Any, Int] = {
            case i: Int => i + 1 // case语句可以自动转换为偏函数
        }
        val list2 = List(1, 2, 3, 4,"ABC")
                .collect(f2)
                .foreach(x=>print(x+"\t"))  // 2	3	4	5

        //简化形式2
        val list3 = List(1, 2, 3, 4,"ABC")
                .collect{ case i: Int => i + 1 }
                .foreach(x=>print(x+"\t"))  // 2	3	4	5

    }

    //TODO 解决方式1  先按类型判断过滤 ==> 转换为int类型 ==> 加1计算
    def method1(): Unit = {
        val list = List(1, 2, 3, 4, "abc")
        list
                .filter(_.isInstanceOf[Int])
                .map(_.asInstanceOf[Int])
                .map(_+1)
                .foreach(x=>print(x+"\t"))  // method02
    }
    //TODO  解决方式2 模式匹配
    def method02(): Unit ={
        val list = List(1, 2, 3, 4, "abc","dsdf")
        list
                .map(_ match {
                    case elem: Int => elem + 1
                    case _ =>
                })
                .filter(x=>{
                    !"()".equals(x.toString)
                })
                .foreach(x=>print(x+"\t"))  // 2	3	4	5


    }


}
