package kr.or.ddit.user.dao;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVO;

public class UserDaoTest extends LogicTestEnv{

	@Resource(name="userDao")
	private IUserDao userDao;
	
	/**
	 * 사용자 전체 리스트 조회 테스트
	 */
	@Test
	public void userListtest() {
		/***Given***/
		
		/***When***/
		List<UserVO> list = userDao.userList();
		
		/***Then***/
		assertNotNull(list);
		assertTrue(list.size() > 100);
		
		
	}
	
	/**
	 * 
	* Method : insertUserTest
	* 작성자 : PC09
	* 변경이력 :
	* Method 설명 : 사용자 등록 테스트
	 * @throws ParseException 
	 */
	@Test
	public void insertUserTest() throws ParseException {
		/***Given***/
		//사용자 정보를 담고 있는 vo객체 준비
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		UserVO uservo = new UserVO("감자", "userTest1", "감자사랑", "test123", "서울", "뭐시기", "123", sdf.parse("2019-05-31"));
		/***When***/
		//userDao.insertUser()
		//int cnt = userDao.insertUser(uservo);
		
		/***Then***/
		//insertCnt(1)
		//assertEquals(1,cnt);
		
		//data 삭제
		//userDao.deleteUser(uservo.getUserId());
		
	}
	
	/**
	 * 사용자 정보 조회 테스트
	 */
	@Test
	public void getUserTest() {
		/***Given***/
		String userId = "sally";
		/***When***/
		UserVO uservo = userDao.getUser(userId);
		
		/***Then***/
		assertEquals("샐리", uservo.getName());
				
	}
	
	
	/**
	 * 
	* Method : userPagingList
	* 작성자 : PC09
	* 변경이력 :
	* @param pagevo
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	 */
	@Test
	public void userPagingListTest(){
		/***Given***/
		PageVO pagevo = new PageVO(1,10);
		
		/***When***/
		List<UserVO> userlist= userDao.userPagingList(pagevo);
		

		/***Then***/
		assertNotNull(userlist);
		assertEquals(10, userlist.size());
	};
	
	/**
	 * 
	* Method : updateUser
	* 작성자 : PC09
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 수정
	 * @throws ParseException 
	 */
	@Test
	public void updateUserTest() throws ParseException {
		/***Given***/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		UserVO uservo = new UserVO("감자1", "userTest", "감자사랑", "test123", "서울", "뭐시기", "123", sdf.parse("2019-05-31"));
		
		/***When***/
		int cnt = userDao.updateUser(uservo);

		/***Then***/
		assertEquals(cnt, 1);
	};

	@Test
	public void usersCntTest() {
		/***Given***/
		
		/***When***/
		int cnt = userDao.usersCnt();
		/***Then***/
		assertEquals(cnt, 111);
		
	}
	
	

}
