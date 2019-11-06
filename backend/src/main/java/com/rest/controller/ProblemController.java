package com.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.service.MemberService;
import com.ssafy.service.ProblemService;
import com.ssafy.vo.Answer;
import com.ssafy.vo.Problem;




@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class ProblemController {
	Logger logger = LoggerFactory.getLogger("WebMobile.logger");
	@Autowired
	ProblemService pService;
	@Autowired
	MemberService mService;
	
	@RequestMapping(value = "/problem", method = RequestMethod.GET, produces = { "application/json;charset=utf-8" })
	public List<Problem> selectAllProblem() {
		return pService.selectAllProblem();
	}
	
	@RequestMapping(value = "/problem/solving/{id}", method = RequestMethod.GET, produces = { "application/json;charset=utf-8" })
	public List<Problem> selectSolvingProblem(@PathVariable("id") String id) {
		List<Problem> slist = new ArrayList<Problem>();
		List<Problem> origin = pService.selectAllProblem();
		List<Answer> alist = mService.selectAnswer(id);
		logger.info(origin.get(0).getTitle());
		logger.info(origin.get(0).getAnswer());
		for (int i = 0; i < 10; i++) {
			int size = origin.size();
			int rand = (int)(Math.random()*size);
			slist.add(origin.get(rand));
			slist.get(i).setNum((i+1));
			origin.remove(rand);
			if(origin.size()==0) {
				break;
			}
			
			for (int j = 0; j < alist.size(); j++) {
				if(slist.get(i).getKey().equals(alist.get(j).getProblemKey())) {
					slist.get(i).setUserAnswer(alist.get(j).getAnswer());
				}
			}
			
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
	@RequestMapping(value = "/problem/{key}", method = RequestMethod.PUT, produces= {"application/json;charset=utf-8"})
	public Map updateProblem(@PathVariable("key") String key, @RequestBody HashMap<String,String> contents) {
		pService.updateProblem(key,contents.get("content"));
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("success", "true");
		return map;
	}
}
