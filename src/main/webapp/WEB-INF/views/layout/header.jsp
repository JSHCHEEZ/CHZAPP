<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>My Bootswatch Page</title>

  <!-- 부트스트랩 CSS -->
  <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet">

  <!-- Bootswatch 테마 CSS -->
  <link href="<c:url value='/css/_bootstwatch.scss' />" rel="stylesheet">
  
  <script src="<c:url value='/js/bootstrap.bundel.min.js' />"></script>
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
</head>

<body>

  <!-- 네비게이션 바 -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container">
      <a class="navbar-brand" href="<c:url value='/' />">CHZAPP</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="<c:url value='/' />">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<c:url value='/post' />">Post</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">...</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

