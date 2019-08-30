package kr.or.ddit.lprod.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.Test;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVO;

public class LprodServiceTest extends LogicTestEnv{

	@Resource(name="lprodService")
	private ILprodService lprodService;
	
	@Test
	 public void  lprodPagingListTest(){
			
		/***Given***/
		PageVO pagevo = new PageVO(1, 10);
		
		
		/***When***/
		Map<String, Object> resultMap = lprodService.lprodPagingList(pagevo);
		List<UserVO> userlist= (List<UserVO>) resultMap.get("lprodList");
		int paginationSize = (Integer) resultMap.get("paginationSize");

		/***Then***/
		//pagingList assert
		assertNotNull(userlist);
		assertEquals(10, userlist.size());
		
		//usersCnt assert
		assertEquals(2, paginationSize);
	 }

}
