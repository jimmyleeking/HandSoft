package com.handos.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by jimmylee on 5/10/14.
 */
class InnerRunnable implements Runnable{
    private Method method;
    private Object obj;
    private ICallBack callBack;
    InnerRunnable(Object obj,Method method,ICallBack callBack) {
        this.method=method;
        this.obj=obj;
        this.callBack=callBack;
    }

    @Override
    public void run() {
        try {
            Object result=method.invoke(obj);
            if(callBack!=null)
            {
                this.callBack.result(result);
            }
        } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (InvocationTargetException e) {
        e.printStackTrace();
    }
    }
}
