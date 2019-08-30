package kr.or.ddit.prod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.prod.model.ProdVO;

@Repository
public class ProdDao implements IProdDao{

	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public List<ProdVO> prodListLgu(String prod_lgu) {
		List<ProdVO> list = sqlSession.selectList("prod.prodListLgu",prod_lgu);
		return list;
	}

	@Override
	public List<ProdVO> prodList() {
		List<ProdVO> list = sqlSession.selectList("prod.prodList");
		return list;
	}
	


}
