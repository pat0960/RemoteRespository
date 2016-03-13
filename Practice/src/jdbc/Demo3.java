package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Demo3 {
public static void main(String[] args){
	Connection conn = null;
	try {
		String url="jdbc:sqlserver://localhost:1433;database=jdbc";
		conn = DriverManager.getConnection(url,"sa","sa123456");
		String qryStmt="SELECT custid,password,email,birth FROM customer";
		PreparedStatement pstmt=conn.prepareStatement(qryStmt);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			System.out.println("name="+rs.getString("custid")+", password="+   
					rs.getString("password")+", email="+rs.getString("email")+", birth="
					+rs.getString("birth").substring(0, 10));    	   	
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
	
	
}
