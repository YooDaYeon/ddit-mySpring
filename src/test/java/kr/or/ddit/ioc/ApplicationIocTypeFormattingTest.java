package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.typeConvert.model.FormattingVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-type-formatting.xml")
public class ApplicationIocTypeFormattingTest {

	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationIocTypeFormattingTest.class);
	@Resource(name="formattingVo")
	FormattingVO vo;
	
	@Test
	public void test() {
		/***Given***/
		
		/***When***/
//		Date reg_dt1 = vo.getMod_dt();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String reg_dt = sdf.format(reg_dt1);
//		
//		Date mod_dt1 = vo.getMod_dt();
//		SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
//		String mod_dt = sdf1.format(mod_dt1);
		logger.debug("vo.getMod_dt( : {}",vo.getMod_dt());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
		String reg_dt = sdf.format(vo.getReg_dt());
		String mod_dt = sdf1.format(vo.getMod_dt());
		

		/***Then***/
		assertNotNull(vo);
		assertEquals(reg_dt, "2019-06-21");
		assertEquals(mod_dt, "06-21-2019");
		assertEquals(6000, vo.getNumber()); //"6,000"의 문자열을 숫자로 바꿔주기
	}

}
