package com.zuoxiao.app.pojo;

import com.zuoxiao.app.annotation.Tohaha;
import com.zuoxiao.app.interf.PrintInterface;
import lombok.Data;

import java.io.Serializable;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2020/10/24 14:32
 */
public @Data
class User extends Person implements Serializable, PrintInterface {

    @Tohaha("haha 1")
    private String name;
    @Tohaha("haha 2")
    private Integer age;
    @Tohaha("haha 3")
    private Address address;


    @Override
    public void print() {
        System.out.println("User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}');
    }

}
