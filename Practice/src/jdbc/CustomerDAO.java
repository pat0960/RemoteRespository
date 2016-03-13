package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerDAO {
private static final String url="jdbc:sqlserver://localhost:1433;database=jdbc";
private static final String username="sa";
private static final String password1="sa123456";
private static final String SELECT_BY_ID="select * from customer where custid=?";
private static final String UPDATE="UPDATE customer set password=?,email=?,birth=? WHERE custid=?";

public static void main(String[] args) throws ParseException{
	CustomerDAO dao=new CustomerDAO();
	 DateFormat df = DateFormat.getDateInstance();
	 Date date=df.parse("2014/12/12");
	dao.update("A".getBytes(), "Eleea@yahoo.com.tw", date, "Ellen");
	CustomerBean bean=dao.select("Babe");
	System.out.println(bean);
}

public CustomerBean select(String custid){
	Connection conn = null;
	PreparedStatement Pstmt = null;
	ResultSet rs = null;
	CustomerBean result=null;
	try {
		
		conn = DriverManager.getConnection(url,username,password1);
		Pstmt = conn.prepareStatement(SELECT_BY_ID);
		Pstmt.setString(1,custid);
		rs = Pstmt.executeQuery();
		
		if(rs.next()){
			result =new CustomerBean();
			result.setCustid(rs.getString("custid"));
			result.setPassword(rs.getBytes("password"));
			result.setEmail(rs.getString("email"));
			result.setBirth(rs.getDate("birth"));											
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{		
		try {
			if (rs!=null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (Pstmt!=null) {
				Pstmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (conn!=null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
	return result;		
}

public boolean update(byte[] password,String email,java.util.Date birth,String custid){
	Connection conn = null;
	PreparedStatement Pstmt = null;
	try {
		conn = DriverManager.getConnection(url,username,password1);
		Pstmt = conn.prepareStatement(UPDATE);
		Pstmt.setBytes(1, password);
		Pstmt.setString(2, email);
		if(birth!=null){
			long time=birth.getTime();
			Pstmt.setDate(3, new java.sql.Date(time));
		}else{
			Pstmt.setDate(3,null);		
		}
		Pstmt.setString(4, custid);
		int i=Pstmt.executeUpdate();
		if(i==1){
			return true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally{		
		try {
			if (Pstmt !=null) {
				Pstmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (conn!=null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	return false;
	
	
}
	
	
}
