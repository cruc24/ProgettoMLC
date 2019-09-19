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
