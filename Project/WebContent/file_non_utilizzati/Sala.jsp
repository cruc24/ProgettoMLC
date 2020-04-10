<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="com.Project.servlet.*,com.Project.beans.*,java.util.*,java.io.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="jquery-3.4.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="projectStyle.css">
<title>Insert title here</title>
<%
	String sala= (String) request.getParameter("sala");
%>
<script>
	$(document).ready(function(){
	$.get('Gestione_Sale?sala=<%=sala%>',function(data){
	var s;
	var x= data.films.length;
	console.log(x);
		for(i in data.films)
			{
				s+="<div class='lista' id='"+i+"' style='display:none'>";
				s+="<p>"+data.films[i].titolo+"</p>";
				s+="<img src='./Locandine_film/"+data.films[i].filename+"' width='400' height='600'>";
				s+="<p>"+data.films[i].ora_init+"/"+data.films[i].ora_fine+"</p>";
				s+="</div>";
			}
		$("#gallery").html(s);
		
		setInterval(function(){
			for(i in data.films)
			{
			console.log(i);	
			$("#"+i).css("display","block");
			setTimeout(function(){$("#"+i).css("display","none")},2000);
			clearTimeout();
			}
			},15000);
	});
});
</script>
</head>
<body class="content">
	<div class="header">
		<h1 class="title"> <%= sala %> </h1>
	</div>
	<div id="gallery"></div>
	<button onClick="document.location.href='/Project/home.jsp'" >Indietro</button><br>
</body>
</html>