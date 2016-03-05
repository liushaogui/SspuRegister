package com.example.sspuregister;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


public class managedownload extends Activity{
	private Handler mHandler = new Handler();
	public static Activity managedownload;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.same);
		
		managedownload=this;
		final ProgressBar proBar = (ProgressBar)findViewById(R.id.sameprogressBar);
		DoDownloadWork work = new DoDownloadWork();
    	work.setHandler(mHandler);
    	work.setProgressBar(proBar);
    	work.start();
    	
    	new Handler().postDelayed(new Runnable(){
    		@Override
    		public void run(){
    			if(DoDownloadWork.rowCount==0){
    				Toast.makeText(getApplicationContext(), "无更新数据", Toast.LENGTH_SHORT).show();
    			}else{
    				Toast.makeText(getApplicationContext(), "新增"+DoDownloadWork.addCount+"记录", Toast.LENGTH_SHORT).show();
    			}
    			
    		}
    	}, 200);
		
	}
	
	public void activity_back(View v) {    
      	this.finish();
      }  

	
}
