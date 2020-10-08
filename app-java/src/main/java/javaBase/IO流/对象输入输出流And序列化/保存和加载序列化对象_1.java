package javaBase.IO流.对象输入输出流And序列化;

import javaBase.domain.Employee;
import javaBase.domain.Manager;

import java.io.*;

/**
 * TODO 1. 对象序列化机制：它可以将任何对象写出到输出流中，并在之后将其读回。
 * TODO 2. ObjectOutputStream 对象：保存对象数据
 * ObjectOutputStream out = new ObjectOutputStream(new FileOtputStream("employee.dat"));
 * writeObject方法可以直接使用进行保存对象：
 * Employee harry = enw Employee("张三",88,"学校1");
 * Manger boss = new manager("李四",8000,1993,11,12);
 * out.writeObject(harry);
 * out.writeObject(boss);
 *
 * TODO 3. ObjectInputStream对象：读取对象
 * ObjectInputStream in = new ObjectInputSteam(new FileInputStream("employee.dat")) ;
 * readObject 方法以这些对象被写入的顺序获取他们：
 *  Employee e1 = (Employee)in.readObject();
 *  Employee e2 = (Employee)in.readObject();
 * TODO 4. 要被序列化的类必须继承一个接口： Serializable 接口。
 * TODO 5. 对象流类都实现了DataInput/DataOutput接口。
 * TODO 6. 被序列化的每个对象都是用一个序列号保存的。 因为序列化用序列号替代了内存地址，所以它允许将对象从一台机器传送到另一台机器。
 *
 * TODO 7. 某些数据域是不可以被序列化的： transient 关键字可以标记某个数据域不用被序列化。 表示 瞬时域 。
 */
public class 保存和加载序列化对象_1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Employee harry = new Employee("harry") ;
        Manager car1 = new Manager("car1",200) ;
        Manager tony = new Manager("tony",500) ;
        car1.setSecretary(harry);
        tony.setSecretary(harry);

        Employee[] staff = new Employee[3] ;
        staff[0] = car1 ;
        staff[1] = harry ;
        staff[2] = tony ;
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("emp.dat"));){
            out.writeObject(staff);
        }
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("emp.dat"));){
            Object o = in.readObject();
            Employee[] newStaff = (Employee[])o;

            for (Employee employee : newStaff) {
                System.out.println(employee);
            }
        }
    }
}
















