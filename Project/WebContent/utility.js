// funzione che aggiunge uno 0 se il valore è compreso fra 0 e 9
	function adjustTime(time){
		if(time >= 0 && time <= 9)
			time= "0"+String(time);
			return time;
	}
// al caricamento della pagina viene lanciato l'orologio live
	$(document).ready(function() {
	    var interval = setInterval(function() {
	        var momentNow = new Date();
	        week= ['Dom','Lun','Mar','Mer','Gio','Ven','Sab'];
	        var time = week[momentNow.getDay()]+","+ momentNow.getDate()+"/"+momentNow.getMonth()+1+"/"+momentNow.getFullYear()+"\t";
	        time= time+adjustTime(momentNow.getHours())+":"+adjustTime(momentNow.getMinutes())+":"+adjustTime(momentNow.getSeconds());
	        $('#time').html(time);
	}, 1000);
	});
// quando clicchi su una riga ritorna i valori delle celle
	$(document).on('click','#tab tbody tr', function() { 
	    var tds = $(this).find('td');
	    var values = [];
	    tds.each(function(index, item) {  
	    	values.push($(item).html())
	        //console.log(values);
	    });
	});
	

	function elimina(){
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
						s+="<td>" + "<button onclick='show_table('Elimina')' class='press'> elimina </button>"+"</td>";
						s+="<td>" + "<button onclick='show_table('Modifica')' class='press'> modifica </button>"+"</td>";
						s+="</tr>"
					}
				s+="</table>";
				s+="<div id='alter_form' style='display:none; text-align:center'>";
					s+="<form method='POST'>";
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
				    s+="<input type='submit' value='modifica' name='edit' formaction='http://localhost:8080/Project/Modifica_Film'>";
				    s+="<input type='submit' value='elimina'  name='edit' formaction='http://localhost:8080/Project/Elimina_Film'>";
				    s+="</form></div>";
				    document.getElementById("sub-content").innerHTML=s;
			});
		}
	
	
	
	
	
	
	
	
	
	
	
	
	