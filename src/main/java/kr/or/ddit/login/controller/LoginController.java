package kr.or.ddit.login.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;

@Controller
public class LoginController {
	
	@Resource(name="userService")
	private IUserService userService;
	
	/**
	 * 사용자 로그인 홤면 요청
	 * @param session
	 * @return
	 */
	@RequestMapping(path="/login", method = RequestMethod.GET)
	public String loginView(HttpSession session) {
		if(session.getAttribute("USER_INFO") != null)
			return "main";
		else
			return "login/login";
	}
	
	/**
	 * 사용자 로그인 요청 처리
	 * @return
	 */
	@RequestMapping(path="/login", method = RequestMethod.POST)
	public String loginProcess(String userId, String password, String rememberme,
								HttpServletResponse response, HttpSession session) {
		
		String encryptPassword = KISA_SHA256.encrypt(password);
		UserVO userVo = userService.getUser(userId);
		
		if(userVo != null && encryptPassword.equals(userVo.getPass())) {
			rememberMeCookie(userId, rememberme, response);
			
			session.setAttribute("USER_INFO", userVo);
			
			return "main";
		}
		
		else
			return "login/login";
	}

	/**
	 * rememberme 쿠키를 응답으로 생성
	 * @param userId
	 * @param rememberme
	 * @param response
	 */
	private void rememberMeCookie(String userId, String rememberme, HttpServletResponse response) {
		int cookieMaxAge = 0;
		if(rememberme != null)
			cookieMaxAge = 60*60*24*30;
		
		Cookie userIdCookie = new Cookie("userId", userId);
		userIdCookie.setMaxAge(cookieMaxAge);
		
		Cookie remembermeCookie = new Cookie("rememberme", "true");
		remembermeCookie.setMaxAge(cookieMaxAge);
		
		response.addCookie(remembermeCookie);
		response.addCookie(userIdCookie);
	}
	
}
