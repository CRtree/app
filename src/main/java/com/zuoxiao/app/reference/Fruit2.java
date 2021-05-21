package com.zuoxiao.app.reference;

import java.lang.ref.SoftReference;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2021/5/21 8:53
 */
public class Fruit2 {
    private SoftReference<Apple> apple;

    public Fruit2(Apple apple) {
        this.apple = new SoftReference<>(apple);
    }

    public Apple getApple() {
        return apple.get();
    }
}
