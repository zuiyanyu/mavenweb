package jsp.beans;

public class Student{
    long classno;
    String name;
    int age;
    Boolean sex;
    public Student(){
        classno=12345;
        name="aaaa";
        age=21;
        sex=true;
    }

    public long getClassno() {
        return classno;
    }

    public void setClassno(long classno) {
        this.classno = classno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }
}
