package com.itwill.jsp1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ForwardServlet
 */
@WebServlet(name = "forwardServlet", urlPatterns = { "/ex3" }) // '/' 주의
public class ForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    System.out.println("ForwardServlet#doGet() 호출");

	    
	    //servlet에서는 요청이 들어온 것을 jsp로 전달(응답)을 함.
	    
	    // 클라이언트에서 서버로 온 요청을 새로운 페이지로 "forward" 방식으로 이동:
	    // 같은 WAS(Web Application Server)의 같은 웹 애플리케이션 안에서만 페이지 이동이 가능.
	    // -> 같은 WAS: tomcat, 같은 웹 애플리케이션: 같은 프로젝트.
	    // 다른(웹) 서버 또는 다른 웹 애플리케이션의 페이지로 forward 이동을 할 수 없음.
	    // 요청 주소(URL)가 바뀌지 않음.
	    // -> jsp가 보내준 정보를 보고 있음. 그런데 주소는 servlet.
	    // -> servlet이 없어도 jsp가 단독으로 처리 가능함.
	    // -> 하지만, forward에서는 최초 요청 주소가 그대로 남아있으면서 응답을 보내주는 파일/페이지가 달라짐.
	    // 즉, request 객체와 response 객체가 유지됨.
	    //     => tomcat에게서 전달받은 argument를 그대로 forward의 example.jsp에 넘겨줌.
	    //         -> example.jsp에서 똑같은 argument를 볼 수 있음.
	    
	    // src/main/webapp 폴더 아래의 파일 경로와 파일 이름을 사용.
	    request.getRequestDispatcher("example.jsp").forward(request, response);
	}

}
