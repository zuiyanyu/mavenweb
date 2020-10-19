package com.spring_stu.spring_tx;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.Ordered;

public class PoolMonitorAdvice implements Ordered {
    @Override
    public int getOrder() {
        return 0;
    }

    //监听方法调用使用的时间长度 ，如果超过指定时间，可以保存到库中，后续进行调优
    public Object intercept(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();
        String key = Thread.currentThread().getId() +"_"
                +joinPoint.getTarget().getClass().getName()+"."
                +joinPoint.getSignature().getName() ;

        String exceptionKeyPostFix ="" ;
        Object result =null;
        try{
            result = joinPoint.proceed();
        }catch (Throwable throwable){
            exceptionKeyPostFix ="-exception" ;
            throw throwable ;
        }
        finally {
            try{
                long endTime = System.nanoTime();
                long usedTime = (endTime-startTime)/1000000L ;
                String s = key + exceptionKeyPostFix + usedTime;
                System.out.println(s);
            }catch (Throwable throwable){}

        }
        return result ;
    }
}
