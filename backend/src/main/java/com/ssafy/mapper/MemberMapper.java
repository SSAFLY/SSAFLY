package com.ssafy.mapper;

import java.util.List;

import com.ssafy.vo.Member;

public interface MemberMapper {	
	public void insertMember(Member member);
	public List<Member> selectAllMember();
	public Member selectOneMember(String id);
	public void deleteMember(String id);
	public Member checkLogin(String id, String password);
}
