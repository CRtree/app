package com.zuoxiao.app.reference;

import java.lang.ref.WeakReference;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2021/5/21 8:53
 */
public class Fruit {
    private WeakReference<Apple> apple;

    public Fruit(Apple apple) {
        this.apple = new WeakReference<>(apple);
    }

    public Apple getApple() {
        return apple.get();
    }
}
