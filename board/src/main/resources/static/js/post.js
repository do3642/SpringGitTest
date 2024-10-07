$(document).ready(function(){
	//$("#content").summernote(); // #content = textarea 아이디
	$("#content").summernote({
		height:400
	});
});


/*const postObject ={
	init: function(){
		$("#btn-insert").on("click",(e)=>{
			this.insertPost();
			e.preventDefault();
		});
	
		
	},
	insertPost: ()=>{
		//아작스 부분
		
		let post = {
			title : $("#title").val(),
			content : $("#content").val(),
			cnt : 0,
		};
		
		$.ajax({
			type: "POST",
			url: "/post",
			data: JSON.stringify(post),
			contentType: "application/json; charset=utf-8"
		}).done(function(response){ // 실행성공 했을 때
			 console.log('1');
			alert(response.data);
			if(response.status == 200) 
			location.href = "/"; //성공 후 메인페이지로
			}).fail(function(){
				//console.log(error);
			});
				
		
	}
}

postObject.init();*/

//선생님 insertPost JS
const postObject ={
	init: function(){
		$("#btn-insert").on("click",(e)=>{
			e.preventDefault();
			this.insertPost();
		});
		$("#btn-update").on("click",(e)=>{
			e.preventDefault();
			this.updatePost();
		});
		$("#btn-delete").on("click",(e)=>{
			e.preventDefault();
			this.deletePost();
		});
		
	},
	
	insertPost: ()=>{
		//아작스 부분
		
		const post = {
			title : $("#title").val(),
			content : $("#content").val()
		};
		
		$.ajax({
			type: "POST",
			url: "/post",
			data: JSON.stringify(post),
			contentType: "application/json; charset=utf-8"
		}).done(function(response){ // 실행성공 했을 때
			alert(response.data);
			if(response.status == 200) 
			location.href = "/"; //성공 후 메인페이지로
			}).fail(function(error){
				console.log(error);
			});
				
		
	},
	updatePost: ()=>{
		if(!confirm("수정하시겠습니까?"))
			return;
		const updatePost = {
			id : $("#id").val(),
			title : $("#title").val(),
			content : $("#content").val()
		};
		
		$.ajax({
		type: "PUT",
		url: "/post",
		data: JSON.stringify(updatePost),
		contentType: "application/json; charset=utf-8"
		}).done(function(response){ // 실행성공 했을 때
			alert(response.data);
			if(response.status == 200) 
				location.href = "/post/" + updatePost.id; //성공 후 메인페이지로
		}).fail(function(error){
			console.log(error);
		});
	},
	deletePost: ()=>{
			if(!confirm("삭제하시겠습니까?"))
				return;
			const id = $("#id").text();
			
			$.ajax({
			type: "DELETE",
			url: `/post/${id}`
			}).done(function(response){ // 실행성공 했을 때
				alert(response.data);
				if(response.status == 200) 
					location.href = "/"; //성공 후 메인페이지로
			}).fail(function(error){
				console.log(error);
			});
		}
	
}

postObject.init();
