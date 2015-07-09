package com.example.blingblingtest;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity{
	ImageView image;//�Ϥ�
	Timer timer =new Timer();
    TimerTask task;
    Handler handler; //UI thread
    Handler Boss =null; //�lthread
    HandlerThread Staff = null; 
    LayoutParams para;//����Ϥ���
    DisplayMetrics metrics;//���o�ù��ؤo
    int control = 0;
    int width,heigth,a,b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView)findViewById(R.id.imageView1);
        para = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);//��@����
        metrics = new DisplayMetrics();//��@����
        /* ���o�ù��j�p    */
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;
        heigth = metrics.heightPixels;
        //�ŧiTimer
        task = new TimerTask(){
        	
			@Override
			public void run() {
				// TODO �۰ʲ��ͪ���k Stub
				control = control + 1;
				Message message = new Message();
				message.what =1;
				handler.sendMessage(message);
			}
        	
        };
        handler = new Handler(){
			public void handleMessage(Message msg) {
        		super.handleMessage(msg);
        		switch(msg.what){
        		case 1:
        			if((control%2)==0){
        				
        				a = (int)(Math.random()*heigth);
        				b = (int)(Math.random()*width);
        				para.setMargins(a-200,b,0,0);
        				image.setLayoutParams(para);
        				image.setVisibility(1);
        				
        			}
        			else{
        				image.setVisibility(-1);
        			}
        		}
        	}
        };
        
        Staff = new HandlerThread("kevin");
        Staff.start();
        Boss = new Handler(Staff.getLooper());
        Boss.post(r1);
	}
	Runnable r1 = new Runnable(){

		@Override
		public void run() {
			// TODO �۰ʲ��ͪ���k Stub
			//�]�wTimer(task�����椺�e�A�h�֮ɶ���~�}�l����TimerTask,����1�����@��)
			timer.schedule(task,0,200);
		}
		
	};
	 protected void onDestroy() {
		 // TODO Auto-generated method stub
		 super.onDestroy();
		 timer.cancel();
		   }
}