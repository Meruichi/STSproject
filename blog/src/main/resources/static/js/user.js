let index ={
	init: function(){
		$("#btn-save").on("click", ()=>{ // ()=>{} this를 바인딩하기위해서
			this.save();
		});
/*		$("#btn-login").on("click", ()=>{ // ()=>{} this를 바인딩하기위해서
			this.login();
		});	*/
		$("#btn-update").on("click", ()=>{ // ()=>{} this를 바인딩하기위해서
			this.update();
		});			
	},
	
	save: function(){
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		
		//console.log(data);
		
		// ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청(default가 비동기호출)

		$.ajax({
			// 회원가입 수행요청
			type: "post",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // json타입으로 변경
			contentType: "application/json; charset=utf-8", // body데이터타입(MIME)
			// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바오브젝트로 변환
			// 그래서 dataType: "json" 이 필요없게된듯	
		}).done(function(resp){ // resp : 응답의 결과(UserApiController의 save함수 return값)
			console.log(resp.status);
			alert("회원가입 완료");
			location.href="/";
		}).fail(function(error){
			console.log(error.status);
			alert("회원가입 실패(중복된 아이디)");
			// alert(JSON.stringify(error));
		});
		
		
		
		// 1.회원가입시 ajax이유 => 요청에 대한 응답은 html이 아닌 data(json)을 받기 위해서
		// 2.비동기통신을 하기 위해서
		// 동기통신 : 지연 없이 실시간으로(but 이전작업이 완료않으면 다음작업이 계속 대기해야함)
		// 비동기통신 : 통신간 시차가 있음(이전작업완료까지 시간이 더 걸리겠지만 그동안 다른작업을 할 수 있음
		// => 자원을 효율적으로 사용)
		// 동기(synchronous : 동시의, 동시에 일어나는)
	},
		
	/*	login: function(){
		//alert('user의 login함수 호출됨');
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
	}*/
	update: function(){
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		$.ajax({
			type: "put",
			url: "/user",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
		}).done(function(resp){
			alert("회원수정 완료");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	}

}

index.init();