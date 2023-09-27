package com.winter.app.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService extends DefaultOAuth2UserService implements UserDetailsService{

	//DAO
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//Social Login
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// userRequest에 로그인한 정보를 가지고 있다.
		
		log.info("==========Social Login 처리 진행===========");
		ClientRegistration clientRegistration = userRequest.getClientRegistration();
		log.info("====================={}=======================", clientRegistration); // 제공자 정보
		
		OAuth2User auth2User= super.loadUser(userRequest);// userRequest에서 유저정보 확인
		//super을 지우면 안된 부모의 loadUser을 사용하겠다는 것인데 지우면 자기자신을 계속 호출해서 무한 방복하게 된다.
		log.info("==================Auth2User : {}====================", auth2User);
		
		String social = clientRegistration.getRegistrationId();
		
		if(social.equals("kakao")) {
			auth2User = this.forKakao(auth2User);
		}
		
		return auth2User;
	}
	
	private OAuth2User forKakao(OAuth2User auth2User){
		//위 메서드에서 꺼낸 것을 받기 위해
		MemberVO memberVO = new MemberVO();
		//memberVO.setUsername();//소셜 로그인 하면 id대신 숫자가 오는데 대신 이름을 넣어준다.
		LinkedHashMap<String, Object> map = auth2User.getAttribute("properties");
		log.info("*********** {} **************", auth2User.getAttribute("properties").toString());
		
		LinkedHashMap<String, Object> map2  = auth2User.getAttribute("kakao_account");
		LinkedHashMap<String, Object> profile = (LinkedHashMap<String, Object>)map2.get("profile");
		
		log.info("NickName : {} =======", profile.get("nickname"));
		log.info("Image : {} =======", profile.get("profile_image_url"));
		log.info("Email : {} =======", map2.get("email"));
		log.info("birth : {} =======", map2.get("birthday"));
		
		String birth = map2.get("birthday").toString();
		String m = birth.substring(0, 2);
		String d = birth.substring(2);//시작인덱스만 넣으면 끝까지 나온다.
		Calendar ca = Calendar.getInstance();
		int y = ca.get(Calendar.YEAR);
		StringBuffer sb = new StringBuffer();//더하기 클래스
//		sb.append(y);
//		sb.append("-");
//		sb.append(m);
//		sb.append("-");
//		sb.append(d);
		sb.append(y).append("-").append(m).append("-").append(d);
 		
		memberVO.setUsername(map.get("nickname").toString());
		memberVO.setName(map.get("nickname").toString());
		memberVO.setEmail(map2.get("email").toString());
		memberVO.setBirth(Date.valueOf(sb.toString()));
		
		memberVO.setAttributes(auth2User.getAttributes());
		
		List<RoleVO> list = new ArrayList<>();
		RoleVO roleVO = new RoleVO();
		roleVO.setRoleName("ROLE_MEMBER");//DB에 저장 안하는 기준 나중에는 넣어줘야 한다.
		
		list.add(roleVO);
		
		memberVO.setRoleVOs(list);
	
		return memberVO;
	}
	
	//Server Login
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
