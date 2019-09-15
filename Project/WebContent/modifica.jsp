<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="com.Project.servlet.*,com.Project.beans.*,java.util.*,java.io.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% 
	ArrayList<Film> f= new ArrayList<>();
	f=(ArrayList<Film>) request.getAttribute("film");
	Iterator <Film> i= f.iterator();
	%>
	<table id="tabella">
		<tr>
			<th></th>
			<th>Titolo</th>
			<th>Giorno</th>
			<th>Ora_init</th>
			<th>Ora_fin</th>
			<th>Durata</th>
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
	      out.println("<td>" + film.getDurata()+"</td>");
	      out.println("<td>" + film.getSala() + "</td>");
	      out.print("<td>" + "<button onclick=document.getElementById('alter_form').style.display='block' style='width:auto;'>"+"modifica</button>"+"</td>");
	      out.println("</tr>");
		}%>
	</table>
	<div id="alter_form" style="display:none;">
		<form action="http://localhost:8080/Project/Modifica_Film" method="POST" >
		<h5>Modifica Film </h5>
		Id: <input type="text" name="id" id="id" ><br /><br />
		Titolo: <input type="text" name="titolo" id="titolo" ><br /><br />
		Giorno: <input type="text" name="giorno" id="giorno" ><br /><br />
		Ora Inizio: <input type="text" name="ora_init" id="ora_init" ><br /><br />
		Ora Fine: <input type="text" name="ora_fine" id="ora_fin" ><br /><br />
		Durata: <input type="text" name="durata" id="durata" ><br /><br />
		Sala: <br />
				<input type="radio" name="sala" value="Sala_1" id="sala_cinema"> Sala 1 <br />
				<input type="radio" name="sala" value="Sala_2" id="sala_cinema"> Sala 2 <br />
				<input type="radio" name="sala" value="Sala_3" id="sala_cinema"> Sala 3 <br />
				<input type="radio" name="sala" value="Sala_4" id="sala_cinema"> Sala 4 <br />
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
                         document.getElementById("durata").value = this.cells[5].innerHTML;
                         
                         var radios = document.getElementsById("sala_cinema");
                         for (var j = 0; j < radios.length; j++) {
                             if (radios[j].value == this.cells[6].innerHTML) {
                                 radios[j].checked = true;
                                 break;}}
                         //document.getElementById("sala_cinema").checked = this.cells[7].innerHTML;
                    };
                }
        </script>
		</form>
	</div>
  		<button onClick="document.location.href='/Project/home.jsp'" >Indietro</button><br>
</body>
</html>