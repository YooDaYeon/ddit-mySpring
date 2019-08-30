package kr.or.ddit.prod.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.prod.dao.IProdDao;
import kr.or.ddit.prod.model.ProdVO;

@Service
public class ProdService implements IProdService{

	@Resource(name="prodDao")
	private IProdDao dao ;

	@Override
	public List<ProdVO> prodListLgu(String prod_lgu) {
		List<ProdVO> list = dao.prodListLgu(prod_lgu);
		return list;
	}

	@Override
	public List<ProdVO> prodList() {
		List<ProdVO> list = dao.prodList();
		return list;
	}
	

}
