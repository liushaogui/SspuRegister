package com.example.sspuregister;

import java.util.Calendar;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class manageabsent extends Activity{
	private ListView stuabsListView;
	private Cursor myCursor2;
	private ToDoDB myToDoDB2;
	private Register register2;
	private String name;
	public static String absname=null;
	public static String abskid=null;



	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.absent);
		stuabsListView=(ListView)findViewById(R.id.stuabsListView);
		myToDoDB2 = new ToDoDB(this);
		register2 = new Register();
		name=register2.majorname;
		Calendar calendar = Calendar.getInstance();
		int month=calendar.get(Calendar.MONTH)+1;
		String strmonth=Integer.toString(month);
		 if(month<10){
	        	strmonth="0"+strmonth;
		}
		int day= calendar.get(Calendar.DAY_OF_MONTH);
		String strday=Integer.toString(day);
		if(day<10){
			strday="0"+strday;
	    }
		//Toast.makeText(manageabsent.this, strday, Toast.LENGTH_LONG).show();
		String logtime=strmonth+strday;
		String col="L"+logtime;
		myCursor2=myToDoDB2.selectabststu(name,col);
		SimpleCursorAdapter adapter = 
	     	    new SimpleCursorAdapter
	     	    (this, R.layout.listabs, myCursor2, new String[]
	     	        { "name","code","myclass" }, new int[]
	     	        { R.id.listTextView2,R.id.mytextView2,R.id.myclass2});
		stuabsListView.setAdapter(adapter);
		
		
		stuabsListView.setOnItemClickListener
	    (new AdapterView.OnItemClickListener()
	    {

	      public void onItemClick
	      (AdapterView<?> arg0, View arg1, int arg2, long arg3)
	      {
 
	    	  myCursor2.moveToPosition(arg2);
	    	  arg1.setBackgroundColor(3);
	    	  String name=(myCursor2.getString(myCursor2.getColumnIndexOrThrow("name")));
	    	  String kid=(myCursor2.getString(myCursor2.getColumnIndexOrThrow("kid")));
	    	  //Toast.makeText(manageabsent.this, aaa, Toast.LENGTH_LONG).show();
	    	  absname=name;
	    	  abskid=kid;
	    	  Intent intent = new Intent();
	  		  intent.setClass(manageabsent.this, setabs.class);
	  		  startActivity(intent);
	  
	      }

	    });
		
		   
		
	}
	
	
	
	public void activity_back(View v) {    
      	this.finish();
      }  
}

