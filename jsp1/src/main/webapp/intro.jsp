<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	  	<meta charset="UTF-8" />
		<title>JSP</title>
	</head>
	<body>
		<h1>JSP 소개</h1>
        <h2>Java Server Page</h2>
        
        <!-- HTML, XML 주석 -->
        <%-- JSP 주석: jsp 파일 -> '자바 파일 -> 클래스 파일' == '컴파일' -> doGet() -> HTML --%>
        <%--
        Servlet/JSP 동작 방식:
        1. Servlet: Server + Applet 합성어.
           - 웹 서버에서 실행되는, 요청을 처리하는 작은 Java 프로그램.
           - Java 클래스로 작성. HttpServlet 클래스를 상속.
           - 서블릿 객체의 생성과 서블릿 메서드 호출은 WAS(tomcat)가 담당.
             * 최초 요청: 서블릿 객체 생성 -> doGet, doPost 메서드 호출 -> 응답.
             * 요청: 생성되어 있는 서블릿 객체의 doGet, doPost 메서들 호출 -> 응답. 
             + 그 다음부터는 서블릿 객체 생성자를 호출하지 않음. 오직 1개.
           - 자바 코드 작성시.  
        2. JSP: Java Server Page:
           - 서블릿은 순수한 자바 클래스 코드이기 떄문에 HTML을 작성하기가 힘듦.
           - HTML 형식의 문서 안에서 자바 코드들이 실행될 수 있도록 만든 Server-side 문법.    
           - JSP 실행 과정: jsp -> java -> class(컴파일 결과물) -> 객체 생성 -> 메서드 호출(doGet(), doPost()) -> 응답.
             * 최초 요청: jsp를 서블릿 클래스(java - public)로 변환. -> 컴파일해서 확장자가 class 파일 생성
                -> 객체 생성 -> 메서드 호출 -> 응답.
             * 요청: 생성되어 있는 객체에서 메서드 호출 -> 응답.   
           - 화면을 만들어 낼떄 많이 사용.  
         --%>
        
	</body>
</html>