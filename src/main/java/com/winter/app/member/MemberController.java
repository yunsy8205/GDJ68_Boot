package com.winter.app.member;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member/*")
public class MemberController {
	
	//service
	@Autowired
	private MemberService memberService;
	
	@GetMapping("info")
	public void getInfo()throws Exception{
		//DB에서 사용자 정보를 조회 해서 JSP로 보냄
		//로그인시 세션에 담는 것은 최소한으로 하는 것이 좋고 불러올 때는 DB에서 불러오는 것이 좋다.
	}
	
	@GetMapping("update")
	public void setUpdate(@AuthenticationPrincipal MemberVO memberVO, Model model)throws Exception{
//		MemberVO memberVO = (MemberVO)principal;
		//세션보다는 DB를 수정하는 것이기 때문에 DB에서 불러와서 사용하는 것이 좋다.
		//세션의 정보도 같이 바꿔줘야 함
		//memberVO = memberService.getLogin(memberVO);
		
		MemberInfoVO memberInfoVO = new MemberInfoVO();
		memberInfoVO.setName(memberVO.getName());
		memberInfoVO.setBirth(memberVO.getBirth());
		memberInfoVO.setEmail(memberVO.getEmail());
		
		model.addAttribute("memberInfoVO", memberInfoVO);
	}
	
	@PostMapping("update")
	public String setUpdate(@Valid MemberInfoVO memberInfoVO,BindingResult bindingResult, MultipartFile photo)throws Exception{
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MemberVO memberVO = (MemberVO) obj;
		memberVO.setEmail("UpdateEmail@naver.com");
		
		return "redirect:/";
	}
	
	@GetMapping("logout")
	public String getLogout(HttpSession session)throws Exception{
		//세션을 소멸시킨다.
		session.invalidate();//세션 유지시간을 0으로 만듬
		
		return "redirect:../";
	}
	
	@GetMapping("login")
	public String getLogin(@ModelAttribute MemberVO memberVO)throws Exception{
		SecurityContext context = SecurityContextHolder.getContext();
		
		String check = context.getAuthentication().getPrincipal().toString();
		
		log.info("=========Name : {} =========", context.getAuthentication().getName());
		
		if(!check.equals("anonymousUser")) {
			return "redirect:/";
		}
		
		return "member/login";
	}
	
//	@PostMapping("login")
//	public String getLogin2(MemberVO memberVO, HttpSession session)throws Exception{
//		memberVO=memberService.getLogin(memberVO);
//		
//		if(memberVO != null) {
//			System.out.println("로그인 성공");
//			session.setAttribute("member", memberVO);
//			return "redirect:../";
//		}
//		return "redirect:./login";
//	}
	
//	@GetMapping("join")
//	public String setJoin(Model model) throws Exception{
//		MemberVO memberVO = new MemberVO();
//		model.addAttribute("memberVO", memberVO);
//		
//		return "/member/join";
//	} 아래와 같은 의미
	
	@GetMapping("join")
	public String setJoin(@ModelAttribute MemberVO memberVO) throws Exception{
		// 모델에 들어가는 키 이름이 "memberVO"가 됨(앞글자 소문자)
		return "/member/join";
	}
	
	@PostMapping("join")
	public String setjoin(@Valid MemberVO memberVO,BindingResult bindingResult, MultipartFile photo)throws Exception{
		//멤버VO를 검증할 거면 바로 뒤에 BindingResult가 있어야 한다.
		//결과를 BindingResult에 담음
		//나머지는 검증했고 다음에 비번 검증
		//Valid는 컨트롤러에서만 사용가능
		
		boolean check = memberService.getMemberError(memberVO, bindingResult);
		
		if(bindingResult.hasErrors()||check) {//어노테이션 검증 에러 또는 비번확인 검증 에러 (둘중 하나만 에러가 있어도 조인으로 재이동)
			return "member/join";
		}
		
		//회원가입 진행
		int result = memberService.setJoin(memberVO);
	
		
		log.info("photo:{}----size:{}",photo.getOriginalFilename(), photo.getSize());
		return "redirect:../";
	}
}
