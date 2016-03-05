package com.example.sspuregister;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

public class uploadstudent extends Activity{
	public static Activity uploadstudent;
	private Handler mHandler = new Handler();
	private commitToServerDB serverdb;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.uploadstudent);
		
//		serverdb=new commitToServerDB();
//		boolean major=serverdb.checkmajor();
//		if(!major){
//			Toast.makeText(uploadstudent.this, "课程"+Register.majorname+"在服务器上不存在", Toast.LENGTH_SHORT).show();
//			this.finish();
//			return;
//		}
		
		uploadstudent=this;
		final ProgressBar proBar = (ProgressBar)findViewById(R.id.uploadloadprogressBar);
		DoUploadStuWork work = new DoUploadStuWork();
    	work.setHandler(mHandler);
    	work.setProgressBar(proBar);
    	work.start();
    	 	
    	
   }
	
}

