<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <!-- html문법이 아님. 자바 코드 사용 가능. -->
<!-- /index.jsp 파일임, /ex1과는 다른 파일.-->
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
        </ul>
    </body>
</html>