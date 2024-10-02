const userObject = {
	init:function(){
		$('#btn-save').on('click',(e) => {
			this.insertUser();
			// on click 옆에 화살표 함수 말고 function으로 하면 안됐음
			e.preventDefault(); // 스크립트로 값을 받아 보내줄거기 때문에 sumit의 자체 보내는 기능 정지
		});
	},
	insertUser:function(){
		alert("회원가입요청");
		//회원가입페이지에 입력한 내용을 서버로 전송해서
		// 회원가입처리가 되도록 코드를 구현할 예정
		
		//회원가입 시 입력한 정보를 추출
		let user = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		// console.log(user);
			
		$.ajax({
			type: "POST", // 소문자 상관없음
			url: "/auth/insertuser",
			data: JSON.stringify(user),
			contentType: "application/json; charset=utf-8"
		}).done(function(response){ // 실행성공 했을 때 
			alert(response.data);
			
			if(response.status == 200) //중복으로 실패해도 메인페이지로 가서 막아줌
				location.href = "/"; //성공 후 메인페이지로
		}).fail(function(error){
			console.log(error);
		});
		
		
	}
}

userObject.init();


/*$('#btn-update').on('click',(e)=>{
	
	e.preventDefault();
	if(!confirm("회원 정보를 수정하시겠습니까?"))
		return;
	
	let user = {
		password: $("#password").val(),
		email: $("#email").val()
	};
	
	$.ajax({
		type: "POST", // 소문자 상관없음
		url: "/auth/update",
		data: JSON.stringify(user),
		contentType: "application/json; charset=utf-8"
	}).done(function(response){ // 실행성공 했을 때 
			alert(response.data);
			if(response.status == 200) //중복으로 실패해도 메인페이지로 가서 막아줌
				location.href = "/"; //성공 후 메인페이지로
			}).fail(function(error){
				console.log(error);
			});
	
});*/





$('#btn-update').on('click',(e)=>{
	
	e.preventDefault();
	if(!confirm("회원 정보를 수정하시겠습니까?"))
		return;
	
	let user = {
		id:$("#id").val(),
		password: $("#password").val(),
		email: $("#email").val()
	};
	
	$.ajax({
		type: "PUT", // 소문자 상관없음
		url: "/auth/update",
		data: JSON.stringify(user),
		contentType: "application/json; charset=utf-8"
	}).done(function(response){ // 실행성공 했을 때 
			alert(response.data);
			if(response.status == 200) //중복으로 실패해도 메인페이지로 가서 막아줌
				location.href = "/"; //성공 후 메인페이지로
			}).fail(function(error){
				console.log(error);
			});
	
});

$("#btn-delete").on('click',(e)=>{
	e.preventDefault();
		if(!confirm("회원 탈퇴 하시겠습니까?"))
			return;
		
		let user = $("#id").val();
		console.log(user);
			
		$.ajax({
			type: "Delete",
			url: "/auth/delete",
			data: JSON.stringify(user),
			/*data:user,*/
			contentType: "application/json; charset=utf-8"
		}).done(function(){
			
			location.href = "/";
		}).fail(function(error){
			console.log(error);
		});
});