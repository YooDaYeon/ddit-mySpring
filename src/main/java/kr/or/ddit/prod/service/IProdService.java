package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.prod.model.ProdVO;

public interface IProdService{

	/**
	 * 
	 * @param prod_lgu
	 * @return
	 * prod_lgu에 맞는 리스트 가져오기
	 */
	List<ProdVO> prodListLgu(String prod_lgu);
	
	/**
	 * 리스트 전체 가져오기
	 */
	List<ProdVO> prodList();
	
}
