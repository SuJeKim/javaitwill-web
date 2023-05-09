<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
	<head>
	  	<meta charset="UTF-8" />
		<title>JSP</title>
	</head>
	<body>
		<h1>form 제출 페이지</h1>
        <form action="form2-result.jsp" method="get"> <%-- action은 submit실행시 이동 페이지 설정., get방식의 경우 method는 생략 가능함 --%>
            <div>
                <input type="text" name="username" placeholder="아이디 입력" autofocus /> <%-- requestParameter: name: "username" --%>
            </div>
            <div>
                <select name="color"> <%-- requestParameter: name: "color" --%>
                    <option value="r">빨강</option>
                    <option value="g">초록</option>
                    <option value="b">파랑</option>
                </select>
            </div>
            <div>
                <input type="submit" value="보내기" /> <%-- name 속성만을 가지고 잇는 경우에만 서버로 전송이 됨. --%>
            </div>
        </form>
        
        
        
        
	</body>
</html>