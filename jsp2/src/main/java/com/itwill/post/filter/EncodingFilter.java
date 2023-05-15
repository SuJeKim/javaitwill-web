package com.itwill.post.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet Filter implementation class EncodingFilter
 */
public class EncodingFilter extends HttpFilter implements Filter {

    // SLf4j 로깅 기능:
    private static final Logger log = LoggerFactory.getLogger(EncodingFilter.class);
    
    // 생성자
    public EncodingFilter () {
        log.info("Encoding Filter 생성자 호출");
    }
    
    // Filter의 라이프사이클 메서드: destroy(), doFilter(), init() 
	/**
	 * HttpFilter것
	 * @see Filter#destroy()
	 * 
	 */
    @Override
	public void destroy() {
        // Filter 객체를 메모리에서 제거할 때 WAS가 호출하는 생명주기(life cycle)메서드.
        // Filter 객체가 소멸될 때: 리소스 해제와 같은 작업이 필요하면 해당 작업들을 수행함.
        // 웹 서버가 셧다운될 때 호출되는 메서드. 즉, 웹 서버가 죽어야 Filter가 없어짐.
        
        log.info("destroy() 호출");
	}

	/**
	 * HttpFilter것
	 * 가장 중요함. + 빠지면 안 됨.
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 * 
	 */
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("dofilter() : chain.doFilter() 호출 전.");
        // 클라이언트에서 온 요청을 controller(서블릿)에게 전달하기 전에 실행할 코드들을 작성.

		// 요청을 필터체인의 그 다음 단계로 전달 -> 다른 필터가 없이 바로 controller를 호출하는 거라면 controller 메서드가 호출됨.
        // -> 필터가 여러개일 경우도 존재.
		chain.doFilter(request, response); 
		// Filterchain: (필터가 2개이상일 경우) 필터들이 여러개 연결 되어있는 모습
		// FilterCain: 필터가 1개 일경우 controller에게 해당 파라미터를 전달.
		//               //   2개 일경우 다른 Filter에게 해당 파라미터를 전달.
		
		log.info("doFilter() : chain.doFilter 호출 후");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
    @Override
	public void init(FilterConfig fConfig) throws ServletException {
		// 필터 객체가 생성된 후 초기화(intilalization) 작업이 필요할 때 WAS가 호출하는 생명주기 메서드.
        
        log.info("init() 호출");
	}

}
