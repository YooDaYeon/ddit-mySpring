package kr.or.ddit.lprod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;

@Repository
public class LprodDao implements ILprodDao {

	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<LprodVO> lprodPagingList(PageVO pagevo) {
		return sqlSession.selectList("lprod.lprodPagingList",pagevo);
	}

	@Override
	public int lprodCnt() {
		return sqlSession.selectOne("lprod.lprodCnt");
	}

}
