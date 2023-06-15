package com.zexis.sd.presentation.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.Membership;
import com.zexis.sd.domain.model.ProjectMenberResponse;
import com.zexis.sd.service.RedmineManagerComp;

/**
 * メインメニューコントローラ
 * @author zexis
 *
 */
@RestController
@RequestMapping("/menber")
public class MenberController {
	@Autowired
	RedmineManagerComp redmineManagerComp;

    @GetMapping("/{projectId}")
    public List<ProjectMenberResponse> getMenber(@PathVariable("projectId") int projectId) throws RedmineException {
        List<Membership> menberships = redmineManagerComp.getRedmineManager().getProjectManager().getProjectMembers(projectId);
        return menberships.stream().map(menbership -> new ProjectMenberResponse(menbership.getUserId(), menbership.getUserName())).collect(Collectors.toList());

    }
}