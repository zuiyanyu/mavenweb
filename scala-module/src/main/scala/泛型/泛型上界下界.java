package 泛型;

public class 泛型上界下界 {
    public static void main(String[] args) {
        getSon(Parent.class);
        getParent(Son.class);

    }


    //TODO 泛型的下界  只能传递 Son类或其 父类
    public static   void getSon(Class<? super Son> parent ){

    }

    //TODO 泛型的上界  只能传递 Parent类或其 子类
    public static   void getParent(Class<? extends Parent> parent ){

    }

    public static <T> T getObj(Class<T> t ) throws IllegalAccessException, InstantiationException {
        return t.newInstance() ;
    }

}
class Parent{}
class Son extends Parent{

}
