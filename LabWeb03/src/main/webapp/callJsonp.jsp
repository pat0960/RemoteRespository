<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
var request = null;
function doClick() {
	request = new XMLHttpRequest();
	request.onreadystatechange = doReadyStateChange;
	request.open("GET",
			"http://localhost:7080/JsonpWeb/demo/jsonp.view?data="+Math.floor(Math.random()*100));
	request.send(null);
}
function doReadyStateChange() {
	if(request.readyState==4) {
		if(request.status==200) {
			var element = document.getElementById("data");
			element.innerHTML = request.responseText;
		} else {
			console.log("Error Code:"+request.status+", "+request.statusText);
		}
	}
}
</script>
</head>
<body>
<input type="button" value="Call JsonP" onclick="doClick()">
<div id="data"></div>
</body>
</html>