package com.itwill.post.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter(
        filterName = "authenticationFilter",
        urlPatterns = { 
                "/post/create", "/post/detail", "/post/modify", "/post/update", "/post/delete" 
                }) // 로그인이 필요한 기능(요청 주소)들

// urlPatterns에 설정된 요청 주소들에 대해서,
// 로그인이 되어 있으면 요청을 계속해서 처리(컨트롤러에게 요청을 전달)
// 로그인이 되어 잇지 않으면 로그인 페이지로 redirect.
public class AuthenticationFilter extends HttpFilter implements Filter {
    
    private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);
       
   

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.info("doFilter()");
		
		// 로그인이 되어 있는 지를 체크:
		// (1) request(요청 객체)에서 Session을 찾음
		HttpSession session = ((HttpServletRequest) request).getSession(); // getSession이 HttpServletRequest에 속해져 있음.
		// (2) 세션에 로그인 정보(signedInUser)가 있는 지를 확인. -> 세션에 저장할 떄 해당 변수로 저장했으니 가져올 때도 동일한 변수로.
		String username = (String) session.getAttribute("signedInUser"); // 저장할 수 있는 데이터들은 어떤 타입이든지 가능하니 Object를 반환.
        log.info("로그인 정보: {}", username);
		
		if (username != null) { // signedInUser정보가 Session에 있다. 즉, 로그인 정보가 Session에 저장된 경우.
		    // 요청을 필터 체인 순서대로 전달. - 해당 컨트롤러로 전달.
		    chain.doFilter(request, response);
		    
		    return;
		} 
		
		// 로그인 정보가 없으면, 로그인 페이지로 redirect.
		// response 객체가 HttpServletResponse가 아니어서 sendRedirect가 없음. -> 캐스팅 필요
		((HttpServletResponse)response).sendRedirect("/post/user/signin");
		
	}


}
