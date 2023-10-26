/**
 * 
 */
$().ready(function(){
  var plen = $('.price').length;
 	for(var i=0; i<plen; i++){
	 var p=$('.price').eq(i).text();
	 var to_p = 
	     p.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g,",");
	 $('.price').eq(i).text(to_p);
   }
   
   //주문 수량보다 재고 수량이 부족할 시
	$('.cartAdd').on('click',function(){
		let q = $('input[name=quantity]').val();
		let s = $('input[name=stock]').val();
		if(!$.isNumeric(q)){
			alert("숫자로 입력하시오.");
			$('input[name=quantity]').focus();
			return false;
		}
		
		if(parseInt(q)>parseInt(s)){
			alert("재고가 부족합니다.");
			return false;
		} else {
			$('form[name=formCart]').submit();
		}
	})

	$(".submit1").on("click",function(){
		$("form[name=updateOrder").submit();
	})

	$('.productUp').on('click', function(){
		$('form[name=formCart]').attr('action','productUpForm');
		$('form[name=formCart]').submit();
	})

	$('.productDel').on('click',function(){
		pno = $('input[name=p_no]').val();
		$.ajax({
			async:true,
			type:'post',
			data:{"p_no":pno},
			url:'orderCntOfProduct',
			dataType:"json",
			success:function(cnt){
				if(cnt>0){
					alert("주문내역이 존재합니다. \n삭제 불가.");
					return false;
				} else {
					r = confirm("정말 삭제하시겠습니까?");
					if(r){
						$('form[name=formCart]').attr('action','/productDel');
						$('form[name=formCart]').submit();						
					} else {
						return false;
					}
				}
			}
		});
	})

	//체크박스
	$(".selectBtn").click(function(){
//		let rowData = new Array();
		let tdArr = new Array();
		let checkbox = $("input[name=ck]:checked");
		
		checkbox.each(function(i){
			let tr = checkbox.parent().parent().eq(i);
			let td = tr.children();
//			rowData.push(tr.text());
			let pno = tr.find(td).find("input[name=p_no]").val();
			let ono = tr.find(td).find("input[name=o_no]").val();
			let memid = $('.acl2 input[name=mem_id]').val();
			let state = $('select[name=state]').val();
			tdArr.push("o_no:"+ono);
			tdArr.push("p_no:"+pno);
			tdArr.push("mem_id:"+memid);
			tdArr.push("state:"+state);
		});
		
		$.ajax({async:true,
		type:'post',
		data:{
			tdArr
		},
		url:"/orderMgtProc",
		dataType:"json",
		success:function(){
			document.location.href="/orderMgt"
		}
		});
		
	})

}) //ready end
	
function cartUpdate(f, obj){
	let url1;
	let quantity = $(obj).closest('tr').find('input[name=quantity]').val();
	let stock = $(obj).closest('tr').find('input[name=stock]').val();
	let pno = $(obj).closest('tr').find('input[name=p_no]').val();
	if(f=='D'){
		url1='cartProc?flag=delete';
		$(obj).closest('tr').remove();
		
	} else if(f=='U'){
		if(parseInt(quantity)>parseInt(stock)){
			alert("재고가 부족합니다.");
			return false;
		} else {
			alert('장바구니 수정');
			url1 = 'cartProc?flag=update';				
		}
	}
	
	$.ajax({
		async: true,
		type: 'post',
		data: {
			"quantity":quantity,
			"p_no": pno
		},
		url: url1,
		dataType: "json",
		success: function(){
			alert('장바구니 수정');
		}
	});
}

function orderDetail(obj){
	let ono = $(obj).closest('td').find('input[name=o_no]').val();
	let pno = $(obj).closest('td').find('input[name=p_no]').val();
	let memId = $(obj).closest('td').find('input[name=mem_id]').val();
	
	$('form[name=orform] input[name=o_no]').val(ono);
	$('form[name=orform] input[name=p_no]').val(pno);
	$('form[name=orform] input[name=mem_id]').val(memId);
	$('form[name=orform]').submit();
	
}