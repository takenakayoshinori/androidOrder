package com.zexis.sd.service;

import org.mybatis.dynamic.sql.SqlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zexis.sd.domain.model.EstimeForm;
import com.zexis.sd.infrastructure.entity.Estimes;
import com.zexis.sd.infrastructure.repository.EstimesDynamicSqlSupport;
import com.zexis.sd.infrastructure.repository.EstimesMapper;

@Service
public class EsTimeService {
    @Autowired
    private EstimesMapper estimesMapper;
    
    @Transactional(rollbackFor = Exception.class)
	public void update(EstimeForm form) throws JsonProcessingException, JsonMappingException {
		ObjectMapper  objectMapper = new ObjectMapper();
        JsonNode json = objectMapper.readTree(form.getEstimes());
        int no = 1;
        estimesMapper.delete(c -> c.where(EstimesDynamicSqlSupport.projectId, SqlBuilder.isEqualTo(form.getProjectId())));
        for(JsonNode node : json) {
			if (!StringUtils.hasLength(node.get("theme").asText())) {
        		continue;
        	}
        	node.get("");
        	Estimes estimes = new Estimes();
        	estimes.setProjectId(form.getProjectId());
        	estimes.setNumber(no++);
        	estimes.setTheme(node.get("theme").asText());
        	estimes.setFunction(node.get("function").asText());
        	estimes.setTheme(node.get("theme").asText());
        	estimes.setCharge(node.get("charge").asInt());
        	estimes.setDesign(node.get("design").asText());
        	estimes.setImplementation(node.get("implementation").asText());
        	estimes.setUnit(node.get("unit").asText());
        	estimes.setBinding(node.get("binding").asText());
        	estimesMapper.insert(estimes);
        }
	}
}
