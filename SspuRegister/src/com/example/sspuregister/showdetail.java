package com.example.sspuregister;

import java.text.DecimalFormat;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class showdetail extends Activity {
	private manageontime montime;
	private Cursor myCursor5;
	private Cursor myCursor55;
	private ToDoDB myToDoDB5;
	private Register register5;
	public static String stucode="20133430048";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showdetail);
		TextView showdetailname=(TextView)findViewById(R.id.showdetailname);
		TextView showdetailsum=(TextView)findViewById(R.id.showdetailsum);
		TextView showdetailon=(TextView)findViewById(R.id.showdetailon);
		TextView showdetailday=(TextView)findViewById(R.id.showdetailday);
		TextView showdetailabs=(TextView)findViewById(R.id.showdetailabs);
		TextView showdetaillose=(TextView)findViewById(R.id.showdetaillose);
		TextView showdetaillv=(TextView)findViewById(R.id.showdetaillv);
		
		
		myToDoDB5 = new ToDoDB(this);
		montime=new manageontime();
		register5=new Register();
		//Toast.makeText(showdetail.this, register5.majorname+montime.ontimekid, Toast.LENGTH_LONG).show();
		myCursor5=myToDoDB5.selectstudent(register5.majorname,montime.ontimekid);
		myCursor5.moveToFirst();
		String name=myCursor5.getString(3);
		showdetailname.setText(name+"出勤明细");
		String times=myCursor5.getString(5);
		String absenceday=myCursor5.getString(6);
		String losecard=myCursor5.getString(7);
		showdetailon.setText(times+"次");
		showdetailday.setText(absenceday+"次");
		showdetaillose.setText(losecard+"次");
		int itimes=Integer.parseInt(times);
		
		stucode=myCursor5.getString(2);

		myCursor5=myToDoDB5.selectlogin(register5.majorname);
		int logintimes=myCursor5.getCount();
		showdetailsum.setText(Integer.toString(logintimes)+"次");
		
	
		int abs=logintimes-itimes;
		showdetailabs.setText(abs+"次");
		float lv=(itimes*100)/logintimes;
		String str=lv+"";
		String strlv = String .format("%.0f",lv);
		float a=1/2;
		showdetaillv.setText(strlv+"%");
		
	}
	
	public void showphoto(View v){
		Intent intent = new Intent();
		intent.setClass(showdetail.this, photo.class);
		startActivity(intent);
		
	}

	
}
