package com.zuoxiao.app.pojo;

import java.io.Serializable;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2020/10/24 14:32
 */
public class User implements Serializable {

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
}
