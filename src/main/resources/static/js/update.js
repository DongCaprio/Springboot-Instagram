// (1) 회원정보 수정
function update(userId, event) {
	event.preventDefault(); //폼태그 액션 막기
	let data1 = $("#profileUpdate").serialize();
	
	$.ajax({
		type: "put",
		url : `/api/user/${userId}`,
		data: data1,
		dataType : "json"
	}).done(res=>{
		console.log("update 성공", res);
		location.href=`/user/${userId}`
	}).fail(error=>{
		console.log("update 실패", error);
	});
}