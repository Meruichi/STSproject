let index ={
	init: function(){
		$("#btn-add").on("click", ()=>{
			this.add();
		});

	},
	
	add: function(){
		let data = {
			branchName: $("#branchName").val(),
			branchAddress: $("#branchPostcode").val()+", "+$("#branchBasicAddress").val()+" "+$("#branchDetailAddress").val()+$("#branchExtraAddress").val(),
			branchDirectNumber: $("#branchDirectNumber").val()
		}
		$.ajax({
			type: "post",
			url: "/api/branch",
			data: JSON.stringify(data), 
			contentType: "application/json; charset=utf-8",
		}).done(function(resp){
			alert("지점 등록이 완료되었습니다.");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
}

index.init();