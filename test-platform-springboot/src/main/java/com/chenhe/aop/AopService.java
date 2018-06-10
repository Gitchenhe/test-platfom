package com.chenhe.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenhe
 * @Date 2018-06-04 17:15
 * @desc
 **/
@Configuration
@Aspect
public class AopService {
    Logger logger = LoggerFactory.getLogger(AopService.class);
    private final String logPrefix = "[AOP测试]";

    /**
     * 匹配TestService的所有方法
     */
    @Pointcut("execution(* com.chenhe.aop.TestService.*(..))")
    public void pointCut1(){
        logger.info("{} - pointCut1",logPrefix);
    }

    /**
     * 匹配aop下的包
     */
    @Pointcut("within(com.chenhe.aop.*)")
    public void pointCut2(){
        logger.info("{} - pointCut2",logPrefix);
    }

    @Pointcut("pointCut1() && pointCut2()")
    public void pointCut3(){
        logger.info("{} - pointCut3",logPrefix);
    }

    @Before("pointCut1()")
    public void before(){
        logger.info("{} - before pointCut1",logPrefix);
    }

    @After("pointCut1()")
    public void after(){
        logger.info("{} - after pointCut1",logPrefix);
    }

    @AfterReturning(pointcut = "pointCut1()", returning = "returnValue")
    public void afterReturning(Object returnValue){
        logger.info("{} - after returning",logPrefix);
    }

    @AfterThrowing(pointcut= "pointCut1()",throwing="e")
    public void afterThrowing(RuntimeException e)
    {   System.out.println("AfterThrowing:"+e.getMessage());
        System.out.println("AfterThrowing:"+e);
    }

    @Around("pointCut1()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable
    {
        logger.info("{} - around before ",logPrefix);
        Object obj=pjp.proceed();
        logger.info("{} - around after ",logPrefix);
        return obj;
    }

    @Before("pointCut1() && args(args)")
    public void beforeWithParam(String args){
        logger.info("{} - before param: {}",logPrefix,args);
    }
}
