package com.ssafy.dao;

import java.util.List;

import com.ssafy.vo.Problem;

public interface ProblemDAO {	
	public void insertProblem(Problem problem);
	public List<Problem> selectAllProblem();
	public List<Problem> selectSolveingProblem(String type);
	public Problem selectOneProblem(String id);
	public void updateProblem(String id, String contents);
	public void deleteProblem(String id);
}
