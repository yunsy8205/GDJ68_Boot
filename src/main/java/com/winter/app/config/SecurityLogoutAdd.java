package com.winter.app.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.nimbusds.oauth2.sdk.Response;
import com.winter.app.board.PostVO;
import com.winter.app.member.MemberVO;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class SecurityLogoutAdd implements LogoutHandler{
   
	//@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
	private String adminKey = "53a6de0c0b7a00fdfdb2178acc9f9912";
	
   @Override
   public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
      // TODO Auto-generated method stub
   
      log.info("============= Log Out Add =======================");
      //this.logoutForKakao2(response);
      this.userWeblient();
      
   }
   
   public void logoutForKakao(Authentication authentication) {
      RestTemplate restTemplate = new RestTemplate();
      MemberVO memberVO = (MemberVO)authentication.getPrincipal();
      //log.info("--------> AccessToken {} ", memberVO.getAccessToken());
      
      org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
      headers.add("Content-Type", "application/x-www-form-urlencoded");
      //headers.add("Authorization", "Bearer "+memberVO.getAccessToken());
      headers.add("Authorization", "KakaoAK "+adminKey);
      
      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
      params.add("client_id","user_id");
      params.add("target_id", memberVO.getName());
   
      HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(params,headers);
      ResponseEntity<String> res = restTemplate.postForEntity("https://kapi.kakao.com/v1/user/logout", req, String.class);
   
      String result = res.getBody();
      
      log.info("==============로그아웃 ID {}", result);
      
   }
   
   //web-client
   public void userWeblient() {
	  WebClient webClient = WebClient.builder()
			  				.baseUrl("https://jsonplaceholder.typicode.com/")
			  				.build();
	  Mono<ResponseEntity<PostVO>> res = webClient.get()
	  		.uri("posts/1")
	  		.retrieve()
	  		.toEntity(PostVO.class);
	  
	  PostVO postVO = res.block().getBody();
	  
	  log.info("+++ WebClient {} ", postVO);

   }
   
   //카카오계정과 함께 로그아웃
   public void logoutForKakao2(HttpServletResponse response) {
	   //RestTemplate restTemplate = new RestTemplate();// 동기적 요청
	   StringBuffer sb = new StringBuffer();
	   sb.append("https://kauth.kakao.com/oauth/logout?");
	   sb.append("client_id=");
	   sb.append("804b3cf0cf95130500b38827e8883948");
	   sb.append("&logout_redirect_uri=");
	   sb.append("http://localhost:82/member/kakaoLogout");
	   
	   //header가 없다.
	   //ResponseEntity<String> res=restTemplate.getForEntity(sb.toString(), String.class);
	   //log.info("+++++++++ 카카오계정과 함께 로그아욱 : {}", res.getBody());
	   
	   try {
		response.sendRedirect(sb.toString());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   
   public void userRestTemplate() {
      
      RestTemplate restTemplate = new RestTemplate();
      
      //파라미터 
      
//      post방식
      MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
      params.add("postId", "1");
      
      
      HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(params,null);
      // 결과가 하나일 떄
//      ResponseEntity<PostVO> res =  restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/1", PostVO.class, req);
//   
//      PostVO result = res.getBody(); // res타입을 string로 선언했기 떄문에 getBody의 타입도 string이다 // PostVO를 선언해서 PostVO타입으로 선언
      
      //결과가 여러개 일 때
      //List<PostVO> res =  restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", List.class, req);
      //get방식
      //ResponseEntity<String> res = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/comments?postId=1", String.class, req);
      
      //log.info("**** JSON : {} ****",res);
   }
   
}