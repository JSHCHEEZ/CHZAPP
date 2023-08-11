<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container">
  <!-- 콘텐츠 목록 -->
  <div class="container mt-4">
    <h1>POST</h1>
    
    <form>
      <div class="mb-3" style="">
        <label for="content" class="form-label">콘텐트</label>
        <input type="text" class="form-control" id="content" placeholder="..." style="">
            <button id="btn-save" class="btn btn-primary" style="float: right">POST</button>
      </div>
    </form>
    
    <div>
     <c:forEach var="post" items="${posts.content}">
     	<div class="card border-secondary mb-3" style="max-width: 20rem;">
		  <div class="card-header">Username</div>
		  <div class="card-body">
		    <p class="card-text">${post.content}</p>
		  </div>
		</div>
     </c:forEach>
	</div>

	<ul class="pagination justify-content-center">
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

</div>

<script src="/js/post.js"></script>

<%@ include file="../layout/footer.jsp" %>