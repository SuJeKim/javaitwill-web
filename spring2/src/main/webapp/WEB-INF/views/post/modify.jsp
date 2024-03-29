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
		<header class="my-2 p-3 text-center text-bg-dark">
            <h1>포스트 수정하기</h1>
        </header>
        
        <%-- 
        <div>
        ${ post } <%-- post.toString
        </div>
        --%>
        
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <ul class="navbar-nav bg-light">
                <li class="nav-item">
                    <c:url var="mainPage" value="/" /> <%--param이 없을 경우 --%>
                    <a class="nav-link"  href="${ mainPage }">메인 페이지</a>
                </li>
                <li class="nav-item">
                    <c:url var="postListPage" value="/post/list" /> <%--param이 없을 경우 --%>
                    <a class="nav-link" href="${ postListPage }">포스트 목록</a>
                </li>
                 <li class="nav-item">
                    <c:url var="postdetailPage" value="/post/detail" >
                        <c:param name="id" value="${ post.id }"></c:param>
                    </c:url> 
                    <a class="nav-link" href="${ postdetailPage }">상세보기</a>
                </li>
            </ul>
        </nav>
        
        <main class="my-2" >
            <div class="card" style="width: 18rem;">
                <form class="card-body" id="modifyForm">
                    <div class="my-2">
                        <label class="form-label" for="id">번호</label>
                        <input class="form-control" name="id" id="id" value="${ post.id }" readonly />
                        <%-- reqest parameter에서 name 속성이 있어야 submit이 됨 + id: for에서 찾기 위해서 --%>
                    </div>
                    <div class="my-2">
                        <label class="form-label" for="title">제목</label>
                        <input class="form-control" name="title" id="title" value="${ post.title }"  autofocus />
                    </div>
                    <div class="my-2">
                        <label class="form-label" for="content">내용</label>
                        <textarea class="form-control" name="content" id="content" >${ post.content }</textarea>
                    </div>
                     <div class="my-2">
                        <label class="form-label" for="author">작성자</label>
                        <input class="form-control" name="author" id="author" value="${ post.author }" readonly />
                    </div>
                    
                <div class="card-footer">
                    <div class="d-flex justify-content-start">
                        <button class="mx-2 btn btn-outline-danger" id="btnDelete">삭제</button>
                        <button class="mx-2 btn btn-outline-success" id="btnUpdate">업데이트</button>
                    </div>
                </div>
                </form>
               
            </div>
        </main>
        
        
		 <script 
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" 
            crossorigin="anonymous"></script>
         <script src="../static/js/post-modify.js"></script> 
         <%-- ../: 한 단계 위를 의미를 하는데(WEB-INF - 서버 내에서만 동작, 브라우저에서 못찾음), 
         클라이언트에서는 WEB-INF에 직접 접근이 안되기에(모르기에) 한 단계 위가 webapp이라고 고려함.--%>
            
    </div>        
	</body>
</html>