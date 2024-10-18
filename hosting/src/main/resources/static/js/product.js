const form = document.querySelector("#product-form");

form.addEventListener("submit", async (e) =>{
	e.preventDefault();
	
	const fileInput = document.querySelector("#img-file");
	
	const fileName = fileInput.files[0].name;
	
	// 주소에는 공백같이 사용할 수 없는 문자들이 존재함
	// 그래서 그걸 대체하는 문자로 변환시켜야 하므로 encodeURIComponent 함수로 처리함
	const name = encodeURIComponent(fileName);
	
	//클라이언트가 이미지를 업로드 한다는 요청을 서버에게 함
	const presignedResponse = await fetch(`/presigned-url?filename=${name}`);
	//서버에서 클라이언트한테 업로드할 URL을 넘겨받음
	const presignedUrl = await presignedResponse.text();
	 
	//클라이언트가 받은 URL을 이용해서 버킷에 업로드 요청함
	const s3uploadResponse = await fetch(presignedUrl,{
				method: "PUT",
				body : fileInput.files[0]
			});	
			
	//버킷에서 이미지 저장이 끝나면 해당 이미지 URL을 클라이언트한테 되돌려줌 		
	const fileUrl = s3uploadResponse.url.split("?")[0];
	
	//console.log(fileUrl);
	/*fetch(`/presigned-url?filename=${name}`)
		.then((response) => response.text())
		.then((result) => {
			//console.log(result); 버킷에 올라갈 url이 들어있음
			//버킷에  업로드할 예정
			fetch(result, {
				method: "PUT",
				body : fileInput.files[0] //이미지 이름이아닌 진짜 이미지 주소를 넣어야함
			}).then((response) => {
				console.log(response);
				console.log(response.url.split("?")[0]);
				// 받아온 url뒤에 쿼리스트링이 붙기때문에 그걸 제외한 주소를 뽑음
			})
		}).catch((error)=>{
			console.log(error)
		});*/
		
	// 히든 인풋에 값을 넣기 위함	
	document.querySelector("#imgUrl").value = fileUrl;
	
	// 기존에는 기본동작막고 ajax로 보냈는데 form자체에 submit하면 서버로 날라감, form에 메소드 액션있어야함
	form.submit();
		
})
