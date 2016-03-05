package com.example.sspuregister;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import android.database.Cursor;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;


public class DoUploadMajorWork extends Thread {
	private ToDoDB myToDoDB;
	private Cursor myCursor;

	public static int count=0;
	private String time;
	
	private Handler mHandler;
	private ProgressBar mProBar;
	
	
	public void run () {
		count=0;
		int rowCount=0;
		int rowCount1=0;
	
		
		myToDoDB=new ToDoDB(uploadmajor.uploadmajor);
		
		try {

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// e.printStackTrace();

		}
		try {
			Connection con = DriverManager.getConnection(commitToServerDB.url,commitToServerDB.user, commitToServerDB.password);
			Statement stmt = con.createStatement();
			Statement st=con.createStatement();
			
			ResultSet rset = stmt.executeQuery("select count(*) totalCount from login where logtime='"+uploadingActivity.time+"' and major='"+Register.majorname+"'"); 
			
			if(rset.next()) { 
				   rowCount=rset .getInt("totalCount"); 
				}
				
				if(rowCount==0){
					String insertInfoStr="alter table "+Register.majorname+" add "+uploadingActivity.logintime+" nvarchar(10) not null default 0";
					String logintimesql="insert into login(logtime,major) values('"+uploadingActivity.time+"','"+Register.majorname+"')";
					stmt.executeUpdate(logintimesql);
					stmt.executeUpdate(insertInfoStr); 
				}
			
			myCursor=myToDoDB.select(Register.majorname);
			int max=myCursor.getCount();
		
			while(myCursor.moveToNext()){
				String kid=myCursor.getString(1);
				String code=myCursor.getString(2);
				String name=myCursor.getString(3);
				String myclass=myCursor.getString(4);
				String times=myCursor.getString(5);
				time=myCursor.getString(myCursor.getColumnIndex(uploadingActivity.logintime));
				rset = stmt.executeQuery("select count(*) totalCount from "+Register.majorname+" where kid='"+kid+"'"); 
				if(rset.next()) { 
				   rowCount1=rset .getInt("totalCount"); 
				}
				
				if(rowCount1==0){
					 String insertInfoStr="INSERT INTO "+Register.majorname+"(kid,code,name,class,times,flag,"+uploadingActivity.logintime+") VALUES('"+kid+"','"+code+"','"+name+"','"+myclass+"',"+times+",2,'"+time+"')";
			         stmt.executeUpdate(insertInfoStr); 
				}else{
					if(time.equals("1")){
						int itimes=0;
						rset=st.executeQuery("SELECT * FROM "+Register.majorname+" where kid='"+kid+"'");  
						if(rset.next()){
							itimes=rset.getInt("times");
							itimes=itimes+1;
							String insertInfoStr="update "+Register.majorname+" set times="+itimes+","+uploadingActivity.logintime+"='"+time+"' where kid='"+kid+"'";
					        stmt.executeUpdate(insertInfoStr); 
						}
						
						//int itimes=Integer.parseInt(stimes);
						
					}else{
						String insertInfoStr="update "+Register.majorname+" set "+uploadingActivity.logintime+"='"+time+"' where kid='"+kid+"'";
				        stmt.executeUpdate(insertInfoStr); 
					}
					
				}
				
				mProBar.setMax(max);
				count++;
				mHandler.post(new Runnable() {
	                public void run() {
	                	mProBar.setProgress(count);
	                }
	            });	

			}
			myToDoDB.logintime(uploadingActivity.time,Register.majorname);
			uploadmajor.uploadmajor.finish();
			

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


