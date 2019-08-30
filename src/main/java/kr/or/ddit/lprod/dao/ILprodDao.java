package kr.or.ddit.lprod.dao;

import java.util.List;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;

public interface ILprodDao {

	List<LprodVO> lprodPagingList(PageVO pagevo);
	
	int lprodCnt();
}
