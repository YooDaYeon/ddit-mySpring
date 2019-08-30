package kr.or.ddit.login.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.servlet.http.HttpSession;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testenv.ControllerTestEnv;
import kr.or.ddit.user.model.UserVO;

@WebAppConfiguration
public class LoginControllerTest extends ControllerTestEnv{
	
	/**
	 *  접속하지 않은 상황에서 loginView요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void loginViewNotLoginedTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult =  mockMvc.perform(get("/login")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("login/login", viewName);
		
		
	}
	
	/**
	 * 로그인한 상황에서 로그인 뷰 요청 테스트
	 * @throws Exception
	 */
	@Test
	public void loginViewLoginedTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult =  mockMvc.perform(get("/login").sessionAttr("USER_INFO", new UserVO())).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("main", viewName);
	}
	
	/**
	 * 로그인 요청 처리 성공 테스트
	 * @throws Exception 
	 */
	@Test
	public void loginProcessSuccessTest() throws Exception {
		/***Given***/
		String userId = "sally";
		String password = "sally1234";
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/login").param("userId", userId)
									  .param("password", password)).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		HttpSession session = mvcResult.getRequest().getSession();
		String viewName = mav.getViewName();
		UserVO userVo = (UserVO)session.getAttribute("USER_INFO");
		
		/***Then***/
		assertEquals("main", viewName);
		assertEquals(userVo.getName(), "샐리");
		
		
	}
	
	/**
	 * 로그인 요청 처리 실패 테스트
	 * @throws Exception 
	 */
	@Test
	public void loginProcessFailTest() throws Exception {
		/***Given***/
		String userId = "sally";
		String password = "sally123455"; //틀린 비밀번호
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/login").param("userId", userId)
									  .param("password", password)).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("login/login", viewName);
		
		
	}

}
