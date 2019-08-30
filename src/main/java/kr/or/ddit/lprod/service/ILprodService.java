package kr.or.ddit.lprod.service;

import java.util.Map;

import kr.or.ddit.paging.model.PageVO;

public interface ILprodService {

	
	Map<String, Object> lprodPagingList(PageVO pagevo);
	
	int lprodCnt();
}
