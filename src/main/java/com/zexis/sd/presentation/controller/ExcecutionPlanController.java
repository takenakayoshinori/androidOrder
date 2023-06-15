package com.zexis.sd.presentation.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mybatis.dynamic.sql.SqlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.Tracker;
import com.zexis.sd.domain.dto.Theme;
import com.zexis.sd.domain.dto.ed.EdFunction;
import com.zexis.sd.domain.dto.ed.EdPhase;
import com.zexis.sd.domain.dto.it.ItFunction;
import com.zexis.sd.domain.dto.pgpt.PgUtFunction;
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
@RequestMapping("/executionPlan")
public class ExcecutionPlanController {

    @Autowired
    RedmineManagerComp redmineManagerComp;
    
    @Autowired
    private EstimesMapper estimesMapper;
    
    @Autowired
    private EsTimeService esTimeService;

    @GetMapping("/{projectId}")
    public String index(Model model, EstimeForm form) throws RedmineException, IOException {    	
    	List<Estimes> estimes = estimesMapper.select(c -> c.where(EstimesDynamicSqlSupport.projectId, SqlBuilder.isEqualTo(form.getProjectId())).orderBy(EstimesDynamicSqlSupport.number));
    	
    	Map<String, List<Estimes>> map = estimes.stream().collect(Collectors.groupingBy(Estimes::getTheme));
    	
    	// 割合
    	Map<Integer, Double> rateMap = new HashMap<>();
    	
    	// 係数
    	Map<Integer, Double> coefficientMap = new HashMap<>();
    	
    	for(Map.Entry<String, List<Estimes>> set : map.entrySet()) {
    		
    		// テーマ作成
    		Theme theme = new Theme();    		
    		theme.setEdPhase(new EdPhase());

    		Tracker edTracker = new Tracker();
    		edTracker.setId(8);
    		Tracker pgTracker = new Tracker();
    		pgTracker.setId(9);
    		Tracker utTracker = new Tracker();
    		utTracker.setId(10);
    		Tracker itTracker = new Tracker();
    		itTracker.setId(7);
    		
    		double itCostSum = 0.0; 
			for (Estimes esTime : set.getValue()) {
    			// 設計
    			EdFunction function = new EdFunction(esTime, edTracker, 5, rateMap);
    			theme.getEdPhase().getFunctions().add(function);
    			
    			// 開発・単体
    			PgUtFunction pgUtFunction = new PgUtFunction(esTime, pgTracker, utTracker, 5, 5, rateMap);
    			theme.getPgUtPhase().getFunctions().add(pgUtFunction);
    			
    			
    			if(StringUtils.hasLength(esTime.getImplementation())) {
    				itCostSum += Double.parseDouble(esTime.getImplementation());
    			}
    			
    		}
			
			ItFunction function = new ItFunction(set.getKey(), itTracker, itCostSum, 5, 5, rateMap);

			// 結合テスト
			theme.getItPhase().getFunctions().add(function);
    	}
    	
    	
        return "estime";
    }
    
    @PostMapping
    public String post(Model model, EstimeForm form) throws RedmineException, IOException {
    	esTimeService.update(form);
    	return "redirect:/estime/" + form.getProjectId();
    }    
}