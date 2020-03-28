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
		startDisplay(); // Display current data
	}

	//
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
		for(i in films)//aggiustare manca il while
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
