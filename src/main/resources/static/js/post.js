let index = {
	init : function(){
		$("#btn-save").on("click", ()=>{
			this.save();
		});
	},
	
	save : function(){
		let data = {
			content: $("#content").val(),
			hashtag: $("#hashtag").val()
		};

		$.ajax({
			type:"POST",
			url:"/api/post",
			data:JSON.stringify(data), 
			contentType:"application/json; charset=utf-8",
			dataType:"json" 
		}).done(function(resp){
			location.href = "/post";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	}
	
}

index.init();