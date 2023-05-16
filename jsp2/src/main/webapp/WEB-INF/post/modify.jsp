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
    		<h1>포스트 수정 페이지</h1>
        </header>
        
        <nav>
            <ul>
                <li>
                    <c:url var="signOut" value="/user/signout" />
                    <span>${ signedInUser }</span> <%-- 로그인된 사용자의 이름 --%>
                    <a href="${ signOut }">로그 아웃</a>
                </li>
                <li>
                    <c:url var="mainPage" value="/" />
                    <a href="${ mainPage }">메인 페이지</a>
                </li>
                <li>
                    <c:url var="listPage" value="/post"></c:url>
                    <a href="${ listPage }">포스트 목록</a> 
                </li>
                <li>
                    <c:url var="postDetail" value="/post/detail">
                        <c:param name="id" value="${ post.id }"></c:param>
                    </c:url>
                    <a href="${ postDetail }">포스트 상세보기</a>
                </li>
            </ul>
        </nav>
        
       <main>
            <form id="postModifyForm"> <%-- form의 기본동작: action, method-> method는 기본값이 get, action의 기본값은 현 페이지(현재 경로)로 다시 요청보냄. --%>
                <div>
                    <input id="id" name="id"
                        type="text" value="${ post.id }" readonly />
                </div>
                <div>
                    <input id="title" name="title"
                        type="text" value="${ post.title }" autofocus />
                </div>
                <div>
                    <textarea id="content" name="content"
                        rows="5" cols="80" >${ post.content }</textarea>
                </div>
                <div>
                    <input type="text" value="${ post.author }" readonly />
                </div>
                <c:if test="${ signedInUser == post.author }"> 
                <%-- 만약 주소를 입력을 하고 들어오는 경우 버튼을 보이지 않게 하여 수정/삭제를 못하게 함. 또한,
                    post 방식이기에 주소줄에 입력해서 들어갈수는 없음 주소줄에 입력해서 수정/삭제가 되는 것은 get 방식임.
                 --%>
                <div>
                    <button id="btnUpdate">수정완료</button> <%-- eventHandler사용 필. -> form에서 되는 방식도 바꿔야 함: form 안/밖에서 선언해도 됨. --%>
                    <button id="btnDelete">삭제</button>
               </div>
               </c:if>
            </form>
       </main>
        
        <script src="../js/post-modify.js"></script>
	</body>
</html>