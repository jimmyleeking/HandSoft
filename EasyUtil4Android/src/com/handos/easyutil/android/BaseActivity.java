package com.handos.easyutil.android;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


/**
 * Created by jimmylee on 5/26/14.
 */
public abstract class BaseActivity extends Activity  {

    /**
     * 执行后台线程
     * @param methodName
     */
    protected void runInBackground(String methodName)
    {
        try {
            EasyThread.runInBackground(this,methodName);
        } catch (EasyThreadException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行后台线程，结束之后并回调
     * @param methodName
     * @param callBack
     */
    protected void runInBackground(String methodName,Handler.Callback callBack){
        try {
            Handler handler=new Handler(callBack);
            EasyThread.runInBackground(this,methodName,handler);
        } catch (EasyThreadException e) {
            e.printStackTrace();
        }
    }



}
