package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.dao.ProblemDAO;
import com.ssafy.vo.Problem;

@Service
public class ProblemServiceImpl implements ProblemService {
	@Autowired
	ProblemDAO dao;

	@Override
	public void insertProblem(Problem problem) {
		dao.insertProblem(problem);
	}

	@Override
	public List<Problem> selectAllProblem() {
		return dao.selectAllProblem();
	}

	@Override
	public List<Problem> selectSolveingProblem(String type) {
		return dao.selectSolveingProblem(type);
	}

	@Override
	public Problem selectOneProblem(String id) {
		return dao.selectOneProblem(id);
	}


	@Override
	public void deleteProblem(String id) {
		dao.deleteProblem(id);
	}

	@Override
	public void updateProblem(String id, String contents) {
		dao.updateProblem(id, contents);
		
	}

	
}
