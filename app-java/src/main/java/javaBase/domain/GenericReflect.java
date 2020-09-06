package javaBase.domain;

import java.io.Serializable;

public class GenericReflect<R extends Comparable<? super R>&Serializable>
        extends Pair<R> implements Serializable,GenericReflectFlag<R>  {

    public  <T extends Comparable<? super T>> T get(T t ){
        return  t ;
    }

    public static void main(String[] args) {
        GenericReflect<String> stringGenericReflect = new GenericReflect<>();
        String hhh = stringGenericReflect.get("hhh");
        System.out.println(hhh);
    }
}
