package kr.or.ddit.prod.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.Test;

import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.testenv.LogicTestEnv;

public class ProdServiceTest  extends LogicTestEnv{

	@Resource(name = "prodService")
	private IProdService prodService;

	@Test
	public void prodListLgu() {
		/***Given***/
		String prod_lgu = "P101";
		/***When***/
		List<ProdVO> list = prodService.prodListLgu(prod_lgu);
		/***Then***/
		assertEquals(list.size(), 6);
	}
	
	@Test
	public void prodList() {
		/***Given***/
		
		/***When***/
		List<ProdVO> list = prodService.prodList();
		
		/***Then***/
		assertEquals(list.size(), 74);
	}

}
