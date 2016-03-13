package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

public class Demo {
public static void main(String[] args){
	Connection conn=null;
	try {               
		
		String connUrl="jdbc:sqlserver://localhost:1433;database=jdbc";
		conn = DriverManager.getConnection(connUrl,"sa","sa123456");
		String qryStmt="SELECT custid,email FROM customer";
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery(qryStmt);
		while(rs.next()){
			System.out.println("name="+rs.getString("custid")+", email="+rs.getString("email"));		
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		
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
	
	
}
