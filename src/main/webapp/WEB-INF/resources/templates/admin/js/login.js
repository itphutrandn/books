$(document).ready(function() {
	if (localStorage.getItem("token") != null && localStorage.getItem("userInfo") ) {
		console.log(localStorage.getItem("token"));
		window.location = "/admincp/books/index";
		return;
	}
});

function handleLogin() {
	$("#wait").css("display", "block");
	var email = $("#email").val();
	var pwd = $("#pwd").val();
	var data = {
		"email" : email,
		"password": pwd
	}
	$.ajax({
		url: "/api/admin/login", 
		type: "POST",
		dataType : "json",
		contentType: "application/json; charset=utf-8",
		data : JSON.stringify(data),
		success: function(result){
			$("#wait").css("display", "none");
     	 	if(result.code == '200') {
     	 		console.log(result);
     	 		localStorage.setItem("token",result.data.access_token);
     	 		localStorage.setItem("userInfo",JSON.stringify(result.data));
     	 		window.location = "/admincp/books/index";
     	 		return;
     	 	} 
      	}, 
	    error: function(xhr, status, error) {
	    	$("#wait").css("display", "none");
	    	$("#loginFail").text(JSON.parse(xhr.responseText).text);
		}
	});
}