<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js">"></script>
<script src="animazioni.js"></script>
<title> Home </title>
</head>
<body>
<%
String roles= (String)session.getAttribute("role");
String username= (String)session.getAttribute("username");
%>

<div>
<div>
	<p><%=username %></p>
	<p id="role"><%=roles %></p>
	<form action="/action_page.php" id="carform">
  <label for="fname">Firstname:</label>
  <input type="text" id="fname" name="fname">
  <input type="submit">
  <label for="cars">Scegli cosa:</label>
<select id="cars" name="carlist" form="carform">
  <option value="F">Film del giorno </option>
  <option value="S">Film nelle sale </option>
  <option value="A">Pannello amministratore</option>
</select>
</form>
	<div id="sub-content">
	</div>
	</div>
</div>
<script>
	var r=$("#role").html();
	var s="";
	if(r == "F"){
		s+="<button>film</button>";
	}
	if(r == "S"){
		s+="<button>film</button>";
		s+="<button>sala1</button>";	
	}
	if(r == "A"){
		s+="<button>film</button>";
		s+="<button>sala1</button>";
		s+="<button>admin</button>";
	}
	document.getElementById("sub-content").innerHTML=s;
</script>
</body>
</html>
