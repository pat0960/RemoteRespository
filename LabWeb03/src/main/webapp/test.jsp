<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<scrip type="text/javascript" src="../js/product.jsp">
<script type="text/javascript">
function doClick3(){
	alert("aaaa");
	document.getElementById("img1").style.display="inline";
	setTimeout(function(){
		document.getElementById("img1").style.display="none";
	},10000);
}

function onClick(){

 var data="id=1&name="+encodeURIComponent("xxx");
 var request=new XMLHttpRequest();
/*request.open("get","/LabWeb01/pages/product.controller?"+data,true); 
request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
request.send(null);  */

 request.open("post","/LabWeb01/pages/product.controller",true); 
request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
request.send(data);
}


</script>
</head>
<body>
<div>
<input type="button" value="按按按" onclick="onClick()">
<img src="img/ajax-loader.gif" id="img1" style="display:none">




</div>
<%@ page import="model.*" %>
<%@ page import="java.util.List" %>
<%
CustomerService service1 = new CustomerService();
CustomerBean bean = service1.login("Alex", "A");

ProductService service2 = new ProductService();
List<ProductBean> beans = service2.select(null);
%>
<h3>bean = <%=bean%></h3>
<h3>beans = <%=beans%></h3>

<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.*" %>
<%
Context ctx = new InitialContext();
DataSource dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/xxx");
Connection conn = dataSource.getConnection();
Statement stmt = conn.createStatement();
ResultSet rset = stmt.executeQuery("select * from dept");
while(rset.next()) {
	String col1 = rset.getString(1);
	String col2 = rset.getString(2);
	out.println("<h1>"+col1+","+col2+"</h1>");
}
rset.close();
stmt.close();
conn.close();
%>
</body>
</html>
