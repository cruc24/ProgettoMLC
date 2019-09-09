<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="com.Project.servlet.*,com.Project.beans.*,java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	String jdbcUrl="jdbc:mysql://localhost:3306/progetto?serverTimezone=UTC";
	String user="root";
	String pwd="root";
	Connection connection=DriverManager.getConnection(jdbcUrl,user,pwd); // ritorna un oggetto di tipo connection se si connette
	Statement statement=null;
	statement = connection.createStatement();
	String sql="select * from films";
	ResultSet rs = statement.executeQuery(sql);%>
	<table id="tabella">
		<tr>
			<th></th>
			<th>Titolo</th>
			<th>Giorno</th>
			<th>Ora_init</th>
			<th>Ora_fin</th>
			<th>Durata</th>
			<th>Sala</th>
			<th> </th>
			<th> </th>
		</tr>
	<%	while(rs.next())
		{
		  out.println("<tr>");
		  out.println("<td>'" + rs.getString("id") + "'</td>");
	      out.println("<td>'" + rs.getString("titolo") + "'</td>");
	      out.println("<td>" + rs.getString("giorno") + "</td>");
	      out.println("<td>" + rs.getString("ora_init") + "</td>");
	      out.println("<td>" + rs.getString("ora_fine") + "</td>");
	      out.println("<td>" + rs.getString("durata") + "</td>");
	      out.println("<td>" + rs.getString("sala") + "</td>");
	      out.println("<td>" + "<button onclick=document.getElementById('alter_form').style.display='block' style='width:auto;'>"+"modifica</button>"+"</td>");
		}%>
	</table>
	<div id="alter_form" style="display:none;">
		<form action="" method="POST" id="modify">
		<h5>Modifica Film </h5>
		Id: <input type="text" name="id" id="id" ><br /><br />
		Titolo: <input type="text" name="titolo" id="titolo" ><br /><br />
		Giorno: <input type="text" name="giorno" id="giorno" ><br /><br />
		Ora Inizio: <input type="text" name="ora_init" id="ora_init" ><br /><br />
		Ora Fine: <input type="text" name="ora_fine" id="ora_fin" ><br /><br />
		Durata: <input type="text" name="durata" id="durata" ><br /><br />
		Genere: <input type="text" name="sala" id="sala" ><br /><br />
		<input type="submit" value="Modifica"><br /> <br />
		<script>
		var table = document.getElementById('tabella');
                for(var i = 1; i < table.rows.length; i++)
                {
                    table.rows[i].onclick = function()
                    {
                         //rIndex = this.rowIndex;
						 document.getElementById("id").value = this.cells[0].innerHTML;
                         document.getElementById("titolo").value = this.cells[1].innerHTML;
                         document.getElementById("giorno").value = this.cells[2].innerHTML;
                         document.getElementById("ora_init").value = this.cells[3].innerHTML;
                         document.getElementById("ora_fin").value = this.cells[4].innerHTML;
                         document.getElementById("durata").value = this.cells[5].innerHTML;
                         document.getElementById("sala").value = this.cells[6].innerHTML;
                    };
                }
        </script>
		</form>
	</div>
  		<button onClick="document.location.href='/Project/home.jsp'" >Indietro</button><br>
</body>
</html>