package com.itwill.jsp1.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Servlet implementation class MvcServlet
 */
@WebServlet(name = "mvcServlet", urlPatterns = { "/mvc" }) // index 페이지에서 href="mvc"로 설정을 하여 /jsp1/mvc로 가기에
public class MvcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalDateTime now = LocalDateTime.now();
		Timestamp ts = Timestamp.valueOf(now);
		
		request.setAttribute("now", ts); // view에 자바 코드가 없게하기위해서 여기서 사용함. 넘어갈 때 now를 el로 사용할 수가 있음.
		// Servlet에서 설정한 속성은 forward 방식으로 전환된 JSP에서도 여전히 HttpServletRequest 객체의 
		// 속성으로 존재하며, 이를 EL 표현식을 사용하여 참조할 수 있습니다. 이를 통해 Servlet과 JSP 간의 값을 전달하고 공유할 수 있습니다.
		// -> redirect 방식에서는 안됨. servlet의 요청 객체와 응답 객체가 존재하지 않기에
	    
	    request.getRequestDispatcher("/WEB-INF/view.jsp").forward(request, response);
		// servlet에서 다음 파일 화면(jsp - el,jstl을 사용해서)으로 넘어가는 forward 방식.
	}

}
