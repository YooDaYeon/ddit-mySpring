package kr.or.ddit.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.ddit.user.dao.IUserDao;

public class SessionCheckInterceptor extends HandlerInterceptorAdapter{

	
	private static final Logger logger = LoggerFactory.getLogger(SessionCheckInterceptor.class);
	
	@Resource(name="userDao")
	private IUserDao userDao;
	
	/**
	 * 로그인한 사용자만 controller에 접근이 가능하도록 체크
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		logger.debug("userDao {}", userDao);
		
		
		HttpSession session = request.getSession();
		
		//사용자 로그인 상태
		if(session.getAttribute("USER_INFO") != null) 
			return true;
		//로그인 상태가 아닌 경우 : 로그인 페이지로 이동
		else {
			response.sendRedirect("/login");
			return false;
		}
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.setAttribute("userList", userDao.userList());
	
	}
	

	
	
}
