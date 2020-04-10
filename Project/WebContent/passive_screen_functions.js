/**
 * 
 */

// da usare per lo schermo passivo	
	var filmsPerPage = 5;
	var secondsPerPage = 3;
	var newDataAvailable = false;
	var currentPage=-1;
	var pages;
	var films;

	function visualizza_passivo() {
		startLoadData(); // Load new XML file every 60 seconds
		if(films != null)
			startDisplay(); // Display current data
	}
	function startLoadData(){
		loadNewData();
		newDataAvailable = true;
		setTimeout("startLoadData()", 60000);
	}
	function startDisplay(){
		if(newDataAvailable){
			createNewPages();
			currentPage = -1;
			newDataAvailable = false;
		}		
		currentPage++;
		currentPage = currentPage % pages.length;
		$("#sub-content").html(pages[currentPage]);
		setTimeout("startDisplay()", secondsPerPage*1000);
	}
	function createNewPages(){
		pages = new Array();
		console.log(films.length)
		var numPages = Math.ceil(films.length/filmsPerPage);
		for(var p = 0; p < numPages; p++){
			pages.push(createFilmsPage(p));			
		}
	}
	function createFilmsPage(n){
		console.log(films);
		var s="";
		s=" <table class='tabella' id='tab'><th>id</th><th>titolo</th><th>Data</th><th>ora inizio</th><th>ora fine</th><th>sala</th><th>action</th>";
		for(var i = n*filmsPerPage; i<(n+1)*filmsPerPage && i<films.length; i++)
			{
				s+="<tr>";
				s+="<td>"+films[i].id+"</td>";
				s+="<td>"+films[i].titolo+"</td>";
				s+="<td>"+films[i].data+"</td>";
				s+="<td>"+films[i].ora_init+"</td>";
				s+="<td>"+films[i].ora_fine+"</td>";
				s+="<td>"+films[i].sala_cinema+"</td>";
				s+="</tr>"
			}
		s+="</table>";
		return s;
	}
	function loadNewData(){
		$.get("Visualizza_Film",function(data){
			films = data;
		});
	}
// funzione che effettua la vista dei film nelle sale	
var slideIndex = 0;
function showSlides() {
	  var i;
	  var slides = document.getElementsByClassName("lista");
	  for (i = 0; i < slides.length; i++) {
	    slides[i].style.display = "none";
	  }
	  slideIndex++;
	  if (slideIndex > slides.length) {slideIndex = 1}
	  slides[slideIndex-1].style.display = "block";
	  setTimeout(showSlides, 4000); // Change image every 2 seconds
	}

function sala(numsala){
	var vsala= 'Sala_'+ numsala;
	$.get("Gestione_Sale?sala="+vsala.valueOf(),function(data){
		var s="";
		var films= data;
		s+="<h1>Sala"+numsala+"</h1>";
		for(i in films){
			s+="<div class='lista' id="+i+" style='display:none;'>";
			s+="<p>"+films[i].titolo+"</p>";
			s+="<p><img src='"+"./Locandine_film/"+ films[i].filename+"' width='50%' heigth='50%'>"+"</p>";
			s+="<p>"+films[i].ora_init+"/"+films[i].ora_fine+"</p>";
			s+="</div>";
		}
		$("#sub-content").html(s);
		showSlides();
	});
}
	
	
	
	
	
	
	
	
	
	
	
	
	