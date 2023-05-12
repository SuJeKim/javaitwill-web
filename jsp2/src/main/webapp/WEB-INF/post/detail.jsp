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
                    <c:url var="listPage" value="/post"></c:url>
                    <a href="${ listPage }">목록 페이지</a> 
                </li>
            </ul>
        </nav>
        
       <main>
            <form>
                <div>
                    <input type="number" value="${ posts.id }"/>
                </div>
                <div>
                    <input type="text" value="${ posts.title }"/>
                </div>
                <div>
                    <textarea rows="5" cols="">${ posts.content }</textarea>
                </div>
                <div>
                    <input type="text" value="${ posts.author }"/>
                </div>
                <div>
                    <input type="text" value="${ posts.createdTime }"/>
                </div>
                <div>
                    <input type="text" value="${ posts.modifiedTime }"/>
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