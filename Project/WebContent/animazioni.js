//pulsante che permette lo show della password nella pagina di login
	$(document).ready(function(){
			  $("#togglepwd").click(function(){
			    if( $('#password').attr('type') === "password"){
			    	$('#password').attr('type','text');}
			    else{
			    	$('#password').attr('type','password');}
			  });
			});
// animazione pagina login che dopo l'intro fa apparire il form di login
	$(document).ready(function(){
		setTimeout(function(){
			$("#intro").css("display","none"); 
			$("#login").css("display","block");},2300);
	});
// cambio colore dello sfondo dell'elemento del menù sul quale ho il puntatore
	$(document).ready(function(){
		$("li").mouseover(function(){
			$(this).css("background","gold");
			//$(".menu").css("border","2px solid black");		
		});
	});
// ritorno al colore dello sfondo precedente dell'elemento del menù dal quale ho tolto il puntatore 
	$(document).ready(function(){
		$("li").mouseout(function(){
			$(this).css("background","black");
			//$(".menu").css("border","2px solid black");
		});
	});
// tr change
$(document).ready(function(){
	$("tr").mouseover(function(){
		$(this).css("background","gold");		
	});
});


/* stampa quale elemento ho clickato
$(document).ready(function(){
	$("ul").children("li").each(function(){
		$(this).click(function(){
			console.log($(this).text())
		});
	});
});
*/








