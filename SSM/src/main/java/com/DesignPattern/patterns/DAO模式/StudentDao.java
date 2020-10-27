package com.DesignPattern.patterns.DAO模式;

import java.util.List;

//数据访问对象接口
public interface StudentDao {
    public List<Student> getAllStudents();
    public Student getStudent(int rollNo);
    public void updateStudent(Student student);
    public void deleteStudent(Student student);
}