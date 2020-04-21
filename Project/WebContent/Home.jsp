<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/Project/css/projectStyle.css">
<script src="jquery-3.4.1.min.js"></script>
<script src="/Project/javascript/functions.js" type="text/javascript"></script>
<script src="/Project/javascript/animazioni.js" type="text/javascript"></script>
<script src="/Project/javascript/passive_screen_functions.js" type="text/javascript"></script>
<title> Home </title>
</head>
<body>
<%
String roles= (String)session.getAttribute("role");
String username= (String)session.getAttribute("username");
if(username == null)
	response.sendRedirect("login.jsp");
%>
<div class="page">
	<div class="container" >
		<header class="header">
			<h1 class="title">CHOICE PAGE</h1>
		</header>
		<div class="content" style="background-color:brown;">
			<div class="form-login" style="padding:15px;background-color:white;" >
				<p> Username: <%=username %></p>
				<p id="role"><%=roles %></p>
				<hr/>
				<div id="sub-content"></div>
				<div id="Films" style="display:none;padding:5px;">
				<button onclick= "document.location.href='/Project/Passive.jsp?5'" id="film-btn"> Films </button>
				</div>
				<div id="Sale" style="display:none;padding:5px;">
				<button onclick= "document.location.href='/Project/Passive.jsp?1'" id="Sala1-btn"> Sala 1 </button>
				<button onclick= "document.location.href='/Project/Passive.jsp?2'" id="Sala2-btn"> Sala 2 </button>
				<button onclick= "document.location.href='/Project/Passive.jsp?3'" id="Sala3-btn"> Sala 3 </button>
				<button onclick= "document.location.href='/Project/Passive.jsp?4'" id="Sala4-btn"> Sala 4 </button>
				</div>
				<div id="Admin" style="display:none;padding:5px;">
				<button onclick= "document.location.href='/Project/admin.jsp'" id="Admin-btn"> Admin </button>
				</div>
				<hr/>
				<button id="logout" style="text-align:center;width:100%"> Logout </button>
				</div>
			</div>
		</div>
	</div>
<script>
var r=$("#role").html();
var s="";
var p="";
if( r == "F")
{
	$("#role").html("Authorization-Type:Films");
	$("#Films").show();
}
if( r == "S")
{
	$("#role").html("Authorization-Type:Sale");
	$("#Films").show();
	$("#Sale").show();
}
if( r == "A")
{
	$("#role").html("Authorization-Type:Admin");
	$("#Films").show();
	$("#Sale").show();
	$("#Admin").show();
}



</script>
</body>
</html>
