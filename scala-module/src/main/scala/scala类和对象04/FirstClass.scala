package scala类和对象04

class FirstClass (userName:String,userAge:Integer ){
    def getUserInfo ={
      "userName = " + userName + " ; userAge = " + userAge
    }
}
object FirstClass{
  def main(args: Array[String]): Unit = {
     val user = new FirstClass("张三",11)
     val userInfo = user.getUserInfo
     println(userInfo);
  }
}
