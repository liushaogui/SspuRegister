package com.example.sspuregister;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;


public class about extends Activity {
	//private MyDialog dialog;
	private LinearLayout layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		//dialog=new MyDialog(this);
		layout=(LinearLayout)findViewById(R.id.about_layout);
		layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！", 
						Toast.LENGTH_SHORT).show();	
			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event){
		finish();
		return true;
	}
	
	public void aboutbutton1(View v) {  
    	this.finish();    	
      }  
	public void aboutbutton0(View v) {
		Uri uri=Uri.parse("http://weibo.com/sspulinux");
		Intent it=new Intent(Intent.ACTION_VIEW,uri);
		startActivity(it);
    	this.finish();
    	
      }  
	
}
