package com.zuoxiao.app.pojo;

import com.zuoxiao.app.interf.PrintInterface;

import java.io.Serializable;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2020/10/24 14:32
 */
public class User extends Person implements Serializable, PrintInterface {

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public void print() {
        System.out.println("User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}');
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
