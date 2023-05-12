<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>POST</title>
    </head>
    <body>
        <header>
            <h1>포스트 검색 페이지</h1>
        </header>
        
        <nav>
            <ul>
                <li>
                    <%-- JSP에서 해당.
                         상대경로에서 현재 푤더(./)의 의미: context root까지의 주소. -> 현재 경로 
                         http://localhost:8081/post/
                    --%>
                    <%-- JSTL 서블릿. --%>
                    <c:url var="mainPage" value="/"></c:url>
                    <a href="${ mainPage }">메인 페이지</a> <!-- 상대경로에서 ./: 현재 경로, ../: 상위 경로. -->
                </li>
                <li>
                    <c:url var="postCreate" value="/post/create"></c:url>
                    <a href="${ postCreate }">새 포스트 작성</a>
                </li>
            </ul>
        </nav>
        
        <main>
            <table>
                <thead>
                    <tr>
                        <th>글 번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>수정 시간</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${ posts }" var="posts">
                        <tr>
                            <td>${ posts.id }</td>
                            <td>
                                <c:url value="/post/detail" var="postDetail">
                                    <c:param name="id" value="${ posts.id }"></c:param> <!-- 쿼리 문에서 id가 몇인지 확인 -->
                                </c:url>
                                <a href="${ postDetail }">${ post.title }</a>
                            </td>
                            <td>${ posts.author }</td>
                            <td>${ posts.modifiedTime }</td>
                        </tr>
                    
                    </c:forEach>
                </tbody>
            </table>
            
            <c:url value="/post/search" var="searchPage"></c:url>
            <form action="${ searchPage }">
                <select name="category">
                    <option value="t">제목</option>
                    <option value="c">내용</option>
                    <option value="tc">제목 + 내용</option>
                    <option value="a">작성자</option>
                </select>
                <input type="text" name="keyword" placeholder="검색어" required autofocus />
                <input type="submit" value="검색"/>
            </form>
            
        </main>
        
    </body>
</html>