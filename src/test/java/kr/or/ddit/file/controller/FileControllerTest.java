package kr.or.ddit.file.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testenv.ControllerTestEnv;

public class FileControllerTest extends ControllerTestEnv{

	/**
	 * uploadView 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void uploadViewTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/file/uploadView")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("upload/upload", viewName);
		
	}
	
	/**
	 * 파일 업로드 테스트
	 * @throws Exception 
	 * @throws IOException 
	 */
	@Test
	public void uploadTest() throws IOException, Exception {
		/***Given***/
		File currentFolder = new File(""); 
		File file = new File("src/test/resources/kr/or/ddit/testenv/Rachel1.jpg");
		
		
		FileInputStream fis = new FileInputStream(file);
		MockMultipartFile mockMultipartFile = new MockMultipartFile("img", file.getName(),"",fis);
		
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(fileUpload("/file/upload")
									  .file(mockMultipartFile))
									  .andReturn();
								
								
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		String msg = (String) mav.getModelMap().get("msg");
		
		/***Then***/
		assertEquals("SUCCESS", msg);
		assertEquals("upload/upload", viewName);
	}

}
