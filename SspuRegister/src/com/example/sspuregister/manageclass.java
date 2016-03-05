package com.example.sspuregister;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;


public class manageclass extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.maclass);
		
	}
	
	public void activity_back(View v) {     
      	this.finish();
      }  

	
}
