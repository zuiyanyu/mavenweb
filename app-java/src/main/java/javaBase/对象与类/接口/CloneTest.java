package javaBase.对象与类.接口;

/**
 * 如果希望 copy 是一个新对象，它的初始状态与 original 相同， 但是之后它们各自会有自
 * 己不同的状态， 这种情况下就可以使用 clone 方法。
 */
public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        CloneA cloneA = new CloneA("张三");

        Object clone = cloneA.clone();
        CloneA  newCloneA = (CloneA)clone ;

        cloneA.name="李四" ;
        System.out.println("newCloneA.name="+newCloneA.name);
        System.out.println("cloneA.name="+cloneA.name);

    }

}
class CloneA implements Cloneable {
    public String name ;
    public CloneA(String name){
        this.name = name ;
    }
    protected  Object clone() throws CloneNotSupportedException {
       return super.clone();
    }
}
