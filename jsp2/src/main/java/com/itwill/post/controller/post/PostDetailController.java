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
 * Servlet implementation class PostDetailController
 */
// 아이디로 검색해서 화면에서 보여주기.
@WebServlet(name = "postDetailController", urlPatterns = { "/post/detail" } )
public class PostDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(PostDetailController.class);
	
	private final PostService postService = PostService.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    log.info("doGet()");
	    
	    String ParamId = request.getParameter("id");
	    int id = Integer.parseInt(ParamId);
	    log.info(id + "");
	    
	    // request값을 받아서 전달. -> dao. -> 리턴.해당 내용들을 리스트 -> 그 값을 리턴.
	    List<Post> posts = postService.read(id);
	    
	    // 포스트 목록을 jsp에게 전달
	    request.setAttribute("posts", posts);
	    
	    
	    request.getRequestDispatcher("/WEB-INF/post/detail.jsp").forward(request, response);
	    
	}

}
