	
$().ready(function(){
	
	//아이디 찾기
	$(".noticeUp").on("click",function(){
		$("form[name=noticeGenerate]").attr('action','/noticeUpForm');
		$("form[name=noticeGenerate]").submit();
	});
	$(".noticeDel").on("click",function(){
		$("form[name=noticeGenerate]").attr('action','/noticeProc?flag=delete');
		$("form[name=noticeGenerate]").submit();
	});
	
	$(".noticeUpProc").on("click",function(){
		$("form[name=noticeForm]").attr('action','/noticeProc?flag=delete');
		$("form[name=noticeForm]").submit();
	})
})