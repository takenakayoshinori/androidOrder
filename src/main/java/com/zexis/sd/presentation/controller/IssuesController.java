package com.zexis.sd.presentation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.internal.ResultsWrapper;
import com.zexis.sd.service.RedmineManagerComp;

@RestController
@RequestMapping("/issues")
public class IssuesController {
	@Autowired
	RedmineManagerComp redmineManagerComp;

    @GetMapping("/maxId")
    public Integer getMaxId() throws RedmineException {
    	Map<String, String> parameters = new HashMap<>();
    	parameters.put("sort", "id:desc");
    	parameters.put("limit", "1");
    	ResultsWrapper<Issue> issues = redmineManagerComp.getRedmineManager().getIssueManager().getIssues(parameters);
    	
        return issues.getResults().get(0).getId();

    }
    
    @PostMapping
    public void createIssue() throws RedmineException {
    	Issue issue = new Issue(redmineManagerComp.getRedmineManager().getTransport(), 34)
    			.setId(99999)
    		    .setSubject("test123")
    		    //.setTargetVersion(ver)
    		    //.setCategory(cat);
    		    .setProjectId(34)
    		    .create();
    	System.out.println(issue.getId());
    }
    
}
