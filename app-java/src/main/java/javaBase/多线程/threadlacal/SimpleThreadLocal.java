package javaBase.多线程.threadlacal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SimpleThreadLocal<T> {
    private Map valueMap = Collections.synchronizedMap(new HashMap<>()) ;
    public void set(Object newValue){
        //key = 线程对象 ；value = 本线程的变量副本
        valueMap.put(Thread.currentThread(),newValue);
    }
    public Object get(){
        Thread currentThread =Thread.currentThread() ;
        //返回本线程的变量
        Object obj = valueMap.get(currentThread);
        if(null == obj && !valueMap.containsKey(obj)){
            //如果在 map中不存在，放到Map中保存起来
            obj = initialValue();
            valueMap.put(currentThread,obj);
        }
        return  obj ;
    }

    private Object initialValue() {
        return null;
    }
}














































