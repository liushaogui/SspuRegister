package com.example.sspuregister;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import android.app.Activity;
import android.widget.Toast;

public class commitToServerDB {
	private ToDoDB myToDoDB;

	public static String user = "sa";
	public static String password = "123456";
	public static String url="jdbc:jtds:sqlserver://192.168.1.119:1433/SspuRegister";

	public void commitToServerUserInfo(String uid, String pwd) {
		try {

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// e.printStackTrace();

		}
		try {
			Connection con = DriverManager.getConnection(url,user, password);
			Statement stmt = con.createStatement();
			// ��ѯ�Ƿ��û����Ƿ��Ѿ���ע��
			ResultSet registeredUser = stmt
					.executeQuery("SELECT * FROM major where name='"
							+ Register.majorname + "'");

			while (registeredUser.next()) {
				if (uid.equals(registeredUser.getString("UserID").trim())) {
					// �û����ѱ�ע��
					// Toast.makeText(RegisterActivity.this,
					// "���û����ѱ�ע��,������ѡ���û�����", Toast.LENGTH_SHORT).show();

				}
			}
			String insertInfoStr = "INSERT INTO t_UserInfo(UserID,Password) VALUES('"
					+ uid + "','" + pwd + "')";
			stmt.executeUpdate(insertInfoStr);
			// Toast.makeText(RegisterActivity.this, "�û�ע��ɹ�",
			// Toast.LENGTH_SHORT).show();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// Toast.makeText(RegisterActivity.this, "���粻ͨ���������磡",
			// Toast.LENGTH_SHORT).show();

		}

	}
	
	public boolean checkmajor() {
		try {

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// e.printStackTrace();

		}
		try {
			Connection con = DriverManager.getConnection(url,user, password);
			Statement stmt = con.createStatement();
			// ��ѯ�Ƿ��û����Ƿ��Ѿ���ע��
			ResultSet registeredUser = stmt
					.executeQuery("SELECT * FROM major where name='"
							+ Register.majorname + "'");
			
			System.out.println("1");
			if (registeredUser.next()) {
				stmt.close();
				con.close();
				return true;
			}else{
				stmt.close();
				con.close();
				return false;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			// Toast.makeText(RegisterActivity.this, "���粻ͨ���������磡",
			// Toast.LENGTH_SHORT).show();

		}
		return false;

	}
	
	
}
