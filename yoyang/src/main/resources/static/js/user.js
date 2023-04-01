let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		/*$("#btn-login").on("click", ()=>{ 
			this.login(); 
		});	*/
		$("#btn-update").on("click", () => {
			this.update();
		});
		$("#btn-roleupdate").on("click", () => {
			this.roleupdate();
		});
		$("#btn-connect").on("click", () => {
			this.connect();
		});
	},

	save: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			userEmail: $("#email").val(),
			userRealname: $("#realname").val(),
			userPhoneNumber: $("#phoneNumber").val()
		}

		$.ajax({
			type: "post",
			url: "/auth/joinProc",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			/*			beforeSend: function (jqXHR, settings) {
					   var header = $("meta[name='_csrf_header']").attr("content");
					   var token = $("meta[name='_csrf']").attr("content");
					   jqXHR.setRequestHeader(header, token);}*/
		}

		).done(function(resp) {
			console.log(resp.status);
			alert("회원가입 완료");
			location.href = "/";
		}).fail(function(error) {
			console.log(error.status);
			alert("회원가입 실패(중복된 아이디)");
		});
	},

	/*		login: function(){
			let data = {
				username: $("#username").val(),
				password: $("#password").val(),
			}
			$.ajax({
				type: "post",
				url: "/api/user/login",
				data: JSON.stringify(data), 
				contentType: "application/json; charset=utf-8", 
			}).done(function(resp){ 
				alert("로그인 완료");
				location.href="/";
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
		},*/
	update: function() {
		let data = {
			userNumber: $("#userNumber").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			userEmail: $("#email").val(),
			userRealname: $("#realname").val(),
			userPhoneNumber: $("#phoneNumber").val()
		}
		$.ajax({
			type: "put",
			url: "/user",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
		}).done(function(resp) {
			alert("회원수정 완료");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	connect: function() {
		let id = $("#branchId").val();
		$.ajax({
			type: "put",
			url: "/user/connectForm/" + id ,
			//data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
		}).done(function(resp) {
			alert("지점연결 완료");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

}

index.init();