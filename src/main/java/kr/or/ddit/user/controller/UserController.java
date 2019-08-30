package kr.or.ddit.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.model.UserVoValidator;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.PartUtil;

@RequestMapping("/user")
@Controller
public class UserController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Resource(name="userService")
	private IUserService userService;
	
	/**
	 * 사용자 전체 리스트
	 * @param model
	 * @return
	 */
	//localhost/user/userList
	@RequestMapping("/list")
	public String userList(Model model) {
		//userList객체를 userList.jsp에서 참고할 수 있도록 request객체에 속성으로 넣어준다
		//request.setAttrivute("userList",userService.userList());
		//userList객체를 이용해 사용자 화면을 생성하는 jsp
		//request.getRequestDispatcher("/user/userList.jsp").forward(request,response);
		model.addAttribute("userList",userService.userList());
		
		return "user/userList";
	}
	
	@RequestMapping("/userListExcel")
	public String userListExcel(Model model) {
		List<String> header = new ArrayList<String>();
		header.add("userId");
		header.add("name");
		header.add("alias");
		header.add("addr1");
		header.add("addr2");
		header.add("zipcd");
		header.add("birth");
		
		model.addAttribute("header",header);
		model.addAttribute("data",userService.userList());
		
		return "userExcelView";
	}
	
	/**
	 * 사용자 페이징 리스트
	 */
	@RequestMapping("/pagingList")
//	public String userPagingList(@RequestParam(name="page",defaultValue="1")int page, 
//								 @RequestParam(name="pageSize",defaultValue="10")int pageSize,
//								 Model model) {
//		
//		PageVO pageVo = new PageVO(page,pageSize);
	public String userPagingList(PageVO pageVo,Model model) { 
								 
		logger.debug("pageVo : {}", pageVo);
		Map<String, Object> resultMap = userService.userPagingList(pageVo);
		List<UserVO> userList = (List<UserVO>) resultMap.get("userList");
		int paginationSize = (int) resultMap.get("paginationSize");
		
		model.addAttribute("userList",userList);
		model.addAttribute("paginationSize",paginationSize);
		model.addAttribute("pageVO",pageVo);
		return "user/userPagingList";
	}
	
	/**
	 * 사용자 상세조회
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping("/user")
	public String user(String userId, Model model) {
		UserVO userVo = userService.getUser(userId);
		model.addAttribute("userVo",userVo);
		
		return "user/user";
	}
	
	/**
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping("/userJson")
	public View userJson(String userId, Model model) {
		
		ModelAndView mav = new ModelAndView("jsonView");
		mav.setViewName("jsonView");
		mav.setView(new MappingJackson2JsonView());
		UserVO userVo = userService.getUser(userId);
		model.addAttribute("userVo",userVo);
		
		return new MappingJackson2JsonView();
	}
	/**
	 * 사용자 등록화면
	 * @return
	 */
	@RequestMapping(path = "/form", method = RequestMethod.GET)
	public String userForm() {
		return "user/userForm";
	}
	
	/**
	 * 사용자 등록
	 * @param userVo
	 * @param userId
	 * @param profile
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/form",  method = RequestMethod.POST)
	public String userFormJsr(UserVO userVo,BindingResult result, String userId, MultipartFile profile, Model model)  {
		
		new UserVoValidator().validate(userVo, result);
		
		if(result.hasErrors()) {
			return "user/userForm";
		}
		
		String viewName="";

		UserVO dbUser = userService.getUser(userVo.getUserId());
		
		
		if(dbUser == null) {
			if(profile.getSize() > 0) {
				String filename = profile.getOriginalFilename();
				String ext = PartUtil.getExt(filename);
				
				String uploadPath = PartUtil.getUpLoadPath();
				String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
				userVo.setPath(filePath);
				userVo.setFilename(filename);
				
				try {
					profile.transferTo(new File(filePath));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
			int insertCnt = userService.insertUser(userVo);
			
			if(insertCnt == 1)
				viewName = "redirect:/user/userPagingList";
			
		}else {
			model.addAttribute("msg","이미 존재하는 사용자 입니다");
			viewName =  userForm();
		}
		
		
		return viewName;
	}
	
	/**
	 * 사용자 사진 응답 생성
	 * @param userId
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/profile")
	public String profile(String userId, Model model) throws IOException {
		   // 사용자 정보를 조회 
		  UserVO userVo = userService.getUser(userId);
		  model.addAttribute("userVo",userVo);
		  
		 return "profileView";
	}
	
	/**
	 * 사용자 수정 화면 요청
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/modify", method = RequestMethod.GET)
	public String userModify(String userId, Model model) {
		UserVO userVo = userService.getUser(userId);
		model.addAttribute("userVo",userVo);
		
		return "user/userModify";
	}
	
	@RequestMapping(path = "/modify", method = RequestMethod.POST)
	public String userModify(UserVO userVo, MultipartFile profile, HttpSession session, Model model,
							RedirectAttributes redirectAttributes) {
		//추후 ajax 요청으로 분리
		//userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
		
		if(profile.getSize() > 0) {
			String filename = profile.getOriginalFilename();
			String ext = PartUtil.getExt(filename);
			
			String uploadPath = PartUtil.getUpLoadPath();
			
			String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
			logger.debug("modify userVo : {}", userVo);
			
			userVo.setPath(filePath);
			userVo.setFilename(filename);
			try {
				profile.transferTo(new File(filePath));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
		int updateCnt = userService.updateUser(userVo);
		
		if(updateCnt == 1) {
			//session.setAttribute("msg", "등록되었습니다");
			redirectAttributes.addFlashAttribute("msg","등록되었습니다");
			redirectAttributes.addAttribute("userId",userVo.getUserId()); //파라미터를 자동으로 붙여준다 redirect:/user/user?userId=
			return "redirect:/user/user";
		}else {
			return userModify(userVo.getUserId(), model);
		}
		
	}
	
	
	/**
	 * 사용자 등록
	 * @param userVo
	 * @param userId
	 * @param profile
	 * @param model
	 * @return
	 */
	//@RequestMapping(path = "/form",  method = RequestMethod.POST)
	public String userForm(UserVO userVo,BindingResult result, String userId, MultipartFile profile, Model model)  {
		
		new UserVoValidator().validate(userVo, result);
		
		if(result.hasErrors()) {
			return "user/userForm";
		}
		
		String viewName="";

		UserVO dbUser = userService.getUser(userVo.getUserId());
		
		
		if(dbUser == null) {
			if(profile.getSize() > 0) {
				String filename = profile.getOriginalFilename();
				String ext = PartUtil.getExt(filename);
				
				String uploadPath = PartUtil.getUpLoadPath();
				String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
				userVo.setPath(filePath);
				userVo.setFilename(filename);
				
				try {
					profile.transferTo(new File(filePath));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
			int insertCnt = userService.insertUser(userVo);
			
			if(insertCnt == 1)
				viewName = "redirect:/user/userPagingList";
			
		}else {
			model.addAttribute("msg","이미 존재하는 사용자 입니다");
			viewName =  userForm();
		}
		
		
		return viewName;
	}
	
}
