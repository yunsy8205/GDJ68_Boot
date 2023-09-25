package com.winter.app.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService implements UserDetailsService{

	//DAO
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 비밀번호도 알아서 확인해줌 (필터)
		// 둘 다 맞으면 세션에 저장해줌!
		log.info("============== 로그인 시도 중 ===============");
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		try {
			memberVO=memberDAO.getMember(memberVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			memberVO=null;
		}
		return memberVO;
	}
	
//	public void testValid(MemberVO memberVO, BindingResult bindingResult) {
//		log.info("test Valid");
//	}
	
	//login
//	public MemberVO getLogin(MemberVO memberVO)throws Exception{
//		MemberVO loginVO = memberDAO.getMember(memberVO);
//		
//		if(loginVO == null) {
//			return loginVO;
//		}
//		
//		if(loginVO.getPassword().equals(memberVO.getPassword())) {
//			return loginVO;
//		}
//		return null; //로그인 안된 표시
//	}
	
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
	
	//AOP이용해 트랜젝션 해줌, 메서드위 또는 클래스에 트랜젝션을 호출할 수 있다.
	@Transactional(rollbackFor = Exception.class)
	public int setJoin (MemberVO memberVO) throws Exception{
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		int result = memberDAO.setJoin(memberVO);
		Map<String, Object> map = new HashMap<>();
		map.put("roleNum", 3); //멤버가 기본값
		map.put("username", memberVO.getUsername());
		result = memberDAO.setMemberRole(map);
		
		return result;
	}
}
