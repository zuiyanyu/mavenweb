package scala类和对象04

//TODO 类的主构造体 (userName:String, userAge:Integer )  形参默认为 val类型
class FirstClass_01(userName:String, userAge:Integer ){
    //TODO 类的主体

    //成员属性
    private val className ="FirstClass_01"
    //方法体
    def getUserInfo ={
        s"className=$className ;userName =$userName ; userAge = $userAge "
    }
}
object FirstClass_01{
  def main(args: Array[String]): Unit = {
     val user = new FirstClass_01("张三",11)
     val userInfo = user.getUserInfo
     println(userInfo);
  }
}
