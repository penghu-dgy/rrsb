package io.renren.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.mysql.jdbc.PreparedStatement;

public class DBManager {
	

	private static Connection getConn() {
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/renren-security";
	    String username = "root";
	    String password = "123";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	
	
	
	public  int insert(Long empid,String empphone,String emppwd) throws ParseException {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "insert into sys_user (user_id,username,password,mobile,status,create_time) values(?,?,?,?,1,?)";
	    PreparedStatement pstmt;
	    
	    /*java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
	System.out.println(date);*/
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setLong(1, empid);
	        pstmt.setString(2, empphone);
	        pstmt.setString(3, emppwd);
	        pstmt.setString(4, empphone);
	        pstmt.setTimestamp(5, new java.sql.Timestamp(new java.util.Date().getTime()));
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	
	public  int addBoss(Long empid) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "insert into sys_user_role (user_id,role_id) values(?,1)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setLong(1, empid);
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	/*    System.out.println(i);*/
	    return i;
	}
	
	
	
	public static void main(String[] args) throws ParseException {
		System.out.println(getConn());
		
			//new DBManager().insert(6l,"1222","1223");
			//new DBManager().addBoss(5l);

	}
	

}
