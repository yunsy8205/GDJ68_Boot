package com.winter.app.member;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO extends MemberInfoVO{
	
	@NotBlank //비어 있으면 안됨
	@Size(max = 12, min = 2)// 순서를 바꿔도 고정으로 max 1번, min 2번이다.
	private String username;
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\\\W)(?=\\\\S+$).{6,12}", message = "다른 비번을 입력해주세요")
	private String password;//메세지에 입력하면 디폴트 값이 아닌 입력한 값이 나옴 (다국어는 지원되지 않음)
	private String passwordCheck;
	
}
