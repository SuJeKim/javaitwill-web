package com.itwill.post.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(name = "homeController", urlPatterns = { "" })
// urlPatterns = { "" }: "http://localhost:8081/post/" 요청 주소(context root)를 처리하는 서블릿.
// name: 서버(톰캣)가 변수를 찾을 때 사용함
// urlPatterns: 해당 요청에 따라 html을 보냄. 즉, 무슨 요청 처리가 중요함. -> url주소에 따라서 해당 homeController가 처리하겠다.
//              -> urlPatterns = { "/" }: 모든 요청을 다 처리함: context root후에 어떤 주소가 오든 모든 요청을 처리함.
//              -> urlPatterns에서 URL을 설정할 때는 Context root를 기준으로 작성 ==>  Context root를 포함한 전체 URL을 작성
// ==> 다음 에너테이션은 톰켓/WAS(Web Application Server) 을 위한 것.             

public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	// HttpServlet()가 갖고 있는 doGet을 override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 웹 서버(WAS:톰캣)에서 요청주소를 보고서 servlet클래스 확인 및 get 방식, post방식 확인 후 호출됨.
	    // parameter도 WAS가 전달.
	    System.out.println("homeController.doGet() 호출");
		
		// View로 요청을 포워드(forward 정의: 받은 것을 그대로 돌려줌):
		request.getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
	}

}
