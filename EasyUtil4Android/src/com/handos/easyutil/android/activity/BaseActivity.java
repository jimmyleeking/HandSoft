package com.handos.easyutil.android.activity;
import android.app.Activity;
import android.os.Handler;
import com.handos.easyutil.android.easythread.EasyThread;
import com.handos.easyutil.android.easythread.EasyThreadException;


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
            EasyThread.runInBackground(this, methodName);
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
