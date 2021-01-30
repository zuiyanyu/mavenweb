package com;


//访问权限：权利和限制 ：方法的提供者和方法的调用者之间的关系
public class TestProtected01 {
    public static void main(String[] args) {
         User user =  new TestProtected01.User();
        // .称之为从属关系： .不代表 user对象调用了info方法，点仅仅代表方法的归属者为 user对象
        // user.info()代表 TestProtected的main方法调用了user的info方法，所以info方法的调用者是 TestProtected
        user.info();

        //Protected权限的方法：同类，同包，子类 才可以调用
        // clone的提供者是user的父类Object,调用者不是user,调用者是TestProtected，而TestProtected 和Object没关系，所以不能调用user的clone方法
        //user.clone(); //报错
    }
   static class  User {
        public void info(){
            //this代表了方法的归属实例对象
            System.out.println(this.getClass().getName());
        }
    }

}

