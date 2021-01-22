package com.zuoxiao.app.cglib;

import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2021/1/22 8:41
 */
@Service
public class SomethingService {

    public void say(){
        System.out.println("SomethingService.say()");
    }

    public final void talk(){
        System.out.println("SomethingService.talk()");
    }

    public String walk(){
        System.out.println("SomethingService.walk()");
        return "dadadada~";
    }
}
