package javaBase.domain;

import java.io.Serializable;
import java.util.Objects;

public class Employee implements Comparable ,Serializable  {
    static final long serialVersionUID = 42L ;
    private String name ;
    private Integer age ;
    public  String school ;
    public Employee(){
        this.name = "张三";
        this.school ="学校" ;
        this.age = 0 ;

    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school='" + school + '\'' +
                '}';
    }

    public Employee(String name){
        this.name = name;
    }
    public Employee(String name,Integer age){
        this(name);
        this.age = age ;
    }
    public Employee(String name,Integer age,String school){
        this(name,age);
        this.school = school ;
    }

    public static void printBuddies(Pair<? extends  Employee> p)
    {
        Employee first = p.getFirst();
        Employee second = p.getSecond();
        System.out.println(first.getName() + " and " + second.getName() + " are buddies.");
    }

    public static void printBuddies(Pair<? super   Manager> p,Employee employee,Manager manager)
    {
        Object first = p.getFirst();
        p.setFirst(manager);

     }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int staticMethod(int age){
        return  age;
    }
    public static int staticMethod(Integer age){
        return  age;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) &&
                Objects.equals(age, employee.age) &&
                Objects.equals(school, employee.school);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age, school);
    }
}
