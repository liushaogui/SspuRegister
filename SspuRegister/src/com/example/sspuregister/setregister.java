package com.example.sspuregister;

import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class setregister extends Activity implements OnInitListener{
	private Cursor myCursor;
	private ToDoDB myToDoDB;
	private ListView studentListView;
	private Register register1;
	private EditText cardeditText;
	private TextView showsum;
	private String col;
	private String name;
	private String kid;
	private int sum;
	private TextToSpeech tts;
	private String mode;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.register);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); 
		studentListView=(ListView)findViewById(R.id.studentListView);
		myToDoDB = new ToDoDB(this);
		register1 = new Register();
		tts = new TextToSpeech(this, this);
		name=register1.majorname;
		showsum=register1.arrived;
		mode=register1.mode;
		cardeditText=(EditText)findViewById(R.id.cardeditText);  
		 
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
		
		String logtime=strmonth+strday;
		col="L"+logtime;
		
		myCursor=myToDoDB.selectinitstu(name,col);
		sum=myCursor.getCount();
		String strsum=Integer.toString(sum)+"人";
		showsum.setText(strsum);
		cardeditText.setOnKeyListener(onKey);  
		
		SimpleCursorAdapter adapter = 
	     	    new SimpleCursorAdapter
	     	    (this, R.layout.list, myCursor, new String[]
	     	        { "name","code","myclass" }, new int[]
	     	        { R.id.listTextView1,R.id.mytextView1,R.id.myclass});
	     	    studentListView.setAdapter(adapter);
	     	    

		
	}
	
	OnKeyListener onKey=new OnKeyListener() {  
		
		  
		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			// TODO Auto-generated method stub
			if(keyCode == KeyEvent.KEYCODE_ENTER){
				kid=cardeditText.getText().toString();
				if(!kid.equals("")){
					selectstudent();
				}
				cardeditText.setFocusable(true);
				cardeditText.setFocusableInTouchMode(true);
				cardeditText.requestFocus();
				cardeditText.requestFocusFromTouch();
				return true;
			}
			return false;
		}  
		  
		};  
		
		
	private void selectstudent(){
	
		myCursor=myToDoDB.selectstudent(name,kid);
		myCursor.moveToFirst();
		    int flag=myCursor.getCount();
		    if(flag==0){
		    	Toast.makeText(setregister.this, "该生信息未录入，请联系老师", Toast.LENGTH_LONG).show();
		    	if(mode.equals("01")){
		    		speak("该生信息未录入，请联系老师");
		    	}
		    	cardeditText.setText("");
		    	cardeditText.setFocusable(true);
		    	return;
		    }
		    
	    String times=myCursor.getString(5);
	    String stuname=myCursor.getString(3);
	    String checkrepeat=myCursor.getString(myCursor.getColumnIndex(col));
	    if(checkrepeat.equals("1")){
	    	Toast.makeText(setregister.this, stuname+"已签到", Toast.LENGTH_SHORT).show();
	    	
	    	if(mode.equals("01")){
	    		speak(stuname+"已签到");
	    	}
	    	
	    	cardeditText.setText("");
	    	return;
	    }
	    int itimes=Integer.parseInt(times);
	    itimes=itimes+1;
	    times=Integer.toString(itimes);
	    myToDoDB.update(name,col,kid);
	    
	    myToDoDB.updatetimes(name,times,kid);
	    
	    
	    myCursor=myToDoDB.selectinitstu(name,col);
	   
	    
	    SimpleCursorAdapter adapter = 
	     	    new SimpleCursorAdapter
	     	    (this, R.layout.list, myCursor, new String[]
	     	        { "name","code","myclass" }, new int[]
	     	        { R.id.listTextView1,R.id.mytextView1,R.id.myclass});
	     	    studentListView.setAdapter(adapter);
	     	    
	    cardeditText.setText("");
	    if(mode.equals("01")){
	    	 speak(stuname+"签到成功");
    	}
	   
		sum=sum+1;
		String strsum=Integer.toString(sum)+"人";
		showsum.setText(strsum);
		
	}


	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		{
			
			int result = tts.setLanguage(Locale.CHINA);
			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED)
			{
				Toast.makeText(setregister.this, "未安装讯飞语音引擎,无法语音朗读",
						Toast.LENGTH_LONG).show();
			}
		}
		
	}
	
	private void speak(String detail){
		 tts.speak(detail,
					TextToSpeech.QUEUE_FLUSH, null);
		
	}
	
	
}
		  
		
		
	
	


