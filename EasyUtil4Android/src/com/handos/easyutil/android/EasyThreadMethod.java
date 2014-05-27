package com.handos.easyutil.android;

import java.lang.annotation.*;

/**
 * Created by jimmylee on 5/10/14.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EasyThreadMethod {


    /**
     * 方法名称
     * @return
     */
    String methodName();


}
