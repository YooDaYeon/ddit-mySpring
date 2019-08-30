package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.placeholder.DbInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-placeholder.xml")
public class SpringPlaceHolderTest {

	@Resource(name="dbInfo")
	private DbInfo dbInfo;
	

	@Test
	public void placeholderTest() {
		/***Given***/
		
		/***When***/

		/***Then***/
		assertEquals(dbInfo.getDriver() , "oracle.jdbc.driver.OracleDriver");
		assertEquals(dbInfo.getUrl(), "jdbc:oracle:thin:@192.168.0.113:1521:xe");
		assertEquals(dbInfo.getUsername(), "pc09");
		assertEquals(dbInfo.getPassword(), "java");
		assertTrue(dbInfo.getPassword().contains("java"));
		
	}

}
