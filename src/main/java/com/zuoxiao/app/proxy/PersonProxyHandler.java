package com.zuoxiao.app.proxy;

import com.zuoxiao.app.pojo.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2020/10/26 9:27
 */
public class PersonProxyHandler implements InvocationHandler {

    private Person person;

    public PersonProxyHandler(Person person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("**************"+method.getName()+"()");
        return method.invoke(person,args);
    }
}
