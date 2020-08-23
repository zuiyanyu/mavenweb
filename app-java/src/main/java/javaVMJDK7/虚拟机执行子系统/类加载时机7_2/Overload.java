package javaVMJDK7.虚拟机执行子系统.类加载时机7_2;

import java.io.Serializable;

/**重载方法匹配优先级：
 * 编译器虽然能确定出方法的重载版本，但是在很多情况下这个重载版本并不是“唯一的”，往往只能确定一个“更加合适的”版本。
 * 产生这种模糊结论的主要原因是字面量不需要定义，所以字面量没有显式的静态类型，它的静态类型只能通过语言上的规则去理解和推断。
 */
public class Overload{
        public static void sayHello(Object arg){
            System.out.println("hello Object");
        }
        public static void sayHello(int arg){
            System.out.println("hello int");
        }
        public static void sayHello(long arg){
            System.out.println("hello long");
        }
        public static void sayHello(Character arg){
            System.out.println("hello Character");
        }
        public static void sayHello(char arg){
            System.out.println("hello char");
        }
        public static void sayHello(char ... arg){
            System.out.println("hello char……");
        }
        public static void sayHello(Serializable arg){
            System.out.println("hello Serializable");
        }
        public static void main(String[]args){
            sayHello('a');
        }
}
