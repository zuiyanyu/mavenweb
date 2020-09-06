package javaBase.domain;

import java.io.Serializable;

public class GenericReflect<R extends Comparable<? super R>&Serializable>
        extends Pair<R> implements Serializable,GenericReflectFlag<R>  {

    public  <T extends Comparable<? super T>> T get(T t ){
        return  t ;
    }
}
