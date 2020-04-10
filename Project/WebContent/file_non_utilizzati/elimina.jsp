<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="com.Project.servlet.*,com.Project.beans.*,java.util.*,java.io.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./projectStyle.css">
<script src="jquery-3.4.1.min.js"></script>
<% String message= ""; 
   	message =(String) request.getAttribute("errore"); %>
<script>
$(document).ready(function(){
$.get("Visualizza_Film",function(data){
	var flightsPerPage = 5;
	var secondsPerPage = 3;
	var newDataAvailable = false;
	var currentPage=-1;
	var pages;
	var flights;
	
	var s="<th>Id</th><th>titolo</th><th>Data</th><th>ora inizio</th><th>ora fine</th><th>sala</th>";
	for(i in data.films)
		{
			s+="<tr>";
			s+="<td>"+data.films[i].id+"</td>";
			s+="<td>"+data.films[i].titolo+"</td>";
			s+="<td>"+data.films[i].data+"</td>";
			s+="<td>"+data.films[i].ora_init+"</td>";
			s+="<td>"+data.films[i].ora_fine+"</td>";
			s+="<td>"+data.films[i].sala_cinema+"</td>";
			s+="<td>" + "<button onclick='show()' class='elimina'> elimina</button>"+"</td>";
			s+="</tr>"
		}
	document.getElementById("tab").innerHTML=s;
	
	});
});
</script>
</head>
<body>
	<p id="errore" style="display:none"></p>
	<table id="tab" class="tabella"></table>
	<div id="alter_form" style="display:none;">
		<form action="http://localhost:8080/Project/Elimina_Film" method="POST">
		<h5>Elimina Film </h5>
		Id:<input type="text" name="id" id="id" ><br /><br />
		Titolo: <input type="text" name="titolo" id="titolo" readonly ><br /><br />
		Giorno: <input type="text" name="giorno" id="giorno" readonly ><br /><br />
		Ora Inizio: <input type="text" name="ora_init" id="ora_init" readonly><br /><br />
		Ora Fine: <input type="text" name="ora_fine" id="ora_fin" readonly ><br /><br />
		Sala: <input type="text" name="sala" id="sala" readonly ><br /><br />
		<input type="submit" value="Elimina"><br /> <br />
		<script>
		function show(){
			$(document).ready(function(){
				$(".elimina").click(function(){
					$("#alter_form").css('display','block');
					$("#tab").css('display','none');
			var table = document.getElementById('tab');
                for(var i = 1; i < table.rows.length; i++)
                {
                    table.rows[i].onclick = function()
                    {
						 document.getElementById("id").value = this.cells[0].innerHTML;
                         document.getElementById("titolo").value = this.cells[1].innerHTML;
                         document.getElementById("giorno").value = this.cells[2].innerHTML;
                         document.getElementById("ora_init").value = this.cells[3].innerHTML;
                         document.getElementById("ora_fin").value = this.cells[4].innerHTML;
                         document.getElementById("sala").value = this.cells[5].innerHTML;
                    }
                }
				});
			});
		}
		$(document).ready(function(){
			$("form").click(function(){
				$("#tab").css('display','block');
				$("#alter_form").css('display','none');
		});
	});
		$(document).ready(function(){
			var a= '<%=message%>';
			if(a != null)
			{
				$("errore").css("display","block");
				$("errore").html(a);
			}
			
	});
		
        </script>
		</form>
	</div>
  		<button onClick="document.location.href='/Project/home.jsp'" >Indietro</button><br>
</body>
</html>