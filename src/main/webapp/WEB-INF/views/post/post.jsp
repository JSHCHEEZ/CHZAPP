<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<body class="content-body">
	<div class="container">
    	<h1>POST</h1>
	    <form>
	    	<textarea id="content" class="content-textarea" placeholder="일상을 공유하세요. :)"></textarea>
	   	    <label class="file-input-container">
	        	<input type="file" class="file-input" id="filesInput" name="files[]" multiple="multiple" accept="image/*">
		    </label>
		    <span id="previewContainer"></span>
		    <div class="btn-right-container">
		    	<button id="btn-save">공유하기</button>
		    </div>
	    </form>
	    
	    <hr/>

	    <div>
	     <c:forEach var="post" items="${posts.content}">
		 	<div class="timeline">
	        	<div class="timeline-item">
		            <div class="timeline-content timeline-hoverable" onclick="directView(${post.id})">
		                <p>${post.content}</p>
		                <c:forEach var="efile" items="${post.efiles}">
		                	<img src="/image/${efile.efileName}" class="preview-image">
		                </c:forEach>
		                
		            </div>
		            <div class="btn-right-container">
		                	<button class="btn btn-success btn-post" onclick="updatePost(${post.id})">수정</button>
		                	<button class="btn btn-danger btn-post" onclick="deletePost(this, ${post.id})">삭제</button>
		                </div>
		            <span class="timeline-date">${post.createDate}</span>
		        </div>
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
		            <p>삭제하시겠습니까?</p>
		        </div>
		        <div class="modal-footer">
				    <button type="button" id="yesButton" class="btn btn-primary">YES</button>
			        <button type="button" id="noButton" class="btn btn-secondary" data-bs-dismiss="modal">NO</button>
		        </div>
		    </div>
		</div>

		<ul class="pagination">
			<c:choose>
			<c:when test="${posts.first}">
			  	<li class="page-item disabled"><a class="page-link" href="?page=${posts.number-1}">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${posts.number-1}">Previous</a></li>
			</c:otherwise>
			</c:choose>
			
			<c:forEach var="page" begin="1" end="${posts.totalPages}">
				<c:choose>
				<c:when test="${posts.number == page-1}">
				  	<li class="page-item disabled"><a class="page-link" href="?page=${page-1}">${page}</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="?page=${page-1}">${page}</a></li>
				</c:otherwise>
				</c:choose>
	      	</c:forEach>
			
			<c:choose>
			<c:when test="${posts.last}">
			  	<li class="page-item disabled"><a class="page-link" href="?page=${posts.number+1}">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${posts.number+1}">Next</a></li>
			</c:otherwise>
			</c:choose>
		</ul>
  </div>
</body>

<link href="<c:url value='/css/chzapp.css' />" rel="stylesheet">

<script src="/js/post.js"></script>

