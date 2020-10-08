package javaBase.domain;

import java.io.Serializable;

public class Manager extends Employee implements  Serializable {
    static final long serialVersionUID = 41L ;
    private int level ;
    private Employee secretary ;
    public Manager() {
    }
    public Manager(String name) {
        super(name);
    }
    public Manager(String name,int level) {
        super(name);
        this.level = level ;
    }
    public Employee getSecretary() {
        return secretary;
    }

    public void setSecretary(Employee secretary) {
        this.secretary = secretary;
    }

    public Manager(Integer level) {
        this.level = level ;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "level='" + level + '\'' +
                '}';
    }
}
