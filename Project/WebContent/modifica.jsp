<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="com.Project.servlet.*,com.Project.beans.*,java.util.*,java.io.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./projectStyle.css">
<title>Insert title here</title>
</head>
<body>
<div class="header">
	<h1 class="title">MODIFICA FILMS</h1>
	<button class="button_header" onClick="document.location.href='/Project/home.jsp'" >Indietro</button>
</div>
	<% 
	ArrayList<Film> f= new ArrayList<>();
	f=(ArrayList<Film>) request.getAttribute("film");
	Iterator <Film> i= f.iterator();
	String errore=(String)request.getAttribute("errore");
	%>
<div class="content">
	<table class="tabella">
		<tr>
			<th>Titolo</th>
			<th>Giorno</th>
			<th>Ora_init</th>
			<th>Ora_fine</th>
			<th>Sala</th>
		</tr>
	<%	while(i.hasNext())
		{
		  Film film= new Film();
		  film=i.next();
		  out.println("<tr>");
		  out.println("<td style='display:none;'>" + film.getId() + "</td>");
		  out.println("<td>" + film.getTitolo() + "</td>");
	      out.println("<td>" + film.getData() + "</td>");
	      out.println("<td>" + film.getOra_Init() + "</td>");
	      out.println("<td>" + film.getOra_Fine() + "</td>");
	      out.println("<td>" + film.getSala() + "</td>");
	      out.print("<td>" + "<button onclick=document.getElementById('alter_form').style.display='block' style='width:auto;'>"+"modifica</button>"+"</td>");
	      out.println("</tr>");
		}%>
	</table>
</div>
	<%
			if(errore != null)
				out.println("<p style=color:red;>" + errore + "</p>");
	%>
	<div id="alter_form" style="display:none;">
		<form action="http://localhost:8080/Project/Modifica_Film" method="post" >
		<h5>Modifica Film </h5>
		Id: <input type="text" name="id" id="id" readonly ><br /><br />
		Titolo: <input type="text" name="titolo" id="titolo" ><br /><br />
		Giorno: <input type="text" name="giorno" id="giorno" ><br /><br />
		Ora Inizio: <input type="text" name="ora_init" id="ora_init" ><br /><br />
		Ora Fine: <input type="text" name="ora_fine" id="ora_fin" ><br /><br />
		Sala: <br />
				<input type="radio" name="sala" value="Sala_1" id="sala"> Sala 1 <br />
				<input type="radio" name="sala" value="Sala_2" id="sala"> Sala 2 <br />
				<input type="radio" name="sala" value="Sala_3" id="sala"> Sala 3 <br />
				<input type="radio" name="sala" value="Sala_4" id="sala"> Sala 4 <br />
				<input type="submit" value="Modifica"><br /> <br />
		<script>
		var table = document.getElementById('tabella');
                for(var i = 1; i < table.rows.length; i++)
                {
                    table.rows[i].onclick = function()
                    {
						 document.getElementById("id").value = this.cells[0].innerHTML;
                         document.getElementById("titolo").value = this.cells[1].innerHTML;
                         document.getElementById("giorno").value = this.cells[2].innerHTML;
                         document.getElementById("ora_init").value = this.cells[3].innerHTML;
                         document.getElementById("ora_fin").value = this.cells[4].innerHTML;
                         
                         var radios = document.getElementsById("sala");
                         for (var j = 0; j < radios.length; j++) {
                             if (radios[j].value == this.cells[5].innerHTML) {
                                 radios[j].checked = true;
                                 break;}}
                    }
                }
        </script>
		</form>
	</div>
  		<button onClick="document.location.href='/Project/home.jsp'" >Indietro</button><br>
</body>
</html>