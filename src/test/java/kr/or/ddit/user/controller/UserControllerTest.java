package kr.or.ddit.user.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.ControllerTestEnv;
import kr.or.ddit.user.model.UserVO;

public class UserControllerTest extends ControllerTestEnv{


	/**
	 * 사용자 전체 리스트 테스트
	 * @throws Exception 
	 */
	@Test
	public void userListTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/userList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		/***Then***/
		assertEquals("user/userList", mav.getViewName());
		assertEquals(111, ((List<UserVO>)mav.getModelMap().get("userList")).size());
		
	}
	
	/**
	 * 사용자 페이징 리스트 테스트
	 * @throws Exception 
	 */
	@Test
	public void userPagingListTest() throws Exception {
		/***Given***/
		
		
		/***When***/
		MvcResult mvcResult =  mockMvc.perform(get("/userPagingList")
												.param("page", "2")
												.param("pageSize", "10"))
												.andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<UserVO> userList = (List<UserVO>) mav.getModelMap().get("userList");
		int paginationSize = (int) mav.getModelMap().get("paginationSize");
		PageVO pageVo = (PageVO) mav.getModelMap().get("pageVO");
		
		/***Then***/
		//viewName
		//속성 userList, paginationSeize, pageVo
		assertEquals("user/userPagingList", viewName);
		assertEquals(10, userList.size());
		assertEquals(1, paginationSize);
		assertEquals(2, pageVo.getPage());
		assertEquals(10, pageVo.getPageSize());
	}

	/**
	 * 사용자 페이징 리스트 테스트(page, pageSize 파라미터 없이 호출)
	 * @throws Exception
	 */
	@Test
	public void userPagingListWithoutParameterTest() throws Exception {
		/***Given***/
		
		
		/***When***/
		MvcResult mvcResult =  mockMvc.perform(get("/userPagingList"))
												.andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<UserVO> userList = (List<UserVO>) mav.getModelMap().get("userList");
		int paginationSize = (int) mav.getModelMap().get("paginationSize");
		PageVO pageVo = (PageVO) mav.getModelMap().get("pageVO");
		
		/***Then***/
		//viewName
		//속성 userList, paginationSeize, pageVo
		assertEquals("user/userPagingList", viewName);
		assertEquals(10, userList.size());
		assertEquals(12, paginationSize);
		
		//PageVO equals, hashCode메소드를 구현해서 객체간 값 비교
		assertEquals(new PageVO(1, 10), pageVo);
		
//		assertEquals(1, pageVo.getPage());
//		assertEquals(10, pageVo.getPageSize());
	}
	
	
	@Test
	public void userTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user").param("userId", "sally")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		UserVO userVo = (UserVO) mav.getModelMap().get("userVo");
		
		
		/***Then***/
		assertEquals("user/user", viewName);
		assertEquals("샐리", userVo.getName());
	}
	
	/**
	 * 사용자 입력 화면 요청
	 * @throws Exception 
	 */
	@Test
	public void userFormTest() throws Exception {
		/***Given***/
		
		/***When***/
		mockMvc.perform(get("/userForm")).andExpect(view().name("user/userForm"));
		/***Then***/
	}
	
	/**
	 * 사용자 등록 테스트 (Success 시나리오)
	 * @throws Exception
	 */
	@Test
	public void userFormPostSuccessTest() throws Exception {
		
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/Rachel1.JPG");
		MockMultipartFile file = new MockMultipartFile("profile", f.getName(),"",new FileInputStream(f));
		
		/***When***/
		mockMvc.perform(fileUpload("/userForm").file(file)
						.param("userId", "userTest12")
						.param("name", "미카엘")
						.param("alias", "미카미카")
						.param("addr1", "대전광역시")
						.param("addr2", "중구")
						.param("zipcd", "12122")
						.param("birth", "2019-06-26")
						.param("pass", "test1234"))
				.andExpect(view().name("redirect:/userPagingList"));
		/***Then***/
		
	}
	
	/**
	 * 사용자 등록 테스트 (Fail 시나리오)
	 * @throws Exception
	 */
	@Test
	public void userFormPostFailTest() throws Exception {
		
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/Rachel1.JPG");
		MockMultipartFile file = new MockMultipartFile("profile", f.getName(),"",new FileInputStream(f));
		
		/***When***/
		mockMvc.perform(fileUpload("/userForm").file(file)
						.param("userId", "user18") // 이미 존재하는 아이디
						.param("name", "미카엘")
						.param("alias", "미카미카")
						.param("addr1", "대전광역시")
						.param("addr2", "중구")
						.param("zipcd", "12122")
						.param("birth", "2019-06-26")
						.param("pass", "test1234"))
				.andExpect(view().name("user/userForm"));
		/***Then***/
		
	}
	
	/**
	 * 사용자 사진 응답 테스트
	 * @throws Exception 
	 */
	@Test
	public void profileTest() throws Exception {
		mockMvc.perform(get("/profile").param("userId", "sally"))
		       							.andExpect(status().isOk());
		
	}
	
	/**
	 * 사용자 수정화면 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void userModifyGetTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/userModify").param("userId", "sally")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		UserVO userVo = (UserVO) mav.getModelMap().get("userVo");
		
		/***Then***/
		assertEquals("user/userModify", viewName);
		assertEquals(userVo.getName(), "샐리");
	}
	
	
	@Test
	public void userModifyPostTest() throws Exception {
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/Rachel1.JPG");
		MockMultipartFile file = new MockMultipartFile("profile", f.getName(),"",new FileInputStream(f));
		
		/***When***/
		mockMvc.perform(fileUpload("/userModify").file(file)
						.param("userId", "sally") 
						.param("name", "미카엘")
						.param("alias", "미카미카")
						.param("addr1", "대전광역시")
						.param("addr2", "중구")
						.param("zipcd", "12122")
						.param("birth", "2019-06-26")
						.param("pass", "test1234"))
				.andExpect(view().name("redirect:/user?userId=sally"));
		/***Then***/
		
	
	}
	
}
