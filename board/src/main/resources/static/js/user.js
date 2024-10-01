const userObject = {
	init:function(){
		$('#btn-save').on('click',() => {
			this.insertUser();
			// on click 옆에 화살표 함수 말고 function으로 하면 안됐음
		});
	},
	insertUser:function(){
		alert("회원가입요청");
		//회원가입페이지에 입력한 내용을 서버로 전송해서
		// 회원가입처리가 되도록 코드를 구현할 예정
	}
}

userObject.init();

