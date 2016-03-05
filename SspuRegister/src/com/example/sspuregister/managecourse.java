package com.example.sspuregister;

import java.util.Calendar;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class managecourse extends Activity{
     
	private EditText c_name;
	private EditText c_teachername;
	private EditText c_phone;
	private EditText c_email;
	private EditText c_address;
	private TextView c_stat;
	private Spinner spshowcourse;
	private String stryear;
	private Button btnadd;
	
	private ToDoDB myToDoDB2;
	private Cursor myCursor2;
	private Dialog myDialog;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.course);
	    myToDoDB2 = new ToDoDB(this);
		c_name = (EditText) findViewById(R.id.couname);
		c_teachername = (EditText) findViewById(R.id.couteacher);
		c_phone = (EditText) findViewById(R.id.couphone);
		c_email = (EditText) findViewById(R.id.couemail);
		c_address = (EditText) findViewById(R.id.couadd);
		c_stat = (TextView) findViewById(R.id.coustat);
		spshowcourse=(Spinner)findViewById(R.id.showcourse);
		btnadd=(Button)findViewById(R.id.btnadd);
		
		
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		stryear=Integer.toString(year);
			
		
	}
	
	public void activity_back(View v) {    
      	this.finish();
      }  
      
   public void addclick(View v) { 
	   if(btnadd.getText().toString().equals("���")){
		   addcourse();
	   }else{
		   delcourse();
	   }
	   
    } 
   
   public void changeclick(View v) { 
	   if (c_name.getText().toString().equals("")) {
		   c_stat.setText("�γ����Ʋ���Ϊ��");
			return;
		}
	   
	   myCursor2=myToDoDB2.checkcourse(c_name.getText().toString());
	   int flag=myCursor2.getCount();
	   if(flag==0){
		   c_stat.setText("�ÿγ���������!");
		   return;
	   }
	   
	   myToDoDB2.updatemajor(c_name.getText().toString(),c_teachername.getText().toString(),c_phone.getText().toString(),c_email.getText().toString(),c_address.getText().toString());
	   c_stat.setText(c_name.getText().toString()+"�޸ĳɹ�!");
   }
   
   private void delcourse() {
	   if (c_name.getText().toString().equals("")) {
		   c_stat.setText("�γ����Ʋ���Ϊ��");
			return;
		}
	   
	   myDialog=new Dialog(managecourse.this);
	   myDialog.setTitle("ɾ����ʾ");
	   myDialog.setContentView(R.layout.delete);
	   TextView delconfirm=(TextView)myDialog.findViewById(R.id.delconfirm);
	   Button delBtn0=(Button)myDialog.findViewById(R.id.delBtn0);
	   Button delBtn1=(Button)myDialog.findViewById(R.id.delBtn1);
	   delconfirm.setText("   "+c_name.getText().toString()+"  ɾ�����޷��ָ�\n   ȷ��ɾ����");
	   delBtn0.setOnClickListener(delok);
	   delBtn1.setOnClickListener(delcancel);
	   myDialog.show();
	   
   }
   
   private Button.OnClickListener delok=new Button.OnClickListener(){

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		myToDoDB2.delete(c_name.getText().toString());
		myToDoDB2.deletelog(c_name.getText().toString());
		myToDoDB2.deletetable(c_name.getText().toString());
		c_stat.setText(c_name.getText().toString()+"��ɾ��");
		c_name.setText("");
		c_teachername.setText("");
		c_phone.setText("");
		c_email.setText("");
		c_address.setText("");
		showcourselist(v);
		myDialog.cancel();
		
		
	}
	   
   };
   
   private Button.OnClickListener delcancel=new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			c_stat.setText("ɾ��������ȡ��");
			myDialog.cancel();
		}
		   
	   };
   
   private void addcourse() {
	   
	   if (c_name.getText().toString().equals("")) {
		   c_stat.setText("�γ����Ʋ���Ϊ��");
			return;
		}
	   if (c_teachername.getText().toString().equals("")) {
		   c_stat.setText("�ο���ʦ����Ϊ��");
			return;
		}
	   if (c_phone.getText().toString().equals("")) {
		   c_stat.setText("�绰���벻��Ϊ��");
			return;
		}
	   if (c_email.getText().toString().equals("")) {
		   c_stat.setText("�������䲻��Ϊ��");
			return;
		}
	   if (c_address.getText().toString().equals("")) {
		   c_stat.setText("�칫��ַ����Ϊ��");
			return;
		}

		/* ������ݵ����ݿ� */
		String iname = c_name.getText().toString()+stryear+"��";
		String iteacher = c_teachername.getText().toString();
		String iphone = c_phone.getText().toString();
		String iemail = c_email.getText().toString();
		String iadd = c_address.getText().toString();
		
		Pattern pattern = Pattern.compile("[0-9]*"); 		
		int i = iname.length();
		if (Character.isDigit(iname.charAt(0))){
			    c_stat.setText("�γ������������ֿ�ͷ!");
			    return ; 
			} 
		
		myCursor2 = myToDoDB2.checkcourse(iname);
		int count=myCursor2.getCount();
		if(count>0){
			c_stat.setText(iname + "�Ѵ���,���ʧ��!");
			return;
			
		}
		
	
		myToDoDB2.insertmajor(iname, iteacher, iphone, iemail,iadd);
		myToDoDB2.createTable(iname);
		
		c_stat.setText(c_name.getText().toString() + "��ӳɹ�!");	
		c_name.setText("");
		c_teachername.setText("");
		c_phone.setText("");
		c_email.setText("");
		c_address.setText("");
   
   }
   
   public void showcourselist(View v) {
	   btnadd.setText("ɾ��");
//	   myToDoDB2 = new ToDoDB(this);
	    /* ȡ��DataBase������� */
       myCursor2 = myToDoDB2.selectcourse();
       if(myCursor2.getCount()==0){
       	Toast.makeText(managecourse.this, "��û���κογ�,������ӿγ�!", Toast.LENGTH_LONG).show();
			return;
       }
       
		SimpleCursorAdapter adapter = 
	     	    new SimpleCursorAdapter
	     	    (this, R.layout.courselist, myCursor2, new String[]
	     	        { "name"}, new int[]
	     	        { R.id.courselist});
		spshowcourse.setAdapter(adapter);
	     	    
		
		spshowcourse.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
           public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
               // TODO Auto-generated method stub
           	SQLiteCursor sc = (SQLiteCursor) arg0.getSelectedItem();

           	c_name.setText(sc.getString(1));
           	c_teachername.setText(sc.getString(2));
       		c_phone.setText(sc.getString(3));
       		c_email.setText(sc.getString(4));
       		c_address.setText(sc.getString(5));
   	      
           }
           public void onNothingSelected(AdapterView<?> arg0) {
               // TODO Auto-generated method stub
              
           }
       });

		

	}
	
}
