/**
 * 
 */
 
 $().ready(function(){
	$("#submitBoard").on("click",function(){
		if(validate()){
			$("form[name=formBoardWrite]").submit();
		}
	});
});
//chk에 대해서 점검

function validate(){
	let flen = $("form[name=formBoardWrite] .chk").length;
	for(var i=0; i<flen; i++){
		if($('.chk').eq(i).val()=="" ||
			$('.chk').eq(i).val()==null||
			$('.chk').eq(i).val().trim()==""){
				alert($('.chk').eq(i).attr('title')+ '은/는 필수 입력');
				$('.chk').eq(i).focus();
				return false;
			}  
	}
	return true;
}