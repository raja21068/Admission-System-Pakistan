	$( document ).ready(function() {
		$('#busy').hide();
		$("#pd").click(function(){
			$("#content").load('personal_detail.php');
		});
		$("#cd").click(function(){
			$("#content").load('contact_detail.php');
		});
		$("#cp").click(function(){
			$("#content").load('password_setting.php');
		});
				
		$.post('handler/campus_handler.php', {code:"cams"}, 
			function(data){		
				$.each(data, function(key, val) {
					$( "#campus_id" ).append("<option value='" + val.campus_id + "'>" + val.campus_name + "</option>");
				});
			}, 
		"json").fail(function() { alert("error: cams"); });
		
		$( "#campus_id" ).click(function( event ) {
			$("#faculty_campus_id option").remove();
			$("#dept_id option").remove();
			$("#dept_prog_id option").remove();
			
			var campus_id = $('#campus_id').val();
			if(campus_id == "") return;

			$.post('handler/campus_handler.php', {campus_id:campus_id, code:"fcs"}, 
				function(data){
					$.each(data, function(key, val) {
						$("#faculty_campus_id").append("<option value='" + val.faculty_campus_id + "'>" + val.fac_name + "</option>");
					});
				},
			"json").fail(function() { alert("error: fcs"); });
		});
		
        $( "#faculty_campus_id" ).click(function( event ) {
			$("#dept_id option").remove();
			$("#dept_prog_id option").remove();

			var faculty_campus_id = $('#faculty_campus_id').val();
			if(faculty_campus_id == "") return;
			
			$.post('handler/campus_handler.php', {faculty_campus_id:faculty_campus_id, code:"depts"}, 
				function(data){
					$.each(data, function(key, val) {
						$("#dept_id").append("<option value='" + val.dept_id + "'>" + val.dept_name + "</option>");
					});
				},
			"json").fail(function() { alert("error: depts"); });
		});
		
		$("#dept_id").click(function( event ) {
			$("#dept_prog_id option").remove();
			
			var dept_id = $('#dept_id').val();
			if(dept_id == "") return;
			
			$.post('handler/campus_handler.php', {dept_id:dept_id, code:"dps"}, 
				function(data){
					$.each(data, function(key, val) {
						$("#dept_prog_id").append("<option value='" + val.dept_prog_id + "'>" + val.prog_name + " (" + val.prog_duration + " Year)" + "</option>");
					});
				},
			"json").fail(function() { alert("error: dps"); });
		});
		$("#dept_prog_id").click(function( event ) {
			var dept_prog_id = $('#dept_prog_id').val();
			if(dept_prog_id == "") return;
			
			$.post('handler/campus_handler.php', {dept_prog_id:dept_prog_id, code:"dp"}, 
				function(data){
					if(data.is_disciplane == 1){
						$("#subject").prop("disabled", false);
						$('#subject').css('background-color', '#FFFFFF');
						$('#subject').prop('required',true);
					}else{
						$("#subject").prop("disabled", true);
						$('#subject').css('background-color', '#CCCCCC');
						$('#subject').prop('required',false);
					}
				},
			"json").fail(function() { alert("error: dp"); });
		});
    });
	$(document).ajaxStart(function(){$('#busy').show();}).ajaxStop(function(){$('#busy').hide();});// JavaScript Document