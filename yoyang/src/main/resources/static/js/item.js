let index ={
	init: function(){
		$("#btn-add").on("click", ()=>{
			this.add();
		});
		$("#btn-delete").on("click", ()=>{
			this.deleteById();
		});
	},
	
	add: function(){
		let data = {
			itemReportCategory: $("#itemReportCategory").val(),
			itemName: $("#itemName").val(),
			itemListComment: $("#itemListComment").val()
		}
		
		$.ajax({
			type: "post",
			url: "/api/addItem",
			data: JSON.stringify(data), 
			contentType: "application/json; charset=utf-8",
		}).done(function(resp){
			alert("아이템 등록이 완료되었습니다.");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	deleteById: function(){
		let id = $("#btn-delete").val();
		$.ajax({
			type: "delete",
			url: "/api/deleteItem/" + id,
		}).done(function(resp){
			alert("삭제 완료");
			location.href="/";
		}).fail(function(error){
			alert("먼저 발주/소모 보고를 삭제해주세요");
			//alert(JSON.stringify(error));
		});
	},
}

index.init();