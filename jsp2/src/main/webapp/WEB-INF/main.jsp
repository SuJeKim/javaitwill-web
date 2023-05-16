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
                <!-- 로그인한 username이 있는 경우 -->
                <!-- Session에 저장한 이유: redirect에서는 request 객체가 달라짐. 즉, 값이 동일하지가 않음 
                단, Session에 저장을 하면 request 객체가 바뀌어도(== redirect를 해도) 동일한 Session 객체에서 확인할 수가 있음.
                
                EL: 변수가 존재시 해당 변수를 찾는 루트 -> 1) page context 2) reqest 3) session 4) app -->
                <c:if test="${ not empty signedInUser }">
                    <li>
                        <span>${ signedInUser }</span>
                        <c:url var="signOut" value="/user/signout"></c:url>
                        <a href="${ signOut }">로그 아웃</a>
                    </li>
                </c:if>
                
                <!-- 로그인한 username이 없는 경우 -->
                <c:if test="${ empty signedInUser }">
                    <li>
                        <c:url var="signInPage" value="/user/signin"></c:url>
                        <a href="${ signInPage }">로그인</a>
                    </li>
                    <li>
                        <c:url var="signUpPage" value="/user/signup"></c:url>
                        <a href="${ signUpPage }">회원가입</a> <%-- controller 안 만듦. 404가 정상. --%>
                    </li>
                    
                </c:if>
                
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