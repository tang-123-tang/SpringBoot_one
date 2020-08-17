package com.tjj.javaSpringBootOne.aspect;

import java.lang.annotation.*;

@Target(ElementType.METHOD)//设定目标
@Documented//文档型
@Retention(RetentionPolicy.RUNTIME)//运行环境
public @interface ServiceAnnotation {
    String value() default  "aaa";
}


