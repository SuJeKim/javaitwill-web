<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <!-- html문법이 아님. 자바 코드 사용 가능. -->
<!-- /index.jsp 파일임, /ex1과는 다른 파일.-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>JSP</title>
    </head>
    <body>
        <h1>Index</h1>
        
        <ul>
            <li>
                <a href="ex1">First Servlet</a> 
                <!-- 상대경로 링크 지정: servlet 주소 -> 
                http://localhost:8081/jsp1/ 만 사용해도 뜸 web.xml에서
                 welcome-file-list에서 파일 이름이 지정이 안 되어 있을 댸 해당 파일 이름이 열림.-->
            </li>
            <li>
                <a href="ex2">second Servlet</a> <!-- ex2를 처리할 수 있는 jsp나 Servlet이 존재하지 않기에 -->
            </li>
            <li>
                <a href="ex3">포워드</a>
            </li>
            <li>
                <a href="ex4">리다이렉트</a>
            </li>
            <li>
                <!-- URL: 상대경로: http://localhost:8081/contextRoot/ 까지를 현재 작업 디렉토리로 하고, 그 이후 주소만 표기 -->
                <a href="intro.jsp">JSP 소개</a>
            </li>
            <li>
                <a href="form.jsp">form 제출</a>
            </li>
            <li>
                <a href="main.jsp">include 지시문(directive)</a>
            </li>
            <li>
                <a href="scriptlet.jsp">스크립트릿(scriptlet)</a>
            </li>
            <li>
                <a href="actiontag.jsp">액션 태그(action tag)</a>
            </li>
            <li>
                <a href="el.jsp">EL(Expression Language)</a>
            </li>
            <li>
                <a href="jstl.jsp">JSTL</a>
            </li>
            <li>
                <a href="form2.jsp">form 제출</a>
            </li>
            <li>
                <a href="form2-result.jsp?username=adm&in&color=b">클릭 1</a> 
                <!-- 다음 링크는 쿼리 스트링(requestParmeter)을 갖아야만 하는 링크이다. 그래서 오류가 뜨지 않도록 requestParmeter을 만들어서 보내야 한다. -->
                <!-- &: requestParmeter를 구분하는 기호. -->
                <!-- requestParmeter로 특수 기호보내는 법:
                     클라이언트 -> 서버로 전달.
                    1. 특수 기호의 UTF-8 코드값 전달.
                    2. JSTL의 태그를 이용해서 URL을 전달.
                 -->
            </li>
            <li>
                <c:url var="reqURL" value="form2-result.jsp">
                    <c:param name="username" value="adm&in"></c:param>
                    <c:param name="color" value="g"></c:param>
                </c:url>
                <a href="${ reqURL }">클릭 2</a> 
            </li>
            <li>
                <a href="format.jsp">포멧팅</a>
            </li>
            <li>
                <a href="mvc">MVC pattern</a>
            </li>
        </ul>
    </body>
</html>