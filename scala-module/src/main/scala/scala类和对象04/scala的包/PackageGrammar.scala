//1. 和 Java 一样定义包
package scala类和对象04.scala的包


/** TODO 0. 包对象 : 包对象可以包含类、对象和特质trait，但不能包含函数/方法或变量的定义。
  * TODO 1. 可以在同一个.scala文件中，声明多个并列的package
  * TODO 2. 包声明的时候可以进行嵌套
  * TODO 3. 子包可以访问父包中的变量或函数等。
  * TODO 4. 在子包和父包中的类重名时，默认采用就近原则.（在子包和父包中的类重名时，如果希望指定使用某个类，则带上包路径）
  * TODO 5. 父包要访问子包的内容时，需要import对应子包中的类等
  * TODO 6. 包名可以相对路径或者绝对路径：只有当使用的包名冲突的时候，使用绝对路径处理。
  *       //第一种形式
  *       //@BeanProperty var age: Int = _
  *       //第二种形式, 和第一种一样，都是相对路径引入
  *       //@scala.beans.BeanProperty var age: Int = _
  *       //第三种形式, 是绝对路径引入，可以解决包名冲突
  *        //@_root_. scala.beans.BeanProperty var age: Int = _
  *  TODO 7. import 导包可以在任意位置（在需要时候引入包，缩小import包的作用范围，提高效率）
  *  TODO 8. Scala中采用下划线 "_" 来导入包中所有的类(Java中如果想要导入包中所有的类，可以通过通配符*)
  *  TODO 9. 类 选取器 {} :如果不想要某个包中全部的类，而是其中的几个类，可以采用选取器(大括号)
  *  TODO 10.类 重命名 : 如果引入的多个包中含有相同的类，那么可以将类进行重命名进行区分，这个就是重命名
  *  TODO 11.类 隐藏:  如果某个冲突的类根本就不会用到，那么这个类可以直接隐藏掉。
  */

class PackageGrammar {
// TODO
}
  class AB_Class
class C_Class
