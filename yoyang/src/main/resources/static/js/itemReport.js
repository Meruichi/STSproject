let index ={
	init: function(){
		$("#btn-report").on("click", ()=>{
			this.report();
			this.update();
		});
		$("#btn-delete").on("click", ()=>{
			this.deleteById();
		});
	},
	report: function(){
		let id = $("#itemId").val();
		let data = {
			reportType: $("#reportType").val(),
			itemQuantityReport: $("#itemQuantityReport").val(),
			itemReportComment: $("#itemReportComment").val(),
		}
		$.ajax({
			type: "post",
			url: "/api/addReport/" + id,
			data: JSON.stringify(data), 
			contentType: "application/json; charset=utf-8",
		}).done(function(resp){
			alert("보고 완료되었습니다.");
			location.href="/item/itemManagementForm";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	update: function(){
		let id = $("#itemId").val();
		let data = {
			reportType: $("#reportType").val(),
			itemQuantityReport: $("#itemQuantityReport").val(),
			itemReportComment: $("#itemReportComment").val(),
		}
		$.ajax({
			type: "put",
			url: "/api/updateItem/" + id,
			data: JSON.stringify(data), 
			contentType: "application/json; charset=utf-8",
		}).done(function(resp){
			alert("발주/소모처리 완료되었습니다.");
			location.href="/item/itemManagementForm";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	deleteById: function(){
		let id = $("#btn-delete").val();
		$.ajax({
			type: "delete",
			url: "/api/deleteItemReport/" + id,
		}).done(function(resp){
			alert("삭제 완료");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
}

index.init();