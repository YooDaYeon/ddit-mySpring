package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;

public interface IUserService {

	/**
	 * 사용자 전체 리스트 조회
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
	
	int updateUser(UserVO userVo);
	
	/**
	 * 
	* Method : userPagingList
	* 작성자 : PC09
	* 변경이력 :
	* @param pagevo
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	 */
	 Map<String, Object>  userPagingList(PageVO pagevo);
	
	
	
	int usersCnt();
	
}
