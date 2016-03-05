package com.example.sspuregister;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class manageontime extends Activity{
	private ListView ontimeListView;
	private Cursor myCursor3;
	private ToDoDB myToDoDB3;
	private Register register3;
	public static String ontimekid;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.ontime);
		ontimeListView=(ListView)findViewById(R.id.ontimeListView);
		myToDoDB3 = new ToDoDB(this);
		register3 = new Register();
		String name=register3.majorname;
		
		myCursor3=myToDoDB3.select(name);
		SimpleCursorAdapter adapter = 
	     	    new SimpleCursorAdapter
	     	    (this, R.layout.ontimelist, myCursor3, new String[]
	     	        { "name","code","myclass","times" }, new int[]
	     	        { R.id.listTextView3,R.id.mytextView3,R.id.myclass3,R.id.ontimenum});
		ontimeListView.setAdapter(adapter);
		
		
		ontimeListView.setOnItemClickListener
	    (new AdapterView.OnItemClickListener()
	    {

	      public void onItemClick
	      (AdapterView<?> arg0, View arg1, int arg2, long arg3)
	      {
	    	 
	    	  String kid=(myCursor3.getString(myCursor3.getColumnIndexOrThrow("kid"))); 

	    	  ontimekid=kid;
	    	  arg1.setBackgroundColor(3);
	    	  Intent intent = new Intent();
	  		  intent.setClass(manageontime.this, showdetail.class);
	  		  startActivity(intent);
	  
	      }

	    });
		
		
	
		
	}
	
	public void activity_back(View v) {    
      	this.finish();
      }  
}
