//pulsante che permette lo show della password nella pagina di login
$(document).ready(function(){
		  $("#togglepwd").click(function(){
		    if( $('#password').attr('type') === "password"){
		    	$('#password').attr('type','text');}
		    else{
		    	$('#password').attr('type','password');}
		  });
		});
// animazione pagina login
$(document).ready(function(){
	setTimeout(function(){
		$("#intro").css("display","none"); 
		$("#login").css("display","block");},2300);
});

// script che controlla se la data inserita sia successiva a quella odierna
$(document).ready(function(){
	$("#giorno").change(function(){
		  var msg= ['anno non valido.','mese non valido.','giorno non valido.'];
		  var data=new Date();
		  var str="";
		  var v= $("#giorno").val().split("-");
		  var d=[data.getFullYear(),data.getMonth()+1,data.getDate()];
		  for(var i=0;i<d.length;i++){
			  if(v[i]<d[i])
				  str=str+msg[i];
		  }
		  $("#check_data").html(str);
		});
	});
// calcolo automatico della durata
$(document).ready(function(){
	$("#ora_fine").change(function(){
		var a=$("#ora_init").val().split(":");
		var b=$("#ora_fine").val().split(":");
		var a_min=parseInt(a[0] * 60) + parseInt(a[1]);
		var b_min=parseInt (b[0]*60) + parseInt (b[1]);
		var diff= b_min - a_min;
		a=  Math.floor(diff / 60) ;
		b= (diff % 60);
		$("#durata").attr('value',a+" h "+b+" m");
	});
});
// verifica che l'orario di fine è dopo quello di inizio
$(document).ready(function(){
	$("#ora_fine").change(function(){
	
	});
});