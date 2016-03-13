package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo2 {
public static void main(String[] args){
	Connection conn=null;
	try {
		String url="jdbc:sqlserver://localhost:1433;database=jdbc";
		conn = DriverManager.getConnection(url,"sa","sa123456");
		String qryStmt="SELECT custid,password,email,birth FROM customer";
		PreparedStatement stmt=conn.prepareStatement(qryStmt);
		ResultSet rs=stmt.executeQuery();
		while(rs.next()){
		System.out.println("姓名:"+rs.getString("custid")+", 密碼:"+rs.getString("password")+
				", email:"+rs.getString("email")+", 生日:"+rs.getDate("birth"));
		
		
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
