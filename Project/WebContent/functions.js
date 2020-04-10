function hide_table(){
	$("#alter_form").hide();
	$("#tab").show();
}

function show_table(){
	$(document).ready(function(){
		$(".press").click(function(){
			$("#tab").hide();
			$("#alter_form").show();
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

function mostra_sala(numsala){
	var vsala= 'Sala_'+ numsala;
	$.get("Gestione_Sale?sala="+vsala.valueOf(),function(data){
		var s="";
		films=data;
		if(films.length >0)
		{
		s= "<h1>vsala</h1>";
		s=" <table class='tabella' id='tab'><h3>"+vsala+"</h3><th>titolo</th><th>Data</th><th>ora inizio</th><th>ora fine</th><th>sala</th><th>status</th>";
			for(i in films)
			{
				s+="<tr>";
				s+="<td>"+films[i].titolo+"</td>";
				s+="<td>"+films[i].data+"</td>";
				s+="<td>"+films[i].ora_init+"</td>";
				s+="<td>"+films[i].ora_fine+"</td>";
				s+="<td>"+films[i].sala_cinema+"</td>";
				var now = new Date();
				now = now.getHours()*60 + now.getMinutes();
				var init = films[i].ora_init.split(":");
				init = parseInt(init[0])*60 + parseInt(init[1]);
				var fine = films[i].ora_fine.split(":");
				fine = parseInt(fine[0])*60 + parseInt(fine[1]);
				var oggi= new Date();
				oggi= oggi.getFullYear()+"-0"+(oggi.getMonth()+1)+"-"+oggi.getDate()
				console.log(oggi);
				if (now>init && now<fine && films[i].data == oggi)
					s+="<td style='color:#ff0000'>"+"Live!"+"</td>";
				else{
					s+="<td></td>";
				s+="<td>"+"<img src='"+"./Locandine_film/"+ films[i].filename+"'width='50' heigth='50'>"+ "</td>";
				s+="</tr>";
				s+="</table>";
			}
					}
		}
		else
			s+="<p>Non ci sono film disponibili in "+vsala+"</p>";
		document.getElementById("sub-content").innerHTML=s;
		});
}

var numPerPage=5;
var currentPage=0;
var pages=1;
function mostra(){
	$.get("Visualizza_Film",function(data){
		var s="";
		films=data;
		pages= films.length/numPerPage;
		s=" <table class='tabella' id='tab'><thead><th>titolo</th><th>Data</th><th>ora inizio</th><th>ora fine</th><th>sala</th><th>status</th></thead>";
		for( i in films)
		{
			s+="<tr>";
			s+="<td>"+films[i].titolo+"</td>";
			s+="<td>"+films[i].data+"</td>";
			s+="<td>"+films[i].ora_init+"</td>";
			s+="<td>"+films[i].ora_fine+"</td>";
			s+="<td>"+films[i].sala_cinema+"</td>";
			var now = new Date();
			now = now.getHours()*60 + now.getMinutes();
			var init = films[i].ora_init.split(":");
			init = parseInt(init[0])*60 + parseInt(init[1]);
			var fine = films[i].ora_fine.split(":");
			fine = parseInt(fine[0])*60 + parseInt(fine[1]);
			var oggi= new Date();
			oggi= oggi.getFullYear()+"-0"+(oggi.getMonth()+1)+"-"+oggi.getDate()
			if (now>init && now<fine && films[i].data == oggi)
				s+="<td style='color:#ff0000'>"+"Live!"+"</td>";
			else
				s+="<td></td>";
			s+="<td>"+"<img src='"+"./Locandine_film/"+ films[i].filename+"'width='50' heigth='50'>"+ "</td>";
			s+="</tr>"
		}
		s+="<tfoot style='float:right;'><tr>";
		s+="<button id='backward'> << </button>";
		s+="<button id='forward'> >> </button>";
		s+="</tr></tfoot>";
		s+="</table>";
		document.getElementById("sub-content").innerHTML=s;
		$("#tab").find("tbody tr").hide().slice(currentPage*numPerPage, ((currentPage+1)*numPerPage)).show();//0-4
		$("#forward").click(function(){		
			if(currentPage+1 < pages){
				currentPage++;
				$("#forward").attr("disable",false);
				$("#tab").find("tbody tr").hide();
				$("#tab").find("tbody tr").hide().slice(currentPage*numPerPage, ((currentPage+1)*numPerPage)).show();
			}
			else
				$("#forward").attr("disable",true);
		});
		$("#backward").click(function(){					
			if(currentPage-1 >= 0){
				currentPage--;
				$("#backward").attr("disable",false);
				$("#tab").find("tbody tr").hide();
				$("#tab").find("tbody tr").hide().slice(currentPage*numPerPage, ((currentPage+1)*numPerPage)).show();
			}
			else
				$("#backward").attr("disable",true);
		});
	});
}

function modifica(){
	$.get("Visualizza_Film",function(data){
		var s="";
		var films= data;
		s=" <table class='tabella' id='tab'><th>id</th><th>titolo</th><th>Data</th><th>ora inizio</th><th>ora fine</th><th>sala</th><th>action</th>";
		for(i in films)
		{
			s+="<tr>";
			s+="<td>"+films[i].id+"</td>";
			s+="<td>"+films[i].titolo+"</td>";
			s+="<td>"+films[i].data+"</td>";
			s+="<td>"+films[i].ora_init+"</td>";
			s+="<td>"+films[i].ora_fine+"</td>";
			s+="<td>"+films[i].sala_cinema+"</td>";
			s+="<td>" + "<button onclick='show_table()' class='press'> modifica</button>"+"</td>";
			s+="</tr>"
		}
		s+="</table>";
		s+="<div id='alter_form' style='display:none; text-align:center; height:70%' class='modal'>";
		s+="<button onclick='hide_table()' style='float:right;border:0px;background:white;'>&times;</button>";
		s+="<form action='http://localhost:8080/Project/Modifica_Film' method='post'>";
		s+="<h5>Modifica Film </h5>";
		s+="Id: <input type='text' name='id' id='id' readonly ><br /><br />";
		s+="Titolo: <input type='text' name='titolo' id='titolo' ><br /><br />";
		s+="Giorno: <input type='text' name='giorno' id='giorno' ><br /><br />";
		s+="Ora Inizio: <input type='text' name='ora_init' id='ora_init' ><br /><br />";
		s+="Ora Fine: <input type='text' name='ora_fine' id='ora_fin' ><br /><br />";
		s+="Sala: <br />";
		s+="<input type='radio' name='sala' value='Sala_1' id='sala'> Sala 1 <br />";
		s+="<input type='radio' name='sala' value='Sala_2' id='sala'> Sala 2 <br />";
		s+="<input type='radio' name='sala' value='Sala_3' id='sala'> Sala 3 <br />";
		s+="<input type='radio' name='sala' value='Sala_4' id='sala'> Sala 4 <br />";
		s+="<input type='submit' value='press'><br /> <br />";
		s+="</form></div>";
		document.getElementById("sub-content").innerHTML=s;
	});
}

function elimina(){
	$.get("Visualizza_Film",function(data){
		var s="";
		var films=data;
		s=" <table class='tabella' id='tab'><th>id</th><th>titolo</th><th>Data</th><th>ora inizio</th><th>ora fine</th><th>sala</th><th>action</th>";
		for(i in films)
		{
			s+="<tr>";
			s+="<td>"+films[i].id+"</td>";
			s+="<td>"+films[i].titolo+"</td>";
			s+="<td>"+films[i].data+"</td>";
			s+="<td>"+films[i].ora_init+"</td>";
			s+="<td>"+films[i].ora_fine+"</td>";
			s+="<td>"+films[i].sala_cinema+"</td>";
			s+="<td>" + "<button onclick='show_table()' class='press'> elimina </button>"+"</td>";
			s+="</tr>"
		}
		s+="</table>";
		s+="<div id='alter_form' style='display:none; text-align:center'>";
		s+="<form action='http://localhost:8080/Project/Elimina_Film' method='GET'>";
		s+="<h5>Elimina Film </h5>";
		s+="Id: <input type='text' name='id' id='id' readonly ><br /><br />";
		s+="Titolo: <input type='text' name='titolo' id='titolo' ><br /><br />";
		s+="Giorno: <input type='text' name='giorno' id='giorno' ><br /><br />";
		s+="Ora Inizio: <input type='text' name='ora_init' id='ora_init' ><br /><br />";
		s+="Ora Fine: <input type='text' name='ora_fine' id='ora_fin' ><br /><br />";
		s+="Sala: <br />";
		s+="<input type='radio' name='sala' value='Sala_1' id='sala'> Sala 1 <br />";
		s+="<input type='radio' name='sala' value='Sala_2' id='sala'> Sala 2 <br />";
		s+="<input type='radio' name='sala' value='Sala_3' id='sala'> Sala 3 <br />";
		s+="<input type='radio' name='sala' value='Sala_4' id='sala'> Sala 4 <br />";
		s+="<button onclick='elimina()'>Close</button>";
		s+="<input type='submit' value='Elimina'><br /> <br />";
		s+="</form></div>";
		document.getElementById("sub-content").innerHTML=s;
		console.log(data);
	});
}
$(document).ready(function() {
	$("#logout").click(function() {
		$.get("Logout",function(){
			alert("disconnessione");
			location.reload();
		});
	});});  	

function aggiungi(){
	var s="";
	s="<div class='div_add' style='align:center;' >";	
	s+="<form action='http://localhost:8080/Project/Add_film' method='POST' name='form_add' enctype='multipart/form-data'>";
	s+="<h2 class='title'>Aggiungi Film </h2>";
	s+="<label>Titolo film:</label>";
	s+="<input type='text' name='titolo'> <br />";
	s+="<label>Data riproduzione film:</label>";
	s+="<input type='date' name='giorno' id='giorno'> <br>";
	s+="<label>Orario inizio film:</label>";
	s+="<input type='time' name='ora_init' id='ora_init'> <br />";
	s+="<label>Orario termine film:</label>";
	s+="<input type='time' name='ora_fine' id='ora_fine'><br />";
	s+="<label>Sala proiezione del film:</label><br />";
	s+="<input type='radio' name='sala' value='Sala_1' checked> Sala 1 <br />";
	s+="<input type='radio' name='sala' value='Sala_2'> Sala 2 <br />";
	s+="<input type='radio' name='sala' value='Sala_3'> Sala 3 <br />";
	s+="<input type='radio' name='sala' value='Sala_4'> Sala 4 <br />";
	s+="<label>Locandina film:</label>";
	s+="<input type='file' name='file' id='file'> <br /><br />";
	s+="<input type='submit' value='Aggiungi'>";
	s+="</form>";
	s+="</div>";
	document.getElementById("sub-content").innerHTML=s;
}
//completare questo
function validateForm() {
	var titolo = document.forms["form_add"]["titolo"].value;
	if(titolo == null || titolo == " ") {
		$("#titolo").after("il campo non può essere ")
		alert("Name must be filled out");
		return false;
	}
	var data= document.forms["form_add"]["giorno"].value;
	if(!validateData()){// definire validate date
		alert("data non valida");
		return false;
	}
	var data= document.forms["form_add"]["giorno"].value;
	if(!validateOra()){// definire validate date
		alert("ora non valida");
		return false;
	}
}