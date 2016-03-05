package com.example.sspuregister;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class uploadingActivity extends Activity{
	private ListView ListViewuploadmajor;
	private TextView uploadstu;
	private ToDoDB myToDoDB;
	private Cursor myCursor;
	public static int count=0;
	public static String logintime;
	public static String time;
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.uploading);
		ListViewuploadmajor=(ListView)findViewById(R.id.listViewuploadmajor);
		uploadstu=(TextView)findViewById(R.id.uploadstu);
		myToDoDB = new ToDoDB(this);
		myCursor=myToDoDB.selectuploadstu();
		count=myCursor.getCount();
		uploadstu.setText("有"+Integer.toString(count)+"条记录未同步，点击立即同步");
		
		myCursor=myToDoDB.selectlogtime(Register.majorname);
		
		SimpleCursorAdapter adapter = 
	     	    new SimpleCursorAdapter
	     	    (this, R.layout.uploadlist, myCursor, new String[]
	     	        { "logtime"}, new int[]
	     	        { R.id.listTexttime});
		ListViewuploadmajor.setAdapter(adapter);
		
		ListViewuploadmajor.setOnItemClickListener
	    (new AdapterView.OnItemClickListener()
	    {

	      public void onItemClick
	      (AdapterView<?> arg0, View arg1, int arg2, long arg3)
	      {

	    	  myCursor.moveToPosition(arg2);
	    	  arg1.setBackgroundColor(3);
	    	  time=(myCursor.getString(1));
	    	  //Toast.makeText(uploadingActivity.this, time, Toast.LENGTH_LONG).show();
	    	  logintime="L"+time;
	    	  Intent intent = new Intent();
	  		  intent.setClass(uploadingActivity.this, uploadmajor.class);
	  		  startActivity(intent);   
	    	  
	  
	      }

	    });
	}
	
	public void uploadstu(View v){
		if(count==0){
			Toast.makeText(uploadingActivity.this, "无更新数据", Toast.LENGTH_LONG).show();
			return;
		}
		Intent intent = new Intent();
		intent.setClass(uploadingActivity.this, uploadstudent.class);
		startActivity(intent);
		
	}
}