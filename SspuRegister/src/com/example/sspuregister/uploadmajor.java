package com.example.sspuregister;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

public class uploadmajor extends Activity{
	public static Activity uploadmajor;
	private Handler mHandler = new Handler();
	private commitToServerDB serverdb;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.uploadstudent);
		
		serverdb=new commitToServerDB();
		boolean major=serverdb.checkmajor();
		if(!major){
			Toast.makeText(uploadmajor.this, "�γ�"+Register.majorname+"�ڷ������ϲ�����", Toast.LENGTH_SHORT).show();
			this.finish();
			return;
		}
		
		uploadmajor=this;
		final ProgressBar proBar = (ProgressBar)findViewById(R.id.uploadloadprogressBar);
		DoUploadMajorWork work = new DoUploadMajorWork();
    	work.setHandler(mHandler);
    	work.setProgressBar(proBar);
    	work.start();
    	
    	new Handler().postDelayed(new Runnable(){
    		@Override
    		public void run(){
    			if(DoUploadMajorWork.count==0){
    				Toast.makeText(getApplicationContext(), "�޸�������", Toast.LENGTH_SHORT).show();
    			}else{
    				Toast.makeText(getApplicationContext(), "����"+DoUploadMajorWork.count+"����¼", Toast.LENGTH_SHORT).show();
    			}
    			
    		}
    	}, 400);
    	
   }
	
}


