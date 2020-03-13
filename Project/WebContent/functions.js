/*$(document).ready(function(){
		mostra();
	});*/
function hide_table(){
		 $("#alter_form").css('display','none');
		 $("#tab").css('display','block');
	 }
	function show_table(){
	$(document).ready(function(){
		$(".press").click(function(){
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

	function mostra(){
	$.get("Visualizza_Film",function(data){
		var s="";
		s=" <table class='tabella'><th>titolo</th><th>Data</th><th>ora inizio</th><th>ora fine</th><th>sala</th><th>status</th>";
		for(i in data.films)
			{
				s+="<tr>";
				s+="<td>"+data.films[i].titolo+"</td>";
				s+="<td>"+data.films[i].data+"</td>";
				s+="<td>"+data.films[i].ora_init+"</td>";
				s+="<td>"+data.films[i].ora_fine+"</td>";
				s+="<td>"+data.films[i].sala_cinema+"</td>";
				var now = new Date();
				now = now.getHours()*60 + now.getMinutes();
				var init = data.films[i].ora_init.split(":");
				init = parseInt(init[0])*60 + parseInt(init[1]);
				var fine = data.films[i].ora_fine.split(":");
				fine = parseInt(fine[0])*60 + parseInt(fine[1]);
				if (now>init && now<fine)
				{
					s+="<td style='color:#ff0000'>"+"Live!"+"</td>";
				}
				else
				{
					s+="<td></td>";
				}
				s+="<td>"+"<img src='"+"./Locandine_film/"+data.films[i].filename+"'width='50' heigth='50'>"+ "</td>";
				s+="</tr>"
			}
		s+="</table>";
		document.getElementById("sub-content").innerHTML=s;
		//setInterval(show(),60000);
		});
}

function modifica(){
		$.get("Visualizza_Film",function(data){
			var s="";
			s=" <table class='tabella' id='tab'><th>id</th><th>titolo</th><th>Data</th><th>ora inizio</th><th>ora fine</th><th>sala</th><th>action</th>";
			for(i in data.films)
				{
					s+="<tr>";
					s+="<td>"+data.films[i].id+"</td>";
					s+="<td>"+data.films[i].titolo+"</td>";
					s+="<td>"+data.films[i].data+"</td>";
					s+="<td>"+data.films[i].ora_init+"</td>";
					s+="<td>"+data.films[i].ora_fine+"</td>";
					s+="<td>"+data.films[i].sala_cinema+"</td>";
					s+="<td>" + "<button onclick='show_table()' class='press'> modifica</button>"+"</td>";
					s+="</tr>"
				}
			s+="</table>";
			s+="<div id='alter_form' style='display:none; text-align:center; height:70%'>";
			s+="<button onclick='hide_table()'>Close</button>";
				//s+="<form onsubmit='mod()' method='post'>";
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
			    document.getElementById("sub-content").innerHTML=s;
		});
	}
	 
	 function elimina(){
		 $.get("Visualizza_Film",function(data){
			 	var s="";
				s=" <table class='tabella' id='tab'><th>id</th><th>titolo</th><th>Data</th><th>ora inizio</th><th>ora fine</th><th>sala</th><th>action</th>";
				for(i in data.films)
					{
						s+="<tr>";
						s+="<td>"+data.films[i].id+"</td>";
						s+="<td>"+data.films[i].titolo+"</td>";
						s+="<td>"+data.films[i].data+"</td>";
						s+="<td>"+data.films[i].ora_init+"</td>";
						s+="<td>"+data.films[i].ora_fine+"</td>";
						s+="<td>"+data.films[i].sala_cinema+"</td>";
						s+="<td>" + "<button onclick='show_table()' class='press'> elimina </button>"+"</td>";
						s+="</tr>"
					}
				s+="</table>";
				s+="<div id='alter_form' style='display:none; text-align:center'>";
					s+="<form action='http://localhost:8080/Project/Elimina_Film' method='post'>";
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
				    document.getElementById("sub-content").innerHTML=s;
			});
		}
	/* 
	 function logout(){
		 $.get("Logout",function(){alert("disconnetto")});
	 }
	 */
	$(document).ready(function() {
	    $("#logout").click(function() {
	    	 $.get("Logout",function(){
	    		alert("disconnessione");
	    		location.reload();
	    	 });
	    });});  	
	    	
	    	
	    	
	 function sleep(s){
		  var now = new Date().getTime();
		  while(new Date().getTime() < now + (s*1000)){ /* non faccio niente */ } 
		}
	 function sala(numsala){
		 var vsala= 'Sala_'+ numsala;
		 console.log(vsala);
		 $.get("Gestione_Sale?sala="+vsala.valueOf(),function(data){
		var s="";
		var x= data.films.length;
		console.log(x);
		s+="<h1>Sala"+numsala+"</h1>";
			for(i in data.films){
					s+="<div class='lista' id='"+i+"' style='display:none';background-color:#f1e2d3;>";
					s+="<p>"+data.films[i].titolo+"</p>";
					s+="<img style='width:20%;height:20%' src='./Locandine_film/"+data.films[i].filename+"'>";
					s+="<p>"+data.films[i].ora_init+"/"+data.films[i].ora_fine+"</p>";
					s+="</div>";
				}
			$("#sub-content").html(s);
					
			var i=0;
						while(i<x)
						{
						console.log(i);	
						$("#"+i).css("display","block");
						sleep(2);
						setTimeout(function(){$("#"+i).css("display","none")},2000);
						i++;
						}
		 });
	}
$(document).ready(function() {
    var interval = setInterval(function() {
        var momentNow = new Date();
        week= ['Lun','Mar','Mer','Gio','Ven','Sab','Dom']
        var time = week[momentNow.getDay()-1]+","+ momentNow.getDate()+"/"+momentNow.getMonth()+1+"/"+momentNow.getFullYear()+"\t"
        time= time+momentNow.getHours()+":"+momentNow.getMinutes()+":"+momentNow.getSeconds()
        $('#time').html(time);
}, 1000);
});
