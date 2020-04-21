
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
	        var time = week[momentNow.getDay()]+","+ momentNow.getDate()+"/"+String(momentNow.getMonth()+1)+"/"+momentNow.getFullYear()+"\t";
	        time= time+adjustTime(momentNow.getHours())+":"+adjustTime(momentNow.getMinutes())+":"+adjustTime(momentNow.getSeconds());
	        $('#time').html(time);
	}, 1000);
	});	
$(document).on('click','#tab tbody tr', function() { 
    var tds = $(this).find('td');
    var values = [];
    tds.each(function(index, item) {  
    	values.push($(item).html())
        //console.log(values);
    });
});

