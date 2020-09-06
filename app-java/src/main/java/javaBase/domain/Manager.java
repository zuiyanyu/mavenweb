package javaBase.domain;

public class Manager extends Employee {
    private int level ;

    public Manager() {
    }

    public Manager( Integer level) {
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
