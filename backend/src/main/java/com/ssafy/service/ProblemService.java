package com.ssafy.service;

import java.util.List;

import com.ssafy.vo.Problem;

public interface ProblemService {	
	public void insertProblem(Problem problem);
	public List<Problem> selectAllProblem();
	public List<Problem> selectSolveingProblem(String type);
	public Problem selectOneProblem(String id);
	public void updateProblem(String id);
	public void deleteProblem(String id);
}
