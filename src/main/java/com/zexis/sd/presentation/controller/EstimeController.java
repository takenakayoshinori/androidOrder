package com.zexis.sd.presentation.controller;

import java.io.IOException;
import java.util.List;

import org.mybatis.dynamic.sql.SqlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.taskadapter.redmineapi.RedmineException;
import com.zexis.sd.domain.model.EstimeForm;
import com.zexis.sd.domain.model.IndexForm;
import com.zexis.sd.infrastructure.entity.Estimes;
import com.zexis.sd.infrastructure.repository.EstimesDynamicSqlSupport;
import com.zexis.sd.infrastructure.repository.EstimesMapper;
import com.zexis.sd.service.EsTimeService;
import com.zexis.sd.service.RedmineManagerComp;

/**
 * メインメニューコントローラ
 * @author zexis
 *
 */
@Controller
@SessionAttributes(types = { IndexForm.class })
@RequestMapping("/estime")
public class EstimeController {

    @Autowired
    RedmineManagerComp redmineManagerComp;
    
    @Autowired
    private EstimesMapper estimesMapper;
    
    @Autowired
    private EsTimeService esTimeService;

    @GetMapping("/{projectId}")
    public String index(Model model, EstimeForm form) throws RedmineException, IOException {    	
    	List<Estimes> estimes = estimesMapper.select(c -> c.where(EstimesDynamicSqlSupport.projectId, SqlBuilder.isEqualTo(form.getProjectId())).orderBy(EstimesDynamicSqlSupport.number));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String sjson = objectMapper.writeValueAsString(estimes);

        form.setEstimes(sjson);
        //form.setProjectJson(join.toString());
        return "estime";
    }
    
    @PostMapping
    public String post(Model model, EstimeForm form) throws RedmineException, IOException {
    	esTimeService.update(form);
    	return "redirect:/estime/" + form.getProjectId();
    }    
}