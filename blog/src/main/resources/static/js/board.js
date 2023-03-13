let index ={
	init: function(){
		$("#btn-save").on("click", ()=>{ // ()=>{} this를 바인딩하기위해서
			this.save();
		});
		$("#btn-delete").on("click", ()=>{
			this.deleteById();
		});
		$("#btn-update").on("click", ()=>{
			this.update();
		});
		$("#btn-reply-save").on("click", ()=>{
			this.replySave();
		});
	},
	
	save: function(){
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}
		
		$.ajax({
			type: "post",
			url: "/api/board",
			data: JSON.stringify(data), 
			contentType: "application/json; charset=utf-8",
		}).done(function(resp){
			alert("글 등록이 완료되었습니다.");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	deleteById: function(){
		let id = $("#id").text();
		
		$.ajax({
			type: "delete",
			url: "/api/board/" + id,
		}).done(function(resp){
			alert("삭제 완료");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	update: function(){
		let id = $("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		$.ajax({
			type: "put",
			url: "/api/board/" + id,
			data: JSON.stringify(data), 
			contentType: "application/json; charset=utf-8",
		}).done(function(resp){
			alert("수정 완료");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
		replySave: function(){
		let data = {
			userId: $('#userId').val(),
			boardId: $('#boardId').val(),
			content: $("#reply-content").val()
		}
		
		$.ajax({
			type: "post",
			url: `/api/board/${data.boardId}/reply`,
			data: JSON.stringify(data), 
			contentType: "application/json; charset=utf-8",
		}).done(function(resp){
			alert("댓글 작성 완료");
			location.href=`/board/${data.boardId}`;
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	
		replyDelete: function(boardId, replyId){
		$.ajax({
			type: "delete",
			url: `/api/board/${boardId}/reply/${replyId}`,
		}).done(function(resp){
			alert("댓글 삭제 완료");
			location.href=`/board/${boardId}`;
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	}
}

index.init();