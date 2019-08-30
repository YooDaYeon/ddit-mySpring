package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.dao.IboardDao;
import kr.or.ddit.board.service.IboardService;
import kr.or.ddit.testenv.LogicTestEnv;

//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-st.xml")
public class SpringIocStTest extends LogicTestEnv{

	@Resource(name="bService")
	private IboardService boardService;
	@Resource(name="bDao")
	private IboardDao boardDao;
	
	@Test
	public void usetest() {
		/***Given***/
		
		/***When***/

		/***Then***/
		assertEquals(boardDao, boardService.getBoardDao());
		
	}

}
