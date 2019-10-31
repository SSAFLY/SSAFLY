package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.dao.MemberDAO;
import com.ssafy.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDAO dao;

	@Override
	public void insertMember(Member member) {
		dao.insertMember(member);
	}

	@Override
	public List<Member> selectAllMember() {
		return dao.selectAllMember();
	}

	@Override
	public Member selectOneMember(String id) {
		return dao.selectOneMember(id);
	}

	@Override
	public void deleteMember(String id) {
		dao.deleteMember(id);
	}

	@Override
	public Member checkLogin(String id, String password) {
		return dao.checkLogin(id, password);
	}

}
