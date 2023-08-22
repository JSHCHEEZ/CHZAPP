<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container">
  <div class="container">
    <form>
    	<textarea id="content" class="content-textarea" placeholder="일상을 공유하세요. :)">${post.content}</textarea>
	    <div id="previewContainer"></div>
   	    <label class="file-input-container">
        	<input type="file" class="file-input" id="uploadFile" name="files[]" multiple="multiple" accept="image/*">
	    </label>
        <button id="btn-save" class="img-button submit-button"></button>
    </form>
  </div>
</div>


<link href="<c:url value='/css/chzapp.css' />" rel="stylesheet">
<script src="/js/post.js"></script>
<script>
	var efiles = "${post.efiles}";
	
	efiles.forEach(function(efile, index) {
	  alert("test")
	});

</script>
