$(document).ready(function(){
	
	$('#busy').hide();
	$("#pd").click(function(){
		$("#page-content2").load('personal_detail.php');
	});
	$("#cd").click(function(){
		$("#page-content2").load('contact_detail.php');
	});
	$("#cp").click(function(){
		$("#page-content2").load('setting.php');
	});
});
$(document).ajaxStart(function(){$('#busy').show();}).ajaxStop(function(){$('#busy').hide();});// JavaScript Document