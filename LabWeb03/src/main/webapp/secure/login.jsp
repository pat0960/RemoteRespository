<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="" />

<title>Login</title>
</head>
<body>

<h3>Login</h3>

<form action="login.controller" method="POST">
<table>
	<tr>
		<td>ID : </td>
		<td><input type="text" name="username" value=""></td>
		<TD align="CENTER" colspan='3'><Font color='red' size="-1">${ErrorMsgKey.AccountEmptyError}&nbsp;</Font></TD> 
		<td></td>
	</tr>
	<tr>
		<td>PWD : </td>
		<td><input type="text" name="password" value=""></td>
		<TD align="CENTER" colspan='3'><Font color='red' size="-1">${ErrorMsgKey.PasswordEmptyError}&nbsp;</Font></TD> 
		<td></td>
	</tr>
	<tr>
		<td>　</td>
		<td align="right"><input type="submit" value="Login"></td>
		 <TD align="CENTER" colspan='3'><Font color='red' size="-1">${ErrorMsgKey.LoginError}&nbsp;</Font></TD> 
	</tr>
</table>
</form>

</body>
</html>