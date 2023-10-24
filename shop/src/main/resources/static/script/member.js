$().ready(function(){
	
})

function updates(flag){
	openWin = window.open("/pwCheck","pwCheck",
		"width=400, height=150, toolbar=no, location=no, menubar=no, resizable = no, scrollbars = no");
	if(flag=='u'){
		$("#upForm").attr("action","memUpForm");
	} else if(flag=='d'){
		$("#upForm").attr("action","memDelete");
	}
}

function setParentText(){
	let pw1 = $('#pwck').val(); //popup에서 받은 passwd
	if(pw1==null || pw1.length==0){
		alert("패스워드를 입력하시오");
		$('#pwck').focus();
		return false;
	}
	
	let pw2 = $("#pw", opener.document).val(); //opener에서 받은 passwd
	if(pw1==pw2){
		$("#upForm", opener.document).submit();
		this.window.close();
	} else {
		alert('비밀번호 오류');
		this.window.close();
	}
}
