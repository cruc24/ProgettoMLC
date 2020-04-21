function visualizza_passivo() {
	startLoadData(); // Load new XML file every 60 seconds
	//startDisplay(); // Display current data
}
function startLoadData(){
	loadNewData();
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
	var numPages = Math.ceil(films.length/filmsPerPage);
	for(var p = 0; p < numPages; p++){
		pages.push(createFilmsPage(p));			
	}
}
function createFilmsPage(n){
	var s="";
	s=" <table class='tabella' id='tab'><th>id</th><th>titolo</th><th>Data</th><th>ora inizio</th><th>ora fine</th><th>sala</th><th>status</th>";
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
		newDataAvailable = true;
		startDisplay();
	});
}
//funzione che effettua la vista dei film nelle sale	
function showSlides() {
  var i;
  var slides = document.getElementsByClassName("lista");
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";
  }
  slideIndex++;
  if (slideIndex > slides.length) {slideIndex = 1}
  slides[slideIndex-1].style.display = "block";
  setTimeout(showSlides, 10000); // Change image every 2 seconds
}

function sala(numsala){
var vsala= 'Sala_'+ numsala;
$.get("Gestione_Sale?sala="+vsala.valueOf(),function(data){
	var s="";
	var films= data;
	var banner="";
	for(i in films){
		s+="<div class='lista' id="+i+" style='text-align:center;font-size:20pt;background-color:white;font-style:italic;'>";
		s+="<p>"+films[i].titolo+"</p>";
		s+="<p><img src='data:image/jpg;base64,"+films[i].image+"' width='300' height='300'/></p>";
		s+="<p> Orario:"+films[i].ora_init+"/"+films[i].ora_fine+"</p>";
		s+="</div>";
	}
	$("#sub-content").html(s);
	showSlides();
});
}