<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container">
  <div class="container">
    <h1>POST 확인</h1>

      <div class="mb-3">
        <div class="card border-primary mb-3" style="max-width: 20rem;">
		  <div class="card-header"></div>
		  <div class="card-body">
		    <h4 class="card-title">${post.hashtag}</h4>
		    <p class="card-text">${post.content}</p>
		  </div>
		</div>
      </div>

  </div>
</div>

<script src="/js/post.js"></script>

<%@ include file="../layout/footer.jsp" %>