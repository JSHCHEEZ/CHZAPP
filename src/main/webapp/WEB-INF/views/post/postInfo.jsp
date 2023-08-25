<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

 <div class="container">
  <h1>POST 수정</h1>
    <form>
    	<input type="hidden" id="postId" value="${post.id}" />
    	<textarea id="content" class="content-textarea" placeholder="일상을 공유하세요. :)">${post.content}</textarea>
   	    <label class="file-input-container">
        	<input type="file" class="file-input" id="filesInput" name="files[]" multiple="multiple" accept="image/*">
	    </label>
	    <span id="previewContainer0">
	    	<c:forEach var="efile" items="${post.efiles}">
	    	<span>
	    		<img src="/image/${efile.efileName}" class="preview-image">
	    		<button type="button" class="btnX" onclick="deletePost(this, ${efile.id},'File')">X</button>
	    	</span>	
             </c:forEach>
	    </span>
	    <span id="previewContainer"></span>

	    <div class="btn-right-container">
	    	<button id="btn-edit">수정하기</button>
	    </div>
    </form>
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
            <p>선택하신 사진을 삭제하시겠습니까?</p>
        </div>
        <div class="modal-footer">
		    <button type="button" id="yesButton" class="btn btn-primary">YES</button>
	        <button type="button" id="noButton" class="btn btn-secondary" data-bs-dismiss="modal">NO</button>
        </div>
    </div>
</div>



<link href="<c:url value='/css/chzapp.css' />" rel="stylesheet">
<script src="/js/post.js"></script>

