/**
 * 
 */
 $().ready(function(){
		let f = $('form[name=form1]');
		$("#update").on("click", function(){
			if(checkpw()){
				f.attr("action","update");
				f.submit();
			} else 
				alert("비밀번호가 틀렸습니다.");
		})
		$("#delete").on("click", function(){
			if(checkpw()){
				if(confirm("정말 삭제하시겠습니까?"))
				f.attr("action","delete")
				f.submit();
			} else
				alert("비밀번호가 틀렸습니다.");
		})
		
		$("#reply").on("click", function(){
			f.attr('action','writeForm');
			f.submit();
		})
	})
	
	function checkpw(){
		let upw = prompt("비밀번호를 입력하시오.");
		let cpw = $('input[name=passwd]').val();
		if(upw==cpw)
			return true;
		return false;
	}