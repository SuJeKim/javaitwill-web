<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
	  	<meta charset="UTF-8" />
		<title>JSP</title>
        
        <%
        // 요청 파라미터의 한글이 깨지지 않도록 하기 위해서:
        request.setCharacterEncoding("UTF-8");
        
        // 요청 파라미터 값 찾기:
        String username =  request.getParameter("username"); //"username": form에서 설정한 name 속성의 값.    
        String color = request.getParameter("color");
        String colorValue = "";
        if (color.equals("r")) {
            colorValue = "crimson";
        } else if (color.equals("g")) {
            colorValue = "darkgreen";
        } else if (color.equals("b")) {
            colorValue = "dodgerblue";
        }
        %>
        
        <%-- set: 변수를 만들어 주는 태그 - var: 변수, value: 변수의 값.--%>
        <c:set var="colorValue2" value="black"></c:set> <!--  == String colorValue2 = "black"; -->
        <%-- param == requestParameter 
            + jstl에서의 if는 다 하나씩 검사함. -> else if랑은 다름.
        --%>
        <c:if test="${ param.color == 'r' }">
            <c:set var="colorValue2" value="red"></c:set>
        </c:if>
        <c:if test="${ param.color == 'g' }">
            <c:set var="colorValue2" value="green"></c:set>
        </c:if>
        <c:if test="${ param.color == 'b' }">
            <c:set var="colorValue2" value="blue"></c:set>
        </c:if>
        
        <style>
        span#span1 {
             color: <%= colorValue %>;
        }
        
        span#span2 {
            color: ${ colorValue2 };
            <%-- JSTL의 <c:set> 태그에서 선언한 변수는 EL에서 사용 가능. --%>
        }
        </style>
	</head>
	<body>
		<h1>요청 결과 페이지</h1>
        <h2>JSP</h2>
        <h3>아이디: 
            <span id="span1"><%= username %></span>
        </h3>
        
        <hr />
        <h2>JSTL, EL</h2>
        <h3>아이디:    
            <span id="span2">${ param.username }</span>
        </h3>
        
        <hr />
        
        <%-- jstl의 if-else 구문.
            -> when을 중복 사용해도 됨.
            ** el 사용시 공백 주의하기.
              -> } ": true + "" -> 전혀 다른 문자열이 됨.
              -> "" 사이 공백 없어야 함. 단, {} 안에서는 공백 있어도 됨.
         --%>
        
        <c:choose>
            <c:when test="${ param.username == 'admin' }">
                <h2>관리자 페이지</h2>
            </c:when>
            <c:otherwise>
                <h2>일반 사용자 페이지</h2>
            </c:otherwise>
        </c:choose>
        
	</body>
</html>