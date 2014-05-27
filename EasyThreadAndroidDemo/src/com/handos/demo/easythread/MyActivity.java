package com.handos.demo.easythread;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import com.handsoft.easyutil.android.activity.BaseActivity;
import com.handos.easyutil.android.easythread.EasyThreadMethod;


/**
 * 测试安卓程序
 */
public class MyActivity extends BaseActivity {


    TextView textView;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textView=(TextView)findViewById(R.id.hello);


            runInBackground("runCallBack", new Handler.Callback(){

                        @Override
                        public boolean handleMessage(Message message) {
                            textView.setText("good");
                            return false;
                        }
                    }
            );

        Log.d("GOOD","OK,Loading the Activity is Finish!");
    }



    @EasyThreadMethod(methodName = "runCallBack")
    public String changeText(){
        int i=0;
        while(i++<5)
        {
            try {
                Thread.sleep(1000);
                Log.d("GOOD",String.format("%d :Background Thread Say Hello World,Nice!",i));
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
