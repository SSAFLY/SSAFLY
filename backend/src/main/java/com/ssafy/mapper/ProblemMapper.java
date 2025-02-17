package com.ssafy.mapper;

import java.util.List;

import com.ssafy.vo.Problem;

public interface ProblemMapper {	
	public void insertProblem(Problem problem);
	public List<Problem> selectAllProblem();
	public List<Problem> selectSolveingProblem(String type);
	public Problem selectOneProblem(String id);
	public void updateProblem(String id, String contents);
	public void deleteProblem(String id);
}
