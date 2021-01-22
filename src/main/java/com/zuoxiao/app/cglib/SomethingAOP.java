package com.zuoxiao.app.cglib;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2021/1/22 8:57
 */
@Aspect
@Component
public class SomethingAOP {

    @Pointcut("execution(public * com.zuoxiao.app.cglib.SomethingService.*(..))")
    public void pointcut() {
    }

    @After("pointcut()")
    public void doAfter(){
    }
}
