package com.ssafy.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.mapper.MemberMapper;
import com.ssafy.vo.Member;
@Repository
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	MemberMapper mapper;

	@Override
	public void insertMember(Member member) {
		mapper.insertMember(member);
	}

	@Override
	public List<Member> selectAllMember() {
		return mapper.selectAllMember();
	}

	@Override
	public Member selectOneMember(String id) {
		return mapper.selectOneMember(id);
	}

	@Override
	public void deleteMember(String id) {
		mapper.deleteMember(id);
	}

	@Override
	public Member checkLogin(String id, String password) {
		return mapper.checkLogin(id, password);
	}
	

}
