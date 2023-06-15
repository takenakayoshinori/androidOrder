package com.zexis.sd.presentation.controller;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.taskadapter.redmineapi.ProjectManager;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.Project;
import com.zexis.sd.domain.model.IndexForm;
import com.zexis.sd.service.RedmineManagerComp;

/**
 * メインメニューコントローラ
 * @author zexis
 *
 */
@Controller
@SessionAttributes(types = { IndexForm.class })
@RequestMapping("/index")
public class IndexController {

    @Autowired
    RedmineManagerComp redmineManagerComp;

    @GetMapping
    public String index(Model model, IndexForm form) throws RedmineException, IOException {

        ProjectManager projectManager = redmineManagerComp.getRedmineManager().getProjectManager();
        List<Project> projects = projectManager.getProjects();
        
        StringJoiner join = new StringJoiner(",", "[", "]");
        projects.stream().sorted(Comparator.comparing(Project::getId)).forEach(e-> join.add(col(e)));
        form.setProjectJson(join.toString());
        return "index";
    }
    
    private String col(Project project) {
        StringJoiner join = new StringJoiner(",", "{", "}");
        join.add("\"id\":" + project.getId().toString());
        join.add("\"name\":\"" + project.getName() + "\"");
    	return join.toString();
    }
}