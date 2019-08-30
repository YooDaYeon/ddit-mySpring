package kr.or.ddit.lprod.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.ControllerTestEnv;

public class LprodControllerTest extends ControllerTestEnv{

	/**
	 * lprod 페이징 리스트 테스트
	 * @throws Exception 
	 */
	@Test
	public void userPagingListTest() throws Exception {
		/***Given***/
		
		
		/***When***/
		MvcResult mvcResult =  mockMvc.perform(get("/lprod/lprodPagingList")
												.param("page", "2")
												.param("pageSize", "10"))
												.andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<LprodVO> lprodList = (List<LprodVO>) mav.getModelMap().get("lprodList");
		int paginationSize = (int) mav.getModelMap().get("paginationSize");
		PageVO pageVo = (PageVO) mav.getModelMap().get("pageVo");
		
		/***Then***/
		//viewName
		//속성 userList, paginationSeize, pageVo
		assertEquals("lprod/lprod", viewName);
		assertEquals(7, lprodList.size());
		assertEquals(2, paginationSize);
		assertEquals(2, pageVo.getPage());
		assertEquals(10, pageVo.getPageSize());
	}

}
