package com.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.service.MemberService;
import com.ssafy.vo.Member;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class MemberController {

	@Autowired
	MemberService mService;

	@RequestMapping(value = "/member", method = RequestMethod.GET, produces = { "application/json;charset=utf-8" })
	public List<Member> selectAllMember() {
		return mService.selectAllMember();
	}

	@RequestMapping(value = "/member/{key}", method = RequestMethod.GET, produces = {
			"application/json;charset=utf-8" })
	public Member selectOneMember(@PathVariable String key) {
		return mService.selectOneMember(key);
	}

	@RequestMapping(value = "/member/{key}", method = RequestMethod.DELETE, produces = {
			"application/json;charset=utf-8" })
	public Map deleteMember(@PathVariable("key") String key) {
		HashMap<String, String> map = new HashMap<String, String>();
		mService.deleteMember(key);
		map.put("success", "true");
		return map;

	}

	@RequestMapping(value = "/member", method = RequestMethod.POST, produces = { "application/json;charset=utf-8" })
	public Map insertMember(@RequestBody Member member) {
		HashMap<String, String> map = new HashMap<String, String>();
		System.out.println(member.toString());
		System.out.println("들왓: " + mService.selectOneMember(member.getId()));
		if (mService.selectOneMember(member.getId()) != null) {
			map.put("success", "false");
			return map;
		}
		mService.insertMember(member);
		map.put("success", "true");
		map.put("mid", member.getId());
		return map;
	}
}
