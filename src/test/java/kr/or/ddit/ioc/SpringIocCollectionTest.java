package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.collection.IocCollection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-collection.xml")
public class SpringIocCollectionTest {

	@Resource(name="collectionBean")
	IocCollection iocCollection;
	
	
	
	@Test
	public void springIocCollectionTest() {
		/***Given***/
		
		/***When***/

		/***Then***/
		assertEquals("brown", iocCollection.getList().get(0));
		assertEquals("brown", iocCollection.getMap().get("name"));
		assertEquals(4, iocCollection.getSet().size());
		assertTrue("sally",iocCollection.getSet().contains("sally"));
		assertEquals("브라운", iocCollection.getProperties().get("name"));
	}

}
