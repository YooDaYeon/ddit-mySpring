package kr.or.ddit.prod.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.prod.service.IProdService;

@RequestMapping("/prod")
@Controller
public class ProdController {

	@Resource(name = "prodService")
	private IProdService prodService;
	
	private static final Logger logger = LoggerFactory.getLogger(ProdController.class);
	
	@RequestMapping(path = "/prodList", method=RequestMethod.GET)
	public String prodList(Model model) {
		
		List<ProdVO> prodList = prodService.prodList();
		
		model.addAttribute("prodList",prodList);
		return "prod/prod";
	}
	
	@RequestMapping(path = "/prodList", method=RequestMethod.POST)
	public String prodList(String prodLgu, Model model) {
		List<ProdVO> prodList;
		logger.debug("prod_lgu",prodLgu);
		if(prodLgu.equals("all")) {
			prodList = prodService.prodList();
		}else {
			prodList = prodService.prodListLgu(prodLgu);
		}
		model.addAttribute("prodList",prodList);
		model.addAttribute("prodLgu",prodLgu);
		
		return "prod/prod";
	}
	
	
}
