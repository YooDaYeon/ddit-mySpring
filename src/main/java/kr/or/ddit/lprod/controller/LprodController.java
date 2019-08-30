package kr.or.ddit.lprod.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.lprod.service.LprodService;
import kr.or.ddit.paging.model.PageVO;

/**
 * Servlet implementation class LprodPagingController
 */
@RequestMapping("/lprod")
@Controller
public class LprodController {
      
	@Resource(name="lprodService")
	private ILprodService lprodService;
	
	
	@RequestMapping("/lprodPagingList")
	public String lprodPagingList(PageVO pageVo, Model model) {
		
		Map<String, Object> resultMap = lprodService.lprodPagingList(pageVo);
		List<LprodVO> lprodList = (List<LprodVO>) resultMap.get("lprodList");
		int paginationSize = (int) resultMap.get("paginationSize");
		
		model.addAttribute("lprodList",lprodList);
		model.addAttribute("paginationSize",paginationSize);
		model.addAttribute("pageVo",pageVo);
		
		return "lprod/lprod";
	}
	

}
