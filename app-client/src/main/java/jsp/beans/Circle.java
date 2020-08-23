package jsp.beans;

public class Circle {
    double r;
    final double PI = 3.14 ;
    double area ;
    public Circle(Double r ){
        compluteArea(r);
    }
    public double compluteArea(Double r){
        this.r = r ;
        this.area = PI * r * r ;
        return this.area ;
    }
}
