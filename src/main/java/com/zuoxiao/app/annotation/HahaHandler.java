package com.zuoxiao.app.annotation;

import com.zuoxiao.app.pojo.User;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/**
 *
 * @author zuoxiao
 * @date 2021/1/9 15:33
 */
@Log4j2
public class HahaHandler {

    public static void execute(Object obj){
        if(obj instanceof User){
            Class clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (field.isAnnotationPresent(Tohaha.class)){
                    Tohaha tohaha1 = field.getAnnotation(Tohaha.class);
                    String value = tohaha1.value();
                    log.info("field:{}, annotation value:{}",field.getName(), value);
                }
            }
        }
    }
}
