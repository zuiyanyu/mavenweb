package javaBase.IO流.实例应用;

import javaBase.domain.Employee;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * TODO 将一个Employee记录数组存储成一个文本文件。其中每一条记录都保存成单独的一行。
 * TODO 然后读取出来形成对象。
 */
public class Scanner_PrintWriter {
    private static final String SPLIT_SEPARATOR= ":" ;
    private static final Charset charset = StandardCharsets.UTF_8;
    /**
     * TODO 从文本中读取写入的对象
     * @throws FileNotFoundException
     */
    @Test
    public void readObjFormFile() throws FileNotFoundException {
        String filePath = "employee.dat" ;
        System.out.println(charset.name()); //UTF-8
        try(Scanner scanner = new Scanner(
                new BufferedInputStream(
                        new FileInputStream(new File(filePath)))
                ,charset.name()))
        {
            Employee[] employees = readData(scanner);
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }
    /**
     * TODO 写对象到文本中
     * 因为我们要写出一个文本到文件中，所以我们使用PrintWriter类。
     * 我们直接写出所有的字段，每一个字段后面跟一个  | ，最后一个字段后面跟着一个 \n .
     *
     */
    @Test
    public void writeObjToFile() throws FileNotFoundException {
        Employee[] staff = new Employee[3] ;
        staff[0] = new Employee("张三",200,"学校1");
        staff[1] = new Employee("李四",230,"学校2");
        staff[2] = new Employee("王五",2600,"学校3");

        String filePath = "employee.dat" ;
        try(PrintWriter writer = new PrintWriter(
                new OutputStreamWriter(
                        new BufferedOutputStream(
                                new FileOutputStream(new File(filePath))),
                        charset)))
        {
            writeData(staff,writer);
        }
        System.out.println("ok");
    }
    private Employee[] readData(Scanner scanner){
        int num = scanner.nextInt();
        scanner.nextLine() ; //消费当前行

        Employee[] employees = new Employee[num];
        for (int i = 0; i < num; i++) {
            employees[i] = readEmployee(scanner);
        }
        return employees;
    }
    public Employee readEmployee(Scanner scanner){

        String line = scanner.nextLine() ;
        String[] split = line.split(SPLIT_SEPARATOR);
        String name = split[0];
        String age =  split[1];
        String school =  split[2];

        return  new Employee(name,Integer.valueOf(age),school) ;
    }

    private  void writeData(Employee[] employees,PrintWriter out){
        out.println(employees.length);
        for (Employee employee : employees) {
            writeEmployee(employee,out);
        }
    }
    public void writeEmployee(Employee e ,PrintWriter out){
        out.println(e.getName()+SPLIT_SEPARATOR+e.getAge()+SPLIT_SEPARATOR+e.getSchool());
    }
}
