package com.handos.easyutil.android.easythread;

import android.os.Handler;
import android.os.Message;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by jimmylee on 5/10/14.
 */
class InnerRunnable implements Runnable{
    private Method method;
    private Object obj;
    private Handler handler;
    InnerRunnable(Object obj,Method method,Handler handler) {
        this.method=method;
        this.obj=obj;
        this.handler=handler;
    }

    @Override
    public void run() {
        try {
            Object result=method.invoke(obj);
            if(handler!=null)
            {
                Message msg=new Message();
                msg.obj=result;
                handler.sendMessage(msg);
            }
        } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (InvocationTargetException e) {
        e.printStackTrace();
    }
    }
}
