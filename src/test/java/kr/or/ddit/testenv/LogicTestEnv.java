package kr.or.ddit.testenv;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:kr/or/ddit/config/spring/root-context.xml",
	"classpath:kr/or/ddit/config/spring/application-datasource-dev.xml",
	"classpath:kr/or/ddit/config/spring/application-transaction.xml"})
public class LogicTestEnv {

	@Resource(name="datasource")
	private DataSource datasource;
	
	@Before
	public void setup() {
		ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
		rdp.setContinueOnError(false); //false : 에러나면 정지하기
		rdp.addScript(new ClassPathResource("kr/or/ddit/testenv/dbInit.sql"));
		
		DatabasePopulatorUtils.execute(rdp, datasource);
	}
	
	@Ignore
	@Test
	public void dd() {};
	
	
	
	
	
	
	
	
}
