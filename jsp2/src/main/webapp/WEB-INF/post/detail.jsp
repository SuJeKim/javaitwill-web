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
    		<h1>포스트 상세보기 페이지</h1>
        </header>
        
        <nav>
            <ul>
                <li>
                    <c:url var="signOut" value="/user/signout"></c:url>
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
                <%-- 로그인 사용자 아이디와 글 작성자 아이디가 같은 경우에만 수정 메뉴를 보여줌. --%>
                <%-- EL: 자바 코드를 걷어낼려고 사용하는 것이기에 String에서 .equals가 아닌 그냥 연산자 사용. --%>
                <c:if test="${ signesInUser == posts.author }">
                    <li>
                        <c:url var="postModify" value="/post/modify">
                            <c:param name="id" value="${ posts.id }"></c:param>
                        </c:url>
                        <a href="${ postModify }">포스트 수정</a>
                    </li>
                </c:if>
            </ul>
        </nav>
        
       <main>
            <form>
                <div>
                    <input type="text" value="${ posts.id }" readonly />
                </div>
                <div>
                    <input type="text" value="${ posts.title }" readonly />
                </div>
                <div>
                    <textarea rows="5" cols="80" readonly>${ posts.content }</textarea>
                </div>
                <div>
                    <input type="text" value="${ posts.author }" readonly />
                </div>
                <div>
                    <input type="text" value="${ posts.createdTime }" readonly />
                </div>
                <div>
                    <input type="text" value="${ posts.modifiedTime }" readonly />
                </div>
            </form>
            <%--
             내가 고려한 것. -> 그냥 나옴.
            <c:forEach var="post" items="${posts}">
        
                <div>
                    id: ${ post.id }
                </div>
                <div>
                    title: ${ post.title }
                </div>
                <div>
                    content: ${ post.content }
                </div>
                <div>
                    author: ${ post.author }
                </div>
                <div>
                    createdTime: ${ post.createdTime }
                </div>
                <div>
                   modifiedTime: ${ post.modifiedTime }
                </div>
                
            </c:forEach>
             --%>
       </main>
        
        
	</body>
</html>