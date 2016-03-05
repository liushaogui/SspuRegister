package com.example.sspuregister;

import java.util.Calendar;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class setabs extends Activity {
	//private MyDialog dialog;
	private LinearLayout layout;
	private TextView setabsname;
	private manageabsent mabsent;
	private Register register2;
	private String col;
	
	private Cursor myCursor4;
	private ToDoDB myToDoDB4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setabs);
		//dialog=new MyDialog(this);
		myToDoDB4 = new ToDoDB(this);
		mabsent=new manageabsent();
		register2=new Register();
		
		Calendar calendar = Calendar.getInstance();
		int month=calendar.get(Calendar.MONTH)+1;
		String strmonth=Integer.toString(month);
		int day= calendar.get(Calendar.DAY_OF_MONTH);
		String strday=Integer.toString(day);
		String logtime=strmonth+strday;
		col="L"+logtime;
		
		layout=(LinearLayout)findViewById(R.id.setabs_layout);
		setabsname=(TextView)findViewById(R.id.setabsname);
		setabsname.setText("设置"+mabsent.absname+"为事假还是未带卡?\n请选择");
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
	
	public void setabsbutton1(View v) {  
		myCursor4=myToDoDB4.selectstudent(register2.majorname,mabsent.abskid);
		myCursor4.moveToFirst();
	    String times=myCursor4.getString(5);
	    String losecard=myCursor4.getString(7);
	 
	    String checkrepeat=myCursor4.getString(myCursor4.getColumnIndex(col));
	    if(checkrepeat.equals("1")){
	    	Toast.makeText(setabs.this, mabsent.absname+"已经设置缺卡成功", Toast.LENGTH_SHORT).show();
	    	this.finish();
	    	return;
	    }
	    
	    int itimes=Integer.parseInt(times);
	    int ilosecard=Integer.parseInt(losecard);
	    itimes=itimes+1;
	    ilosecard=ilosecard+1;
	    times=Integer.toString(itimes);
	    losecard=Integer.toString(ilosecard);
	    myToDoDB4.update(register2.majorname,col,mabsent.abskid);
	    
	    myToDoDB4.updatetimesabs(register2.majorname,times,losecard,mabsent.abskid);
	    
	    Toast.makeText(setabs.this, mabsent.absname+"设置缺卡成功", Toast.LENGTH_LONG).show();
    	this.finish();    	
      }  
	public void setabsbutton0(View v) {  
		
		myCursor4=myToDoDB4.selectstudent(register2.majorname,mabsent.abskid);
		myCursor4.moveToFirst();

	    String absence=myCursor4.getString(6);
	    
	    String checkrepeat=myCursor4.getString(myCursor4.getColumnIndex(col));
	    if(checkrepeat.equals("事假")){
	    	Toast.makeText(setabs.this, mabsent.absname+"已经设置事假成功", Toast.LENGTH_SHORT).show();
	    	this.finish();
	    	return;
	    }
	    
	    int iabsence=Integer.parseInt(absence);
	    iabsence=iabsence+1;
	    absence=Integer.toString(iabsence);
	    myToDoDB4.updateabs(register2.majorname,col,absence,mabsent.abskid);
	    Toast.makeText(setabs.this, mabsent.absname+"设置事假成功", Toast.LENGTH_LONG).show();
    	this.finish();
    	
      }  
	
}

