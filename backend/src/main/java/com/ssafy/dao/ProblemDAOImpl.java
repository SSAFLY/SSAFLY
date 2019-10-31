package com.ssafy.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.mapper.ProblemMapper;
import com.ssafy.vo.Problem;
@Repository
public class ProblemDAOImpl implements ProblemDAO {
	@Autowired
	ProblemMapper mapper;

	@Override
	public void insertProblem(Problem problem) {
		mapper.insertProblem(problem);
	}

	@Override
	public List<Problem> selectAllProblem() {
		return mapper.selectAllProblem();
	}

	@Override
	public List<Problem> selectSolveingProblem(String type) {
		return mapper.selectSolveingProblem(type);
	}

	@Override
	public Problem selectOneProblem(String id) {
		return mapper.selectOneProblem(id);
	}

	@Override
	public void updateProblem(String id) {
		mapper.updateProblem(id);
	}

	@Override
	public void deleteProblem(String id) {
		mapper.deleteProblem(id);
	}

	
}
