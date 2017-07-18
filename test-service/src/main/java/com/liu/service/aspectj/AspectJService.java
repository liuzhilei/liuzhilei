package com.liu.service.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.aop.aspectj.annotation.AspectJAdvisorFactory;
import org.springframework.stereotype.Component;

/**
 * Created by liuzhilei on 2017/7/18.
 * AspectJ 编译时织入
 * jdk的动态代理和cglib都是运行时织入
 */
@Component
@Aspect
public class AspectJService {


    /**
     * 定义切点，此方法没有返回值和参数
     * 该方法就是一个标识，标识需要进行切入的方法，不进行调用
     */
    @Pointcut("execution(* com.liu.service.gameswitch.SwitchService.add*(..))")
    public void aspectMethod() {
    }

    /**
     * 切入之前进行的操作
     *
     * @param joinPoint
     */
    @Before("aspectMethod()")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("beforeAdvice invoke start...");
        System.out.println("执行业务逻辑之前，进行的操作");
        System.out.println("可以通过joinPoint来获取所需要的内容");
        System.out.println("beforeAdvice invoke end");
    }

    /**
     * 业务逻辑退出后的操作，包括正常执行结束和异常退出
     *
     * @param joinPoint
     */
    @After("aspectMethod()")
    public void afterAdvice(JoinPoint joinPoint) {
        System.out.println("afterAdvice invoke start...");
        System.out.println("执行业务逻辑之后，进行的操作");
        System.out.println("可以通过joinPoint来获取所需要的内容");
        System.out.println("afterAdvice invoke end");
    }

    /**
     * 业务逻辑正常退出后的操作
     *
     * @param joinPoint
     * @param returnValue
     */
    @AfterReturning(value = "aspectMethod()", returning = "returnValue")
    public void afterReturnAdvice(JoinPoint joinPoint, String returnValue) {
        System.out.println("业务逻辑正常执行成功以后，不管有没有返回值，都会调用此方法");
        System.out.println("返回值：" + returnValue);
    }

    /**
     * 发生异常的操作
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "aspectMethod()", throwing = "e")
    public void afterThrowAdvice(JoinPoint joinPoint, Exception e) {
        System.out.println("异常信息：" + e);
        System.out.println("发生错误之后进行的操作");
    }


    /*@Around(value = "aspectMethod()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("-----aroundAdvice().invoke-----");
        System.out.println(" 此处可以做类似于Before Advice的事情");

        //调用核心逻辑
        Object retVal = pjp.proceed();
        System.out.println(" 此处可以做类似于After Advice的事情");
        System.out.println("-----End of aroundAdvice()------");
        return retVal;
    }*/


}