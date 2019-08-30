package kr.or.ddit.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;

@Repository
public class UserDao implements IUserDao {
	
	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;

	/**
	 * 사용자 전체 리스트 조회
	 */
	@Override
	public List<UserVO> userList() {
		
		return sqlSession.selectList("user.userList");
	}

	@Override
	public int insertUser(UserVO userVo) {
		return sqlSession.insert("user.insertUser",userVo);
	}

	@Override
	public int deleteUser(String userId) {
		return sqlSession.delete("user.deleteUser",userId);
	}

	@Override
	public UserVO getUser(String userId) {
		return sqlSession.selectOne("user.getUser",userId);
	}

	@Override
	public int updateUser(UserVO userVo) {
		int cnt = sqlSession.update("user.updateUser",userVo);
		
		return cnt;
	}

	@Override
	public List<UserVO> userListForPassEncrypt(SqlSession sqlSession) {
		return sqlSession.selectList("user.userListForPassEncrypt");
	}

	@Override
	public int updateUserEncryptPass(SqlSession sqlSession, UserVO uservo) {
		int cnt = sqlSession.update("user.updateUserEncryptPass",uservo);
		return cnt;
	}

	@Override
	public List<UserVO> userPagingList(PageVO pagevo) {
		List<UserVO> userlist = sqlSession.selectList("user.userPagingList",pagevo);
		return userlist;
	}

	@Override
	public int usersCnt() {
		int cnt = sqlSession.selectOne("user.usersCnt");
		return cnt;
	}
	
	
	
	
	
	
	
	
}
