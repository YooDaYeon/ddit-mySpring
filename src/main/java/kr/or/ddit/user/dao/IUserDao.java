package kr.or.ddit.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;

public interface IUserDao {

	/**
	 * 전체 사용자 조회
	 * @return
	 */
	List<UserVO> userList();
	
	/**
	 * 
	* Method : insertUser
	* 작성자 : PC09
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 등록
	 */
	int insertUser(UserVO userVo);
	
	/**
	 * 
	* Method : deleteUser
	* 작성자 : PC09
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 삭제
	 */
	int deleteUser(String userId);
	
	/**
	 * 사용자 정보 조회
	 * @param userId
	 * @return
	 */
	UserVO getUser(String userId);
	
	/**
	 * 
	* Method : userPagingList
	* 작성자 : PC09
	* 변경이력 :
	* @param pagevo
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	 */
	List<UserVO> userPagingList(PageVO pagevo);
	
	/**
	 * 
	* Method : updateUser
	* 작성자 : PC09
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 수정
	 */
	int updateUser(UserVO uservo);

	/**
	 * 
	* Method : userListForPassEncrypt
	* 작성자 : PC09
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 비밀번호 암호화 적용대상 사용자 전체 조회
	 */
	List<UserVO> userListForPassEncrypt(SqlSession sqlSession);

	/**
	 * 
	* Method : updateUserEncryptPass
	* 작성자 : PC09
	* 변경이력 :
	* @param sqlSession
	* @param uservo
	* @return
	* Method 설명 : 사용자 비밀번호 암호화 적용
	 */
	int updateUserEncryptPass(SqlSession sqlSession, UserVO uservo);
	
	/**
	 * 
	* Method : usersCnt
	* 작성자 : PC09
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체수 조회
	 */
	int usersCnt();
	
	
}
