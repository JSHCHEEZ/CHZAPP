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
		<button id="btn-reply" class="img-button submit-button"></button>
	</form>
	<c:forEach var="reply" items="${post.replys}">
   		<div class="reply-list">
		    <div class="reply">
		      <div class="reply-author">CHZ</div>
		      <div class="reply-text">${reply.content}</div>
		    </div>
		</div> 
	</c:forEach>

</div>
	

<style>
.reply {
  border-bottom: 1px solid #ccc;
  padding: 10px 0;
}

.reply:last-child {
  border: none;
}

.reply-author {
  font-weight: bold;
  margin-bottom: 5px;
}

.reply-text {
  color: #333;
}
</style>

<link href="<c:url value='/css/chzapp.css' />" rel="stylesheet">

<script src="/js/post.js"></script>
