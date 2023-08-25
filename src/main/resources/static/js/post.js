let index = {
	init : function(){
		$("#btn-save").on("click", ()=>{
			this.save();
		});
		
		$("#btn-edit").on("click", ()=>{
			this.edit();
		});
		
		$("#btn-reply").on("click", ()=>{
			this.reply();
		});
		
		const filesInput = document.getElementById('filesInput');
		const previewContainer = document.getElementById('previewContainer');
		const dataTransfer = new DataTransfer();
		
		$('#filesInput').change(function(event){
			const fileArr = filesInput.files;
			
			if(fileArr != null && fileArr.length > 0){
		         for(var i=0; i<fileArr.length; i++){
		             dataTransfer.items.add(fileArr[i])
		         }
		         filesInput.files = dataTransfer.files;         
		     }
			
			for (let i = 0; i < fileArr.length; i++) {
		        const file = fileArr[i];
		
		        if (file.type.startsWith('image/')) {
		            const reader = new FileReader();
		
		            reader.onload = (e) => {
		                const imgSpan = document.createElement('span');
		                const buttonX = document.createElement('button');
		                const img = document.createElement('img');
		                
		                img.src = e.target.result;
		                img.className = 'preview-image';
		                
		                buttonX.type = 'button';
		                buttonX.innerHTML = 'X';
		                buttonX.className = 'btnX';
		                buttonX.addEventListener("click",() => {
		                	// 코드 정리 필요
			               	const files = Array.from(filesInput.files)
		                	
			               	for(let k=0; k<dataTransfer.items.length; k++){
		
			               		if(dataTransfer.items[k].getAsFile() === file){
			               			dataTransfer.items.remove(k);
			               			break;
			               		}	
			               	}
		                	
		               	  	filesInput.files = dataTransfer.files;
		               	  	
		               		imgSpan.remove();
		                })
		                
		                imgSpan.appendChild(img);
		                imgSpan.appendChild(buttonX);
		                previewContainer.appendChild(imgSpan);
		            };
		
		            reader.readAsDataURL(file);
		        }
			}
		});
	},
	
	save : function(){
		let data = {
			content: $("#content").val()
		};
		
		$.ajax({
            type: "POST",
            url:"/api/post",
			data:JSON.stringify(data), 
			contentType:"application/json; charset=utf-8",
			dataType:"json",
			async: false,
			success: function(res){
				saveFile(res);
				location.href = "/post";
			},
			error: function(error){
				alert(JSON.stringify(error));
			}
		});
	},
	
	edit : function(){
		const id = $("#postId").val();
		
		const data = {
			content: $("#content").val()
		}
		
		$.ajax({
			type: "PUT",
			url: "/api/post/" + id,
			data: JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json",
			success: function(res){
				saveFile(res, "Edit");
				window.location.href = "/post/view/" + id;
			},
			error: function(error){
				alert(JSON.stringify(error));
			}
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
			dataType:"json",
			success:function(){
				location.href = "/post/view/" + id;
			} ,
			error:function(error){
				alert(JSON.stringify(error));
			}
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

function deletePost(e, id, type="ALL") {
	const modal = $('#chzModal');
	const span = $(".close");
	const yesButton = document.getElementById('yesButton');
	const noButton = document.getElementById('noButton');
	     
	modal.show();
	
	yesButton.addEventListener('click', () => {

		$.ajax({
			type:"PUT",
			url: (type == "File" ? "/api/file/delete/" : "/api/post/delete/") + id,
			success: function(){
			if(type == "File"){
				var imageSpan = e.parentNode;
				imageSpan.remove();
			}else{
				location.href = "/post";	
			}},
			error:function(error){
				alert(JSON.stringify(error));
			}
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

function deleteReply(id) {
	const postId = $('#postId').val();
	
	const modal = $('#chzModal');
	const span = $(".close");
	const yesButton = document.getElementById('yesButton');
	const noButton = document.getElementById('noButton');
	     
	modal.show();
	
	yesButton.addEventListener('click', () => {

		$.ajax({
			type:"PUT",
			url: "/api/reply/delete/" + id,
			success: function(){
				location.href = "/post/view/" + postId;
			;},
			error:function(error){
				alert(JSON.stringify(error));
			}
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

function saveFile(res){
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
		    url: "/api/fileUpload",
		    type: "POST",
		    data: formData,
		    contentType: false,
		    processData: false,
		    async: false,
		    success: function(res2) {
		      currentChunk++;
		
		      if (currentChunk < totalChunks) {
		        uploadFileChunk(file, currentChunk, totalChunks);
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
}

function editReply(e){
	var btnSpan = e.parentNode;

	var replyDiv = btnSpan.parentNode;
	
	var replySpan = replyDiv.children[0];

	var replyText = replySpan.children[1];
	var replyContent = replySpan.children[2];
	
	var replybtnSpan = replySpan.children[3];

	replyText.style.display = 'none';
	
	replyContent.value = replyText.innerHTML;
	
	replyContent.style.display = 'inline';
	replybtnSpan.style.display = 'inline';
}

function editDisplay(e){
	var replybtnSpan = e.parentNode;
	
	var replySpan = replybtnSpan.parentNode;
	
	var replyText = replySpan.children[1];
	var replyContent = replySpan.children[2];
	
	replyText.style.display = 'inline';
	
	replyContent.style.display = 'none';
	replybtnSpan.style.display = 'none';

}

function updateReply(e, id, postId){
	var replybtnSpan = e.parentNode;
	
	var replySpan = replybtnSpan.parentNode;
	
	var replyContent = replySpan.children[2];
	
	let data = {
		content : replyContent.value
	}
	
	$.ajax({
		url: "/api/reply/" + id,
		type: "PUT",
		data:JSON.stringify(data), 
		contentType:"application/json; charset=utf-8",
		dataType:"json",
		success: function(){
			location.href = "/post/view/" + postId;
		},
		error: function(){
			alert('error');
		}
	});
}

function displayRereply(e){
	var btnSpan = e.parentNode;

	var replyDiv = btnSpan.parentNode;
	
	var topDiv = replyDiv.parentNode;
	
	var rereplyDiv = topDiv.children[1];
	
	rereplyDiv.style.display = ( rereplyDiv.style.display == "none" ? "block" : "none");
}

function createRereply(e, id, postId){
	var btnSpan = e.parentNode;
	
	var replyDiv = btnSpan.parentNode;
	
	var rereplyContent = replyDiv.children[0];

	let data ={
		content: rereplyContent.value
	};
	
	$.ajax({
		url: "/api/rereply/" + id,
		type: "POST",
		data:JSON.stringify(data), 
		contentType:"application/json; charset=utf-8",
		dataType:"json",
		success: function(){
			location.href = "/post/view/" + postId;
		},
		error: function(){
			alert('error');
		}
	});
}
