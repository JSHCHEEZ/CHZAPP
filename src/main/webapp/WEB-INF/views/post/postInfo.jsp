<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container">
  <div class="container">
    <h1>POST 작성</h1>
    <form>
      <div class="mb-3">
        <label for="content" class="form-label">콘텐트</label>
        <input type="text" class="form-control" id="content" placeholder="...">
      </div>
      <div class="mb-3">
        <label for="hashtag" class="form-label">태그입력</label>
        <input type="text" class="form-control" id="hashtag" placeholder="###">
      </div>
    </form>
    
    <button id="btn-save" class="btn btn-primary">작성</button>
  </div>
</div>

<script src="/js/post.js"></script>

<%@ include file="../layout/footer.jsp" %>