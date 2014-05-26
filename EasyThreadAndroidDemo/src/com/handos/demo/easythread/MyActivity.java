package com.handos.demo.easythread;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.handos.annotation.EasyThreadMethod;
import com.handos.exception.EasyThreadException;
import com.handos.util.EasyThread;
import com.handos.util.ICallBack;

/**
 * 测试安卓程序
 */
public class MyActivity extends Activity {


    TextView textView;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textView=(TextView)findViewById(R.id.hello);
        try {
            EasyThread.runInBackground(MyActivity.this, "run");

            EasyThread.runInBackground(MyActivity.this,"runCallBack",myCallBack
            );
        } catch (EasyThreadException e) {
            e.printStackTrace();
        }
        Log.d("GOOD","OK,Loading the Activity is Finish!");
    }

    ICallBack myCallBack=new ICallBack() {
        @Override
        public void result(Object result) {
            if(result!=null)
            {
                Log.d("GOOD",String.format("Call Back,the result is %s",result.toString()));

            }else
            {
                Log.d("GOOD","Call Back ,no result.");
            }
        }
    };

    @EasyThreadMethod(methodName = "runCallBack")
    public String changeText(){
        int i=0;
        while(i++<5)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
        return "Very Good";

    }


    @EasyThreadMethod(methodName = "run")
    public void sayHelloWorld() {
        int i=0;
        while(i++<5)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d("GOOD",String.format("%d :Background Thread Say Hello World,Nice!",i));

        }

    }



}
