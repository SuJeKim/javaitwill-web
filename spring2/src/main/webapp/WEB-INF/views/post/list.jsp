<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
	  	<meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1"> 
		<title>Spring 2</title>
         <link 
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" 
            rel="stylesheet" 
            integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" 
            crossorigin="anonymous">
	</head>
	<body>
    <div class="container-fluid">
		<header>
            <h1>포스트 목록 페이지</h1>
        </header>
        
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
          <div class="container-fluid">
            <a class="navbar-brand" href="#">링크들</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <c:url var="mainPage" value="/" />
                        <a class="nav-link active" aria-current="page" href="${ mainPage }">메인 페이지</a>
                    </li>
                    <li class="nav-item">
                        <c:url var="postCreatePage" value="/post/create" />
                        <a class="nav-link active" aria-current="page" href="${ postCreatePage }">새 포스트 작성</a>
                    </li>
                </ul>
            </div>    
        </nav>
        
        <main>
            <div>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">번호</th>
                            <th scope="col">제목</th>
                            <th scope="col">내용</th>
                            <th scope="col">작성시간</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${ posts }" var="post">
                            <tr>
                                <td scope="row">${ post.id }</td>
                                <td>${ post.title }</td>
                                <td>${ post.content }</td>
                                <td>${ post.created_time }</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </main>
        </div>
        <script 
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" 
            crossorigin="anonymous"></script>
            
	</body>
</html>