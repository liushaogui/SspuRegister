package com.example.sspuregister;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import android.database.Cursor;
import android.os.Handler;
import android.widget.ProgressBar;


public class DoDownloadWork extends Thread {
	private ToDoDB myToDoDB;
	private Cursor myCursor;

	int count=0;
	public static int rowCount=0;
	public static int addCount=0;
	
	private Handler mHandler;
	private ProgressBar mProBar;
	
	
	public void run () {
		count=0;
		rowCount=0;
		addCount=0;
		
		try {

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// e.printStackTrace();

		}
		try {
			Connection con = DriverManager.getConnection(commitToServerDB.url,commitToServerDB.user, commitToServerDB.password);
			Statement stmt = con.createStatement();
			Statement st=con.createStatement();
			// 查询是否用户名是否已经被注册
			ResultSet rset = stmt.executeQuery("select count(*) totalCount from student where flag=0"); 
			rowCount = 0; 
			if(rset.next()) { 
			   rowCount=rset .getInt("totalCount"); 
			}
			
			ResultSet registeredUser = stmt
					.executeQuery("SELECT * FROM student where flag=0" );
			
			myToDoDB = new ToDoDB(managedownload.managedownload);
			String kid;
			String name;
			String code;
			String myclass;
			//Toast.makeText(downloadingActivity.this, Integer.toString(sum), Toast.LENGTH_SHORT).show();
			mProBar.setMax(rowCount);
			
			while (registeredUser.next()) {
				kid=registeredUser.getString("kid");
				name=registeredUser.getString("name");
				code=registeredUser.getString("code");
				myclass=registeredUser.getString("class");
				//mProBar.setProgress(count);
				count++;
				mHandler.post(new Runnable() {
	                public void run() {
	                	mProBar.setProgress(count);
	                }
	            });
				
				myCursor=myToDoDB.selectstudent("student",kid);
				if(!myCursor.moveToNext()){
					myToDoDB.insertstudent("student",kid,code,name,myclass);
					addCount++;
				}
				
				
				String updatesql="update student set flag = 1 where kid = '"+kid+"'";
		        st.executeUpdate(updatesql); 
				
			}
			
			stmt.close();
			con.close();
			
			//Toast.makeText(downloadingActivity.downloadingActivity, "新增"+count+"记录", Toast.LENGTH_SHORT).show();
			managedownload.managedownload.finish();
		} catch (SQLException e) {
			e.printStackTrace();
			// Toast.makeText(RegisterActivity.this, "网络不通，请检查网络！",
			// Toast.LENGTH_SHORT).show();

		}

	}
	
	void setProgressBar(ProgressBar proBar) {
		mProBar = proBar;
	}
	
	void setHandler(Handler h) {
		mHandler = h;
	}
	
	public  void download() {

		try {

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// e.printStackTrace();

		}
		try {
			Connection con = DriverManager.getConnection(commitToServerDB.url,commitToServerDB.user, commitToServerDB.password);
			Statement stmt = con.createStatement();
			// 查询是否用户名是否已经被注册
			ResultSet registeredUser = stmt
					.executeQuery("SELECT * FROM "+Register.majorname+" where flag=0" );
			
			myToDoDB = new ToDoDB(downloadingActivity.downloadingActivity);
			String kid;
			String name;
			String code;
			String myclass;
			//Toast.makeText(downloadingActivity.this, Integer.toString(sum), Toast.LENGTH_SHORT).show();
			while (registeredUser.next()) {
				kid=registeredUser.getString("kid");
				name=registeredUser.getString("name");
				code=registeredUser.getString("code");
				myclass=registeredUser.getString("class");

				myToDoDB.insertstudent(Register.majorname,kid,name,code,myclass);
				
			}
			//sum++;
			stmt.close();
			con.close();
			//Toast.makeText(downloadingActivity.this, "新增"+count+"记录", Toast.LENGTH_SHORT).show();
		} catch (SQLException e) {
			e.printStackTrace();
			// Toast.makeText(RegisterActivity.this, "网络不通，请检查网络！",
			// Toast.LENGTH_SHORT).show();

		}

	}
	
	
	
}


