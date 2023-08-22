let index = {
	init : function(){
		$("#btn-save").on("click", ()=>{
			this.save();
		});
		
		$("#btn-reply").on("click", ()=>{
			this.reply();
		});
	},
	
	save : function(){
		let data = {
			content: $("#content").val()
		};
		
		$.ajax({
            type: "POST",
            url:"/api/post2",
			data:JSON.stringify(data), 
			contentType:"application/json; charset=utf-8",
			dataType:"json",
			async: false 
		}).done(function(res){
			//코드 정리 필요
			const filesInput = document.getElementById("filesInput");
			const files = filesInput.files;
			
			function uploadFileChunk(file, currentChunk, totalChunks) {
			  const chunkSize = 1024 * 1024; // 1MB 청크 크기
			  const start = currentChunk * chunkSize;
			  const end = Math.min(start + chunkSize, file.size);
			
			  const chunk = file.slice(start, end);
			  const formData = new FormData();
			  formData.append("fileChunk", chunk);
			  formData.append("totalChunks", totalChunks);
			  formData.append("currentChunk", currentChunk);
			  formData.append("file", file);
			  formData.append("content", $("#content").val());
			  formData.append("postId", res.data.id)
			
			  $.ajax({
				    url: "/api/post",
				    type: "POST",
				    data: formData,
				    contentType: false,
				    processData: false,
				    async: false,
				    success: function(res) {
				      currentChunk++;
				
				      if (currentChunk < totalChunks) {
				        uploadFileChunk(file, currentChunk, totalChunks);
				      } else {
				        console.log("Upload Complete");
				        location.href = "/post";
				      }
				    },
				    error:function() {
				      alert('error');
				    } 
				  });
			  }
			
			  for (let i = 0; i < files.length; i++) {
			    const file = files[i];
			    
			    const totalChunks = Math.ceil(file.size / (1024 * 1024)); // 전체 청크 수 계산
			    uploadFileChunk(file, 0, totalChunks);
			  }
		}).fail(function(error){
			alert(JSON.stringify(error));
		});

		
	

	},
	
	reply : function(){
		const id = $("#postId").val()
		
		let data = {
			content: $("#reply-content").val()
		};
		
		$.ajax({
            type: "POST",
            url:"/api/reply/" + id,
			data:JSON.stringify(data), 
			contentType:"application/json; charset=utf-8",
			dataType:"json" 
		}).done(function(resp){
			location.href = "/post/view/" + id;
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	}
	
}

index.init();

function directView(id){
	location.href = "/post/view/" + id;
}

function updatePost(id) {
    location.href = "/post/form/" + id;
}

function deletePost(id) {
	const modal = $('#chzModal');
	const span = $(".close");
	const yesButton = document.getElementById('yesButton');
	const noButton = document.getElementById('noButton');

	modal.show();
	
	yesButton.addEventListener('click', () => {

		$.ajax({
			type:"PUT",
			url:"/api/post/delete/" + id,
			dataType:"json" 
		}).done(function(resp){
			location.href = "/post";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	
	    modal.hide();
	});
	
    span.on('click', function() {
        modal.hide();
    });
	
	noButton.addEventListener('click', () => {
	   modal.hide();
	});

}

