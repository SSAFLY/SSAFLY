package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.dao.MemberDAO;
import com.ssafy.vo.Answer;
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

	@Override
	public void deleteAnswer(String id, String problemKey) {
		dao.deleteAnswer(id, problemKey);
	}



	@Override
	public void updateAnswer(String id, String problemKey, String answer) {
		dao.updateAnswer(id, problemKey, answer);
	}

	@Override
	public List<Answer> selectAnswer(String id) {
		return dao.selectAnswer(id);
	}

	@Override
	public void insertAnswer(String id, String problemKey, String answer) {
		dao.insertAnswer(id, problemKey, answer);
		
	}


	
}
