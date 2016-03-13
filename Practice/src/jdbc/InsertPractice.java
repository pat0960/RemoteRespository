package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertPractice {
public static void main(String[] args){
	Connection conn = null;
	try {
		String url="jdbc:sqlserver://localhost:1433;database=jdbc";
		conn = DriverManager.getConnection(url,"sa","sa123456");
		String qryStmt="INSERT INTO customer VALUES(?,?,?,?)";
		PreparedStatement pstmt=conn.prepareStatement(qryStmt);
		pstmt.setString(1,"Peter1");
		pstmt.setByte(2, (byte) 0x42);
		pstmt.setString(3, "peter@yahoo.com");
		pstmt.setString(4,"2002-02-02");
		int num=pstmt.executeUpdate();
		System.out.println(num);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally{
	try {
		if (conn !=null) {
			conn.close();
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
	
}
}
