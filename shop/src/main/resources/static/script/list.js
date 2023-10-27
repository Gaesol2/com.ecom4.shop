/**
 * 
 */
 $().ready(function(){
		let $item = $('a.content').on("click",function(){
			let idx = $item.index(this);
			//alert($("a.content input[type=hidden]").eq(idx).val());
			
			var f = $('form[name=content]')
			$('form input[name=bno]').attr('value', $("a.content input[name=no]").eq(idx).val());
			f.attr('action','content');
			f.submit();
		});
	})