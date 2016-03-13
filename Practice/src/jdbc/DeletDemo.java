package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletDemo {
public static void main(String[] args){
	Connection conn=null;
	try {
		String url="jdbc:sqlserver://localhost:1433;database=jdbc";
		conn = DriverManager.getConnection(url,"sa","sa123456");
		String qryStmt="DELETE FROM customer WHERE custid=?";
		PreparedStatement pstmt=conn.prepareStatement(qryStmt);
		String custid="P1eter";
		pstmt.setString(1,custid);
		int num=pstmt.executeUpdate();
		System.out.println(num);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	finally{
		
		try {
			if (conn!=null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}

	
	
}
