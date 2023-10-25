/**
 * 쇼핑몰의 공통스크립트
 */

 $().ready(function(){
	 let idchk;
	 let pcheck = true;   // 두 개가 다르면 true, 같으면 false
	 
	 //aside 로그인
	$("#submitTop").on("click",function(){
		let flen = $("form[name=topForm] .chkt").length;
	for(var i=0; i<flen; i++){
		if($('.chkt').eq(i).val()=="" ||
			$('.chkt').eq(i).val()==null||
			$('.chkt').eq(i).val().trim()==""){
				alert($('.chkt').eq(i).attr('title')+ '를 입력하시오.');
				$('.chkt').eq(i).focus();
				return false;
			}  
	}
	$("form[name=topForm]").submit();
	})

	//아이디 찾기
	$("#submitSearch").on("click",function(){
		let flen = $("form[name=searchForm] .chk").length;
	for(var i=0; i<flen; i++){
		if($('.chk').eq(i).val()=="" ||
			$('.chk').eq(i).val()==null||
			$('.chk').eq(i).val().trim()==""){
				alert($('.chk').eq(i).attr('title')+ '를 입력하시오.');
				$('.chk').eq(i).focus();
				return false;
			}  
	}
	$("form[name=searchForm]").submit();
	})
	 
	 //비밀번호 변경
	 $("#submitUpdatePw").on("click",function(){
		let flen = $("form[name=updatePwForm] .chk").length;
	for(var i=0; i<flen; i++){
		if($('.chk').eq(i).val()=="" ||
			$('.chk').eq(i).val()==null||
			$('.chk').eq(i).val().trim()==""){
				alert($('.chk').eq(i).attr('title')+ '를 입력하시오.');
				$('.chk').eq(i).focus();
				return false;
			}  
	}
	$("form[name=updatePwForm]").submit();
	})

	 //
	 $("#submitInForm").on("click",function(){
		let flen = $("form[name=formInForm] .chk").length;
	for(var i=0; i<flen; i++){
		if($('.chk').eq(i).val()=="" ||
			$('.chk').eq(i).val()==null||
			$('.chk').eq(i).val().trim()==""){
				alert($('.chk').eq(i).attr('title')+ '를 입력하시오.');
				$('.chk').eq(i).focus();
				return false;
			}  
	}
	$("form[name=formInForm]").submit();
	})
	
	/*
	//장바구니
	 $("#submitCart").on("click",function(){
		let flen = $("form[name=formCart] .chk").length;
	for(var i=0; i<flen; i++){
		if($('.chk').eq(i).val()=="" ||
			$('.chk').eq(i).val()==null||
			$('.chk').eq(i).val().trim()==""){
				alert($('.chk').eq(i).attr('title')+ '를 입력하시오.');
				$('.chk').eq(i).focus();
				return false;
			}  
	}
	$("form[name=formCart]").submit();
	})
	 */
	
	
	 //join 로그인
	$("#submit11").on("click",function(){
		if(validate()){
			if(idchk==1){
				alert('아이디 중복\n다시 정하세요.');
				$("#idchk").focus();
				return false;
			}
			if(pcheck){
				alert("비밀번호가 다릅니다.");
				return false;
			}
			$("form[name=form1]").submit();
		}
	});
	
	
		//공지사항
	$(".submitNotice").on("click",function(){
		let flen = $("form[name=noticeGenerate] .chk").length;
	for(var i=0; i<flen; i++){
		if($('.chk').eq(i).val()=="" ||
			$('.chk').eq(i).val()==null||
			$('.chk').eq(i).val().trim()==""){
				alert($('.chk').eq(i).attr('title')+ '를 입력하시오.');
				$('.chk').eq(i).focus();
				return false;
			}  
	}
	$("form[name=noticeGenerate]").submit();
	})
	
	
	$("#idchk").on('propertychange change input paste',function(){
		$.ajax({
			async: true,
			type: 'post',
			url: 'idCheck',
			data:{'mem_id':$('#idchk').val()},
			dataType: "json",
			success: function(data){
				if(data>0){
					//이미 존재하는 아이디
					//submit 불가
					$('font[id=warning]').text('');
					$('font[id=warning]').attr('color','red');
					$('font[id=warning]').text('이미 존재하는 아이디입니다.');
					$('#idchk').focus();
					idchk = 1; 
				} else {
					//사용 가능한 아이디
					//submit 가능
					$('font[id=warning]').text('');
					$('font[id=warning]').attr('color','blue');
					$('font[id=warning]').text('사용가능한 아이디입니다.');
					$('#idchk').focus();
					idchk = 0;
				}
			}
		});
	})

	$("#check1, #check2").keyup(function(){
		$('font[id=check]').text('');
		
		if($('#check1').val()!=$('#check2').val()){
			$('font[id=check]').text('');
			$('font[id=check]').attr('color','red');
			$('font[id=check]').text('비밀번호 다름');
			pcheck = true;
		} else {
			$('font[id=check]').text('');
			$('font[id=check]').attr('color','blue');
			$('font[id=check]').text('비밀번호 같음');
			pcheck = false;
		}
	})

//장바구니
	$('.aclickCart').on("click",function(){
		let on_off = $("input[name=keyonoff]").val();
		if(on_off=='off'){
			alert('로그인이 필요합니다.')
			location.href="/login";
		} else if(on_off=='on'){
			location.href="/cartList";
		}
	})

//구매 목록
	$('.aclickOrder').on("click",function(){
		let on_off = $("input[name=keyonoff]").val();
		if(on_off=='off'){
			alert('로그인이 필요합니다.')
			location.href="/login";
		} else if(on_off=='on'){
			location.href="/orderList";
		}
	})



}); //ready end



function shopDetail(no){
	alert(no);
	$("#no").val(no);
	document.detail.submit();
}


function validate(){
	let flen = $("form[name=form1] .chk").length;
	for(var i=0; i<flen; i++){
		if($('.chk').eq(i).val()=="" ||
			$('.chk').eq(i).val()==null||
			$('.chk').eq(i).val().trim()==""){
				alert($('.chk').eq(i).attr('title')+ '은/는 필수 입력');
				$('.chk').eq(i).focus();
				return false;
			}  
	}
	
	let str = document.form1.m_email.value;
	let atPos = str.indexOf('@');
	let atLastPos = str.lastIndexOf('@');
	let dotPos = str.indexOf('.');
	let spacePos = str.indexOf(' ');
	let commaPos = str.indexOf(',');
	let eMailSize = str.length;
	if(!(atPos>1 && atPos==atLastPos && dotPos>3 && spacePos==-1 && commaPos==-1 && atPos+1<dotPos && dotPos+1<eMailSize)){
		alert("Email 주소 형식이 잘못되었습니다.\n다시 입력해주세요.");
		document.form1.m_email.focus();	
		return false;
	}
	return true;
}