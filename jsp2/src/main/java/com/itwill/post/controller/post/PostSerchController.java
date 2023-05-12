package com.itwill.post.controller.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.post.model.Post;
import com.itwill.post.service.PostService;

/**
 * Servlet implementation class PostSerchController
 * 
 * post() 방식이 왜 안돼ㅣ
 */
@WebServlet(name = "postSerchController", urlPatterns = {"/post/search"})
public class PostSerchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(PostSerchController.class);
	
	private final PostService postService = PostService.getInstance();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("doPost()");
		
		// 요청 파라미터의 값들을 넘김.
		String values = request.getParameter("category");
		
		
		String keyword = request.getParameter("keyword");
		log.info("values = {}", values);
		log.info("keyword = {}", keyword);
		
		List<Post> post = postService.searchByvalues(values, keyword);
		log.info("post = {}", post);
		
		request.setAttribute("posts", post);
		
		request.getRequestDispatcher("/WEB-INF/post/search.jsp").forward(request, response);
	}

}
