package com.winter.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService {

	//DAO
	@Autowired
	private MemberDAO memberDAO;
	
//	public void testValid(MemberVO memberVO, BindingResult bindingResult) {
//		log.info("test Valid");
//	}
	
	//login
	public MemberVO getLogin(MemberVO memberVO)throws Exception{
		MemberVO loginVO = memberDAO.getMember(memberVO);
		
		if(loginVO == null) {
			return loginVO;
		}
		
		if(loginVO.getPassword().equals(memberVO.getPassword())) {
			return loginVO;
		}
		return null; //로그인 안된 표시
	}
	
	//검증메서드
	public boolean getMemberError(MemberVO memberVO, BindingResult bindingResult)throws Exception{
		boolean check=false; //error가 없다, true면 검증 실패 error가 있다.
		
		//password 일치 검증
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
			check=true; //check=!check
			
			bindingResult.rejectValue("passwordCheck", "memberVO.password.equalCheck");
			//errorCode는 프로퍼티스 파일에 입력할 키 (검증 어노테이션이 없어서 넣을 수 없다.)
			
		}
		
		//ID 중복 검사
		
		MemberVO checkVO = memberDAO.getMember(memberVO);
		
		if(checkVO != null) {
			check=true;//검증 실패 이때는 !check 하면 안됨
			bindingResult.rejectValue("username", "memberVO.username.equalCheck");
		}
		
		return check;
	}
}
