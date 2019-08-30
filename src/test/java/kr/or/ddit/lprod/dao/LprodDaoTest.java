package kr.or.ddit.lprod.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.LogicTestEnv;

public class LprodDaoTest extends LogicTestEnv{

	@Resource(name="lprodDao")
	private ILprodDao lprodDao;

	@Test
	public void lprodPagingListTest() {
		/***Given***/
		PageVO pagevo = new PageVO(1,10);
		
		/***When***/
		List<LprodVO> lprodlist= lprodDao.lprodPagingList(pagevo);
		

		/***Then***/
		assertNotNull(lprodlist);
		//assertEquals(10, lprodlist.size());
	}

}
