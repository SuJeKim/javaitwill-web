package com.itwill.post.controller.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class PostController
 */
@WebServlet(name = "postListController", urlPatterns = { "/post" })
// servlet을 만든 다음에 restart하기.
// 다른 servlet이랑 name이 겹치면 안됨. 왜냐 서버가 이름을 찾아서 해당 요청을 처리하기에.
public class PostListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("postListController.doGet() 호출");
		
		// mvc모델2에서 servelt이 모든 요청을 받아들임. 그러므로 view를 설정하는 jsp의 경우 파일 이름이 달라도 됨.
		// 파일 이름과 urlPatters의 경우는 다른 것임.
		request.getRequestDispatcher("/WEB-INF/post/list.jsp").forward(request, response);
	}


}
