var request = null;
function sendGetRequest(url, id, action) {
	var queryString = "action="+action+"&id="+id+"&dummy="+new Date().getTime();
	request = new XMLHttpRequest();
	request.onreadystatechange = doReadyStateChange;
	request.open("GET", url+"?"+queryString, true);
	request.send(null);
}
function sendPostRequest(url, id, action) {
	var queryString = "action="+action+"&id="+id;
	request = new XMLHttpRequest();
	request.onreadystatechange = doReadyStateChange;
	request.open("POST", url, true);
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.send(queryString);
}
function processXML(dom) {
	var hasMoreData = dom.getElementsByTagName("hasMoreData")[0].firstChild.nodeValue;

}
function doReadyStateChange() {
	if(request.readyState==4) {
		if(request.status==200) {
			document.getElementsByTagName("img")[0].style.display = "none";
			processText(request.responseText);
			//processXML(request.responseXML);
			//processJSON(request.responseText);
		} else {
			console.log("Error Code:"+request.status+", "+request.statusText);
		}
	}
}

function processJSON(data){
	var json=JSON.parse(data);
	var text=json[0].text;
	var hasMoreData=json[1].hasMoreData;
		
	console.log(text);
	console.log(hasMoreData);
	
}
function processText(data) {
	var show = data;
	var index = data.indexOf(":");
	if(index!=-1) {
		show = data.substring(0, index);
		var temp = data.substring(index+1);
		var array = temp.split(",");		
		document.forms[0].id.value = array[0];
		document.forms[0].name.value = array[1];
		document.forms[0].price.value = array[2];
		document.forms[0].make.value = array[3];
		document.forms[0].expire.value = array[4];
	}
	var showTextNode = document.createTextNode(show);
	var spanElement = document.getElementsByTagName("span")[0];
	spanElement.appendChild(showTextNode, spanElement.firstChild);
}
