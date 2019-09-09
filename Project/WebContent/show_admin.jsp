<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.Project.servlet.*,com.Project.beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ page import="java.sql.*"%>
	<% 
	Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	String jdbcUrl="jdbc:mysql://localhost:3306/progetto?serverTimezone=UTC";
	String user="root";
	String pwd="root";
	Connection connection=DriverManager.getConnection(jdbcUrl,user,pwd); // ritorna un oggetto di tipo connection se si connette
	Statement statement=null;
	statement = connection.createStatement();
	String sql="select * from users";
	ResultSet rs = statement.executeQuery(sql);
	%>
	<table>
		<tr>
			<th>Username</th>
			<th>Password</th>
		</tr>
	<%
	while(rs.next())
		{
		  out.println("<tr>");
	      out.println("<td>'" + rs.getString("username") + "'</td>");
	      out.println("<td>" + rs.getString("password") + "</td>");
	      out.println("</tr>");
		}
	%>
	</table>
	<% 
	rs.close();
	statement.close();
	connection.close(); 
	%>
</body>
</html>