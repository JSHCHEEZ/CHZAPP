<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container">
	<div class="timeline">
	   	<div class="timeline-item">
	        <div class="timeline-content">
	            <p>${post.content}</p>
	            <c:forEach var="efile" items="${post.efiles}">
	            	<img src="/image/${efile.efileName}" class="preview-image">
	            </c:forEach>
	        </div>
	        <span class="timeline-date">${post.createDate}</span>
	    </div>
	</div>
	
	<hr/>
	
	<form>
		<input type="hidden" id="postId" value="${post.id}"> 
		<textarea id="reply-content" class="reply-textarea" placeholder="댓글을 작성하세요"></textarea>
		<div class="btn-right-container">
			<button id="btn-reply">댓글작성</button>
		</div>
	</form>
	<br>
	<br>
	<c:forEach var="reply" items="${post.replys}">
   		<div>
	   		<div class="reply-list">
			    <span class="reply">
			      <span class="reply-author">None</span>
			      <span class="reply-text">${reply.content}</span>
			      <textarea class="reply-content">${reply.content}</textarea>
			      <span class="btn-reply-span">
			      	<button type="button" onclick = "updateReply(this, ${reply.id}, ${post.id})">완료</button>
			      	<button type="button" onclick = "editDisplay(this)">취소</button>
			      </span>
			    </span>
			    <span class="btn-right-container">
			    	<button type="button" onclick="displayRereply(this)">답글</button>
			    	<button type="button" onclick="editReply(this)">수정</button>
			    	<button type="button" onclick="deleteReply(${reply.id})">삭제</button>
			    </span>
			</div> 
			<div class="reply-reply">
				<textarea class="reply-reply-content" placeholder="답글을 작성하세요"></textarea>
			    <span class="btn-right-container">
			    	<button type="button" onclick="createRereply(this,${reply.id}, ${post.id})">등록</button>
			    </span>
			</div>
			<c:forEach var="rereply" items="${reply.rereplys}">
				<div>${rereply.content}</div>
			</c:forEach>
		</div>
	</c:forEach>

</div>

<!-- Modal -->
<div id="chzModal" class="modal">
    <div class="modal-content animate-top">
        <div class="modal-header">
            <h5 class="modal-title">삭제</h5>
            <button type="button" class="close">
                <span aria-hidden="true">x</span>
            </button>
        </div>
        <div class="modal-body">
            <p>댓글을 삭제하시겠습니까?</p>
        </div>
        <div class="modal-footer">
		    <button type="button" id="yesButton" class="btn btn-primary">YES</button>
	        <button type="button" id="noButton" class="btn btn-secondary" data-bs-dismiss="modal">NO</button>
        </div>
    </div>
</div>
	

<style>
.reply-list{
	width: 100%;
	height: 60px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

.reply-author{
	color: #bbb;
	font-size: small;
	font-weight: bold;
}

.reply-content{
	display:none;
	resize: none;
}

.btn-reply-span{
	display:none;
}

.reply-reply{
	display: none;
}

.reply-reply-content{
	width: 95%;
	height: 45px;
	resize: none;
}


</style>

<link href="<c:url value='/css/chzapp.css' />" rel="stylesheet">

<script src="/js/post.js"></script>

