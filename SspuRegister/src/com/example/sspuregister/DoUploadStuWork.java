package com.example.sspuregister;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import android.database.Cursor;
import android.os.Handler;
import android.widget.ProgressBar;


public class DoUploadStuWork extends Thread {
	private ToDoDB myToDoDB;
	private Cursor myCursor;

	int count=0;
	public static int rowCount=0;
	
	private Handler mHandler;
	private ProgressBar mProBar;
	
	
	public void run () {
		count=0;
		rowCount=0;
		rowCount = 0; 
		
		myToDoDB=new ToDoDB(uploadstudent.uploadstudent);
		
		try {

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// e.printStackTrace();

		}
		try {
			Connection con = DriverManager.getConnection(commitToServerDB.url,commitToServerDB.user, commitToServerDB.password);
			Statement stmt = con.createStatement();
			Statement st=con.createStatement();
			
			myCursor=myToDoDB.selectuploadstu();
			while(myCursor.moveToNext()){
				String kid=myCursor.getString(1);
				String code=myCursor.getString(2);
				String name=myCursor.getString(3);
				String myclass=myCursor.getString(4);
				ResultSet rset = st.executeQuery("select count(*) totalCount from student where kid='"+kid+"'"); 
				if(rset.next()) { 
				   rowCount=rset .getInt("totalCount"); 
				}
				
				if(rowCount==0){
					 String insertInfoStr="INSERT INTO student(kid,code,name,class,flag) VALUES('"+kid+"','"+code+"','"+name+"','"+myclass+"',2)";
			         stmt.executeUpdate(insertInfoStr); 
				}else{
					
				}
				 myToDoDB.updateuploadstu(kid);
				
				mProBar.setMax(uploadingActivity.count);
				count++;
				mHandler.post(new Runnable() {
	                public void run() {
	                	mProBar.setProgress(count);
	                }
	            });	

			}
			
			uploadstudent.uploadstudent.finish();
			

		} catch (SQLException e) {
			e.printStackTrace();
			// Toast.makeText(RegisterActivity.this, "ÍøÂç²»Í¨£¬Çë¼ì²éÍøÂç£¡",
			// Toast.LENGTH_SHORT).show();

		}

	}
	
	void setProgressBar(ProgressBar proBar) {
		mProBar = proBar;
	}
	
	void setHandler(Handler h) {
		mHandler = h;
	}	
	
}

