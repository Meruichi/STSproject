package com.cos.blog.controller;

import java.util.UUID;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// auth : 인증이 안된사용자들이 출입할수있는 경로
// 그냥 주소가 / => index.jsp 허용
// static이하에있는
@Controller
public class UserController {

	@Value("${cos.key}")
	private String cosKey;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	// @ResponseBody  @ResponseBody : Data를 리턴해주는 컨트롤러 함수를 만듬
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) {
		
		// post방식으로 key=value데이터를 요청 (카카오톡으로)
		// HttpsURLConnection url = new HttpsURLConnection(){어쩌구}; 옛날방식
		// Retrofit2, OkHttp, RestTemplte
		
		RestTemplate rt = new RestTemplate();
		
		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "d7f59147ec0a803bb11ddbd2a8561876"); // 변수화시켜서 사용 권장
		params.add("redirect_uri", "http://localhost:9090/auth/kakao/callback");
		params.add("code", code);
		
		// 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
				new HttpEntity<>(params, headers);
		
		// Http요청하기
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token", // 토큰발급 요청주소
				HttpMethod.POST, // 방식
				kakaoTokenRequest, // 엔티티
				String.class // 응답받을 타입
				);

		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		// System.out.println("카카오 액세스 토큰 : " + oauthToken.getAccess_token());
		
		
		RestTemplate rt2 = new RestTemplate();
		
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = 
				new HttpEntity<>(headers2);
		
		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me", 
				HttpMethod.POST, 
				kakaoProfileRequest,
				String.class
				);
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		/*
		 * //카카오 아디이의 정보를 통해 간단하게 회원가입 System.out.println("카카오 아이디(번호): " +
		 * kakaoProfile.getId() ); System.out.println("카카오 이메일: " +
		 * kakaoProfile.getKakao_account().getEmail());
		 * 
		 * System.out.println("카카오 유저네임 : " + kakaoProfile.getKakao_account().getEmail()
		 * + "_" + kakaoProfile.getId() ); System.out.println("블로그서버 이메일 : " +
		 * kakaoProfile.getKakao_account().getEmail() );
		 */
		User kakaoUser = User.builder()
				.username(kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId())
				.password(cosKey)
				.email(kakaoProfile.getKakao_account().getEmail())
				.oauth("kakao")
				.build();
		
		//이미 가입자인지 체크해서 처리
		User originUser = userService.회원찾기(kakaoUser.getUsername());
		if(originUser.getUsername() == null) {
			System.out.println("기존회원이 아님(자동 회원가입 진행)");
			userService.회원가입(kakaoUser); // 미가입자인 경우 위의 간단한 회원가입 처리
		}

		// 자동 로그인처리
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey)
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);			
		// 카카오를 이용한 회원가입을 이미 했다면 비밀번호는 그대로 cosKey로 되어있을것이고, 
		// 카카오로 새로가입했더라도 cosKey가 될것이다. 그러므로 cosKey가 유출된다면
		// 카카오로 가입한 유저들의 웹사이트의 비밀번호가 전부 유출된다는것. 따라서 cosKey의 보안이 중요함.
		// 일단 유저는 웹사이트의 회원정보수정으로 비밀번호를 변경가능함
		// 근데 비밀번호를 변경하면 다음번 카카오로그인시 자동으로 cosKey를 이용해서 가입하므로,
		// 회원정보 수정을 막는게 좋음
		
		return "redirect:/";
		// return response2.getBody();
		// response.getHeaders(), response.getBody() 나눠서 확인 가능
	}
	
	@GetMapping("/user/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}
}
