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
var numPerPage=5;
var currentPage=0;
var pages=1;
function mostra(sala){
	currentPage=0;
	var servlet="";
	var vsala=""
	if(sala== undefined){
		vsala="Films"
		servlet="Visualizza_Film";
	}
	else{
		var vsala= 'Sala_'+ sala;
		servlet="Gestione_Sale?sala="+vsala.valueOf();
	}
	$.get(servlet,function(data){
		var s="";
		films=data;
		pages= films.length/numPerPage;
		if(films.length >0)
		{
			s=" <table class='tabella' id='tab'><h2>"+vsala+"</h2><thead><th>Titolo</th><th>Data</th><th>Ora Inizio</th><th>Ora Fine</th><th>Sala</th><th>Status</th><th><button id='backward'><<</button><button id='forward'> >> </button></th></thead>";
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
				s+="<td>"+"<img src='data:image/jpg;base64,"+films[i].image+"' width='50' height='50'/>"+ "</td>";
				s+="</tr>";
			}
			s+="</tr>";
			s+="</table>";
		}
		else
			s+="<p>Non ci sono film disponibili in "+vsala+"</p>";
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

function edit(mode){
	$.get("Visualizza_Film",function(data){
		var s="";
		currentPage=0;
		films=data;
		pages= films.length/numPerPage;
		if(films.length >0)
		{
			s=" <table class='tabella' id='tab'><thead><th>Id</th><th>Titolo</th><th>Data</th><th>Ora Inizio</th><th>Ora Fine</th><th>Sala</th><th>Action</th></thead>";
			for(i in films)
			{
				s+="<tr>";
				s+="<td>"+films[i].id+"</td>";
				s+="<td>"+films[i].titolo+"</td>";
				s+="<td>"+films[i].data+"</td>";
				s+="<td>"+films[i].ora_init+"</td>";
				s+="<td>"+films[i].ora_fine+"</td>";
				s+="<td>"+films[i].sala_cinema+"</td>";
				s+="<td>" + "<button onclick='show_table()' class='press'>"+mode+"</button>"+"</td>";
				s+="</tr>"
			}
			s+="<tfoot><tr><td><button id='backward'><<</button><button id='forward'> >></button></td></tr><tfoot>";
			s+="</table>";
			s+="<div id='alter_form' style='display:none; text-align:center'";
			s+="<button onclick='hide_table()' style='float:right;border:0px;background:white;'>&times;</button>";
			s+="<form action='/Project/"+mode+"_Film' method='POST'>";
			s+="<h2 style='center'>"+mode+" Film </h2>";
			s+="Id: <input type='text' name='id' id='id' readonly ><br /><br />";
			s+="Titolo: <input type='text' name='titolo' id='titolo' ><br /><br />";
			s+="Giorno:<input type='date' name='giorno' id='giorno' > <br/><br/>";
			s+="Ora Inizio: <input type='time' name='ora_init' id='ora_init' ><br /><br />";
			s+="Ora Fine: <input type='time' name='ora_fine' id='ora_fin' ><br /><br />";
			s+="Sala: <br />";
			s+="<input type='radio' name='sala' value='Sala_1' id='sala' > Sala 1 <br />";
			s+="<input type='radio' name='sala' value='Sala_2' id='sala' > Sala 2 <br />";
			s+="<input type='radio' name='sala' value='Sala_3' id='sala' > Sala 3 <br />";
			s+="<input type='radio' name='sala' value='Sala_4' id='sala' > Sala 4 <br />";
			s+="<input type='submit' value='"+mode+"'><br /> <br />";
			s+="</form></div>";
		}
		document.getElementById("sub-content").innerHTML=s;
		if(mode== "Elimina")
		{
		$("form input").each(function(){
				$(this).attr('readonly',true);});
		}
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


$(document).ready(function() {
	$("#logout").click(function() {
		$.get("Logout",function(){
			alert("disconnessione");
			location.reload();
		});
	});});  	

function aggiungi(){
	var s="";
	s="<div class='div_add' style='align:center;display:grid;' >";	
	s+="<h2 class='title' style='color:black;'>Aggiungi Film </h2>";
	s+="<form action='/Project/Add_film' method='POST' name='form_add' enctype='multipart/form-data'class='form_add'>";
	s+="<label>Titolo film:</label>";
	s+="<input type='text' name='titolo' placeholder='Titolo...' > <br />";
	s+="<label>Data riproduzione film:</label>";
	s+="<input type='date' name='giorno' id='giorno' > <br/>";
	s+="<label>Orario inizio film:</label>";
	s+="<input type='time' name='ora_init' id='ora_init' > <br />";
	s+="<label>Orario termine film:</label>";
	s+="<input type='time' name='ora_fine' id='ora_fine' ><br />";
	s+="<label>Sala proiezione del film:</label><br />";
	s+="<input type='radio' name='sala' value='Sala_1' checked> Sala 1 <br />";
	s+="<input type='radio' name='sala' value='Sala_2'> Sala 2 <br />";
	s+="<input type='radio' name='sala' value='Sala_3'> Sala 3 <br />";
	s+="<input type='radio' name='sala' value='Sala_4'> Sala 4 <br />";
	s+="<label>Locandina film:</label>";
	s+="<input type='file' name='file' id='file' > <br /><br />";
	s+="<input type='submit' value='Aggiungi'>";
	s+="</form>";
	s+="</div>";
	document.getElementById("sub-content").innerHTML=s;
}
