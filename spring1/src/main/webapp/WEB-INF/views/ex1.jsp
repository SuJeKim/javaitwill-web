<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
<!DOCTYPE html>
<html>
	<head>
	  	<meta charset="UTF-8" />
		<title>Spring 1</title>
	</head>
	<body>
        <header>
    		<h1>Example 1</h1>
        </header>
        
        <main>
            <%-- get방식으로 form 데이터 전달. --%>
            <h2>GET submit</h2>
            <c:url var="ex2" value="/ex2" />
            <form action="${ ex2 }"> <%-- form method 기본값은 "get" --%> 
                <input type="text" name="username" placeholder="이름 입력" />
                <input type="number" name="age" placeholder="나이 입력" />
                <input type="submit" value="제출" />
            </form>
            
            <hr />
            
            <h2>Post submit</h2>
            <c:url var="ex3" value="/ex3" />
            <form action="${ ex3 }" method="post"> 
                <input type="text" name="username" placeholder="이름 입력" />
                <input type="number" name="age" placeholder="나이 입력" />
                <input type="submit" value="제출" />
            </form>
            
            <hr />
            
            <h2>DTO submit</h2>
            <c:url var="ex4" value="/ex4" />
            <form action="${ ex4 }" > 
                <input type="text" name="username" placeholder="이름 입력" />
                <input type="number" name="age" placeholder="나이 입력" />
                <input type="submit" value="제출" />
            </form>
            
        </main>
        
	</body>
</html>