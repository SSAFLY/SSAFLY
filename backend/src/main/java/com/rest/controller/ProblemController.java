package com.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.service.ProblemService;
import com.ssafy.vo.Problem;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class ProblemController {
	
	@Autowired
	ProblemService pService;
	
	@RequestMapping(value = "/problem", method = RequestMethod.GET, produces = { "application/json;charset=utf-8" })
	public List<Problem> selectAllProblem() {
		int a = 0;
		return pService.selectAllProblem();
	}
	
	@RequestMapping(value = "/problem/solving", method = RequestMethod.GET, produces = { "application/json;charset=utf-8" })
	public List<Problem> selectSolvingProblem() {
		List<Problem> slist = new ArrayList<Problem>();
		
		int size = pService.selectAllProblem().size();
		for (int i = 0; i < 10; i++) {
			int rand = (int)(Math.random()*size);
			slist.add(pService.selectOneProblem("46"));			
		}
		
		return slist;
	}
	
	@RequestMapping(value = "/problem/{key}", method = RequestMethod.GET, produces = { "application/json;charset=utf-8" })
	public Problem selectOneProblem(@PathVariable String key) {
		return pService.selectOneProblem(key);
	}

	@RequestMapping(value = "/problem/{key}", method = RequestMethod.DELETE, produces = {
			"application/json;charset=utf-8" })
	public Map deleteProblem(@PathVariable("key") String key) {
		HashMap<String, String> map = new HashMap<String, String>();
		pService.deleteProblem(key);
		map.put("success", "true");
		return map;

	}
	@RequestMapping(value = "/problem", method = RequestMethod.POST, produces = { "application/json;charset=utf-8" })
	public Map insertProblem(@RequestBody Problem problem) {
		HashMap<String, String> map = new HashMap<String, String>();
		
			pService.insertProblem(problem);
			map.put("success", "true");
		return map;
	}
}
