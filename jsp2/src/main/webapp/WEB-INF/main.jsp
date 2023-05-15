<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
	  	<meta charset="UTF-8" />
		<title>POST</title>
	</head>
	<body>
        <header>
    		<h1>메인 페이지</h1>
        </header>
        
        <!-- div대신 사용. -->
        <nav>
            <ul>
                <li>
                    <a>로그 아웃</a>
                </li>
                <li>
                    <c:url var="signInPage" value="/user/signin"></c:url>
                    <a href="${ signInPage }">로그인</a>
                </li>
                <li>
                    <c:url var="postlist" value="/post"></c:url> <!-- (서블릿) 해당 c:url에서만 /: 무조건 context root임. -->
                    <a href="${ postlist }">포스트 목록 페이지</a> <!-- 상대경로 작성: context root(/post)까지 생각을 하고 그 이후 주소 부분 -->
                </li>
            </ul>
        </nav>
        
        <main>
            <!-- TODO -->
        </main>
	</body>
</html>