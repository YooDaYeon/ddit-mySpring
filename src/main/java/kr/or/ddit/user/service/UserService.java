package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.UserVO;

@Service
public class UserService implements IUserService {

	@Resource(name="userDao")
	private IUserDao userDao;
	
	
	/**
	 * 전체 사용자 리스트 조회
	 */
	@Override
	public List<UserVO> userList() {
		return userDao.userList();
		
	}


	@Override
	public int insertUser(UserVO userVo) {
		return userDao.insertUser(userVo);
	}


	@Override
	public int deleteUser(String userId) {
		return userDao.deleteUser(userId);
	}


	@Override
	public UserVO getUser(String userId) {
		return userDao.getUser(userId);
	}


	@Override
	public int updateUser(UserVO userVo) {
		return userDao.updateUser(userVo);
	}


	@Override
	public Map<String, Object> userPagingList(PageVO pagevo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		//userList라는 아이디로 map에 저장해서 페이지 정보 넘김
		resultMap.put("userList",userDao.userPagingList(pagevo));
		
		//usersCnt --> paginationSize 변경
//		resultMap.put("usersCnt",userDao.usersCnt());
		int usersCnt = userDao.usersCnt();
		//pageSize --> pageVO.getPageSize();
		int paginationSize = (int)Math.ceil((double)usersCnt/pagevo.getPageSize());
		resultMap.put("paginationSize",paginationSize);
		
		return resultMap;
	}



	@Override
	public int usersCnt() {
		int cnt = userDao.usersCnt();
		return cnt;
	}

}
