package com.zuoxiao.app.pojo;

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

    private String name;
    private Integer age;


    @Override
    public void print() {
        System.out.println("User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}');
    }

}
