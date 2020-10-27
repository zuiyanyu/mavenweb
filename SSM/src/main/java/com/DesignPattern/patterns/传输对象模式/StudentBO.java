package com.DesignPattern.patterns.传输对象模式;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务对象（Business Object） - 为传输对象填充数据的业务服务。
 * 传输对象（Transfer Object） - 简单的 POJO，只有设置/获取属性的方法。
 * 客户端（Client） - 客户端可以发送请求或者发送传输对象到业务对象。
 */
//业务对象
public class StudentBO {

    //列表是当作一个数据库
    List<StudentVO> students;

    public StudentBO(){
        students = new ArrayList<StudentVO>();
        StudentVO student1 = new StudentVO("Robert",0);
        StudentVO student2 = new StudentVO("John",1);
        students.add(student1);
        students.add(student2);
    }
    public void deleteStudent(StudentVO student) {
        students.remove(student.getRollNo());
        System.out.println("Student: Roll No "
                + student.getRollNo() +", deleted from database");
    }

    //从数据库中检索学生名单
    public List<StudentVO> getAllStudents() {
        return students;
    }

    public StudentVO getStudent(int rollNo) {
        return students.get(rollNo);
    }

    public void updateStudent(StudentVO student) {
        students.get(student.getRollNo()).setName(student.getName());
        System.out.println("Student: Roll No "
                + student.getRollNo() +", updated in the database");
    }
}