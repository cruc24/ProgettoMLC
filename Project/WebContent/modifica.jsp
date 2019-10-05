<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="com.Project.servlet.*,com.Project.beans.*,java.util.*,java.io.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./projectStyle.css">
<script src="jquery-3.4.1.min.js"></script>
<title>Insert title here</title>
<style>
	#alter_form{
	z-index:10;
	text-align:center;
	width:100%;
	heigth:100%;
	background:#c0c0c0;
	position: relative;
	background-size:cover;
	margin:0 auto;
	top:0;
	}
</style>
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
			s+="<td>" + "<button onclick='show()' class='modifica'> modifica</button>"+"</td>";
			s+="</tr>"
		}
	document.getElementById("tab").innerHTML=s;
	
	});
});
</script>
</head>
<body>
<div class="header">
	<h1 class="title">MODIFICA FILMS</h1>
	<button class="button_header" onClick="document.location.href='/Project/home.jsp'" >Indietro</button>
</div>
<div class="content">
	<table id='tab' class='tabella'>
	</table>
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
		function show(){
			$(document).ready(function(){
				$(".modifica").click(function(){
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
                         var radios = document.getElementsByName("sala");
                         for (var j = 0; j < radios.length; j++) {
                             if (radios[j].value == this.cells[5].innerHTML) {
                                 radios[j].checked = true;
                                 break;}}
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
        </script>
		</form>
	</div>
</div>
</body>
</html>