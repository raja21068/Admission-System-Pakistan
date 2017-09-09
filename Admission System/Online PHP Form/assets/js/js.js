function getCollage(district_id, type){
	// Create an instance of the HTTP request object
	
	var xmlHttp;
	if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
	    xmlHttp = new XMLHttpRequest();
	}else{// code for IE6, IE5
	    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlHttp.onreadystatechange = function(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
	    	document.getElementById("collage_area").innerHTML = xmlHttp.responseText;
		}
	}; //innerHTML
	
	// Specify HTTP GET by default and supply the relative url
	// 0 = list, 1 = menu
	if(type == 0)
		xmlHttp.open("POST", "admin/collage_list.php?district_id=" + district_id, true);
	else xmlHttp.open("POST", "admin/collage_menu.php?district_id=" + district_id + "&type=" + type, true);
	// Start a synchronous AJAX request and wait 
    // for the response
	xmlHttp.send(null);
}
function getDepartment(fac_id, type){
	// Create an instance of the HTTP request object
	//alert("asd");
	var xmlHttp;
	if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
	    xmlHttp = new XMLHttpRequest();
	}else{// code for IE6, IE5
	    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlHttp.onreadystatechange = function(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
	    	document.getElementById("dept_area").innerHTML = xmlHttp.responseText;
		}
	}; //innerHTML
	
	// Specify HTTP GET by default and supply the relative url
	// 0 = list, 1 = menu
	if(type == 0) 
		xmlHttp.open("POST", "admin/department_list.php?fac_id=" + fac_id, true);
	else xmlHttp.open("POST", "admin/department_menu.php?fac_id=" + fac_id + "&type=" + type, true);
	// Start a synchronous AJAX request and wait 
    // for the response
	xmlHttp.send(null);
}
function getProgram(dept_id, type){
	// Create an instance of the HTTP request object
	//alert(dept_id);
	var xmlHttp;
	if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
	    xmlHttp = new XMLHttpRequest();
	}else{// code for IE6, IE5
	    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlHttp.onreadystatechange = function(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
	    	document.getElementById("prog_area").innerHTML = xmlHttp.responseText;
		}
	}; //innerHTML
	
	// Specify HTTP GET by default and supply the relative url
	// 0 = list, 1 = menu
	if(type == 0)
		xmlHttp.open("POST", "admin/program_list.php?dept_id=" + dept_id, true);
	else xmlHttp.open("POST", "admin/program_menu.php?dept_id=" + dept_id, true);
	// Start a synchronous AJAX request and wait 
    // for the response
	xmlHttp.send(null);
}
function getIndProgram(){
	// Create an instance of the HTTP request object
	//alert("asd");
	var xmlHttp;
	if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
	    xmlHttp = new XMLHttpRequest();
	}else{// code for IE6, IE5
	    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlHttp.onreadystatechange = function(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
	    	document.getElementById("prog_area").innerHTML = xmlHttp.responseText;
		}
	}; //innerHTML
	
	// Specify HTTP GET by default and supply the relative url
	// 0 = list, 1 = menu
	xmlHttp.open("POST", "admin/ind_program_list.php", true);
	// Start a synchronous AJAX request and wait 
    // for the response
	xmlHttp.send(null);
}
function getCollagePrograms(collage_id, type){
	// Create an instance of the HTTP request object
	//alert(collage_id);
	var xmlHttp;
	if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
	    xmlHttp = new XMLHttpRequest();
	}else{// code for IE6, IE5
	    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlHttp.onreadystatechange = function(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
	    	document.getElementById("coll_prog_area").innerHTML = xmlHttp.responseText;
		}
	}; //innerHTML
	
	// Specify HTTP GET by default and supply the relative url
	// 0 = list, 1 = menu
	if (type == 0)
		xmlHttp.open("POST", "admin/collage_program_list.php?collage_id=" + collage_id, true);
	else xmlHttp.open("POST", "admin/collage_program_menu.php?collage_id=" + collage_id, true);
	// Start a synchronous AJAX request and wait 
    // for the response
	xmlHttp.send(null);
}