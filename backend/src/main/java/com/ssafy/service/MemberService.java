package com.ssafy.service;

import java.util.List;

import com.ssafy.vo.Answer;
import com.ssafy.vo.Member;

public interface MemberService {	
	public void insertMember(Member member);
	public List<Member> selectAllMember();
	public Member selectOneMember(String id);
	public void deleteMember(String id);
	public Member checkLogin(String id, String password);
	
	public void deleteAnswer(String id, String problemKey);
	public void insertAnswer(String id, String problemKey, String answer);
	public void updateAnswer(String id, String problemKey, String answer);
	public List<Answer> selectAnswer(String id);
}
