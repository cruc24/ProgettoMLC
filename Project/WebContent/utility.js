	
	
	function adjustTime(time){
		if(time >= 0 && time <= 9)
			time= "0"+String(time);
			return time;
	}
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
	
	
	