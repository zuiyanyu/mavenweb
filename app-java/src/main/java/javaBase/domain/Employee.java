package javaBase.domain;

public class Employee {
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
        System.out.println("========Employee.getAge()");
        return age;
    }

    public void setAge(Integer age) {
        System.out.println("========Employee.setAge()");
        this.age = age;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getName() {
        System.out.println("========Employee.getName()");
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
}
