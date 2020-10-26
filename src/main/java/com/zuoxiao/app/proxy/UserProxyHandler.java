package com.zuoxiao.app.proxy;

import com.zuoxiao.app.interf.PrintInterface;
import com.zuoxiao.app.pojo.User;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2020/10/26 8:58
 */
public class UserProxyHandler implements InvocationHandler {

    /**要代理的对象，必须实现某个接口，*/
    private PrintInterface user;

    public UserProxyHandler(PrintInterface user){
        this.user = user;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("--------"+method.getName()+"()");
        return method.invoke(user,args);
    }
}
