<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>CHZAPP</title>

  <!-- 부트스트랩 CSS -->
  <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet">

  
  <script src="<c:url value='/js/bootstrap.bundel.min.js' />"></script>
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
</head>

<body class="header-body">
	<nav class="navbar">
	  <div class="logo">CHZAPP</div>
	  <ul class="nav-links">
	    <li><a href="<c:url value='/' />">Home</a></li>
	    <li><a href="<c:url value='/post' />">Post</a></li>
	  </ul>
	</nav>
</body>

<link href="<c:url value='/css/chzapp.css' />" rel="stylesheet">
