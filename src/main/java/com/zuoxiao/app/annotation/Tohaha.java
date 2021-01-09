package com.zuoxiao.app.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2021/1/9 15:30
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Tohaha {
    String value() default "";
}
