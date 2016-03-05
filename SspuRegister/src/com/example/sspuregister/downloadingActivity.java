package com.example.sspuregister;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

public class downloadingActivity extends Activity{
	public static Activity downloadingActivity;
	private Handler mHandler = new Handler();
	private commitToServerDB serverdb;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.download);
		
		serverdb=new commitToServerDB();
		boolean major=serverdb.checkmajor();
		if(!major){
			Toast.makeText(downloadingActivity.this, "课程"+Register.majorname+"在服务器上不存在", Toast.LENGTH_SHORT).show();
			this.finish();
			return;
		}
		
		downloadingActivity=this;
		final ProgressBar proBar = (ProgressBar)findViewById(R.id.downloadprogressBar);
		DoLengthyWork work = new DoLengthyWork();
    	work.setHandler(mHandler);
    	work.setProgressBar(proBar);
    	work.start();
    	
    	new Handler().postDelayed(new Runnable(){
    		@Override
    		public void run(){
    			if(DoLengthyWork.rowCount==0){
    				Toast.makeText(getApplicationContext(), "无更新数据", Toast.LENGTH_SHORT).show();
    			}else{
    				Toast.makeText(getApplicationContext(), "新增"+DoLengthyWork.rowCount+"条记录", Toast.LENGTH_SHORT).show();
    			}
    			
    		}
    	}, 200);

    	
   }
	
}
