package com.zuoxiao.app.cglib;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2021/1/22 16:24
 */
public class SomethingInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("调用前");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println(" 调用后，接口返回：" + result);
        return result;
    }
}
