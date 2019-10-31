package com.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.service.JwtService;
import com.ssafy.service.MemberService;
import com.ssafy.vo.Jwt;
import com.ssafy.vo.Member;

@CrossOrigin
@RestController
public class LoginController {
	Logger logger = LoggerFactory.getLogger("WebMobile.logger");

	@Autowired
	MemberService mService;
	@Autowired
	JwtService jwtService;  

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = { "application/json;charset=euc-kr" })
	public Map login(@RequestBody Member member) {
		HashMap<String, String> map = new HashMap<String, String>();
		System.out.println(member.toString());
		Member m = mService.checkLogin(member.getId(), member.getPassword());
//		Member m = mService.selectOneMember("5");  
		logger.info(member.getId() + " 로그인");  
		logger.info(member.getPassword() + " 로그인");
		if (m!= null) {
			map.put("success", "true");
			map.put("mid", m.getId());
			map.put("grade", m.getGrade());
//			tokenService.insertToken(token);
			// jwt 생성 및 Map에 담기
			String jwt = jwtService.makeJwt(m);  
			map.put("jwt", jwt);
			logger.info("jwt 생성: " + jwt);
			logger.info(m.getId() + " 로그인");
			logger.info(m.toString());
			return map;
		} else {
			map.put("success", "false");
			logger.info("로그인 실패");
			return map;
		}
	}

	@RequestMapping(value = "/jwt/auth", method = RequestMethod.POST, produces = { "application/json;charset=utf-8" })
	public Map checkAuth(@RequestBody Jwt jwt) {
		HashMap<String, String> map = new HashMap<String, String>();
		String token = jwt.getJwt();
		logger.info(token);
		// 유효화 검사.. 유효하면 갱신, 그렇지 않으면 폐기
		// 유효화 검사에 accesstoken과 refreshToken 2개를 발
		if (jwtService.checkJwt(token)) {
			logger.info("jwt 유효");
			logger.info(token);
			map.put("success", "true");
			Member m = jwtService.readJwt(token);
			map.put("mid", m.getId());
			map.put("name", m.getName());
			return map;
		} else {
			logger.info("jwt 유효하지 않음");
			logger.info(token);
			map.put("success", "invalidToken");
			return map;
		}
	}
}
