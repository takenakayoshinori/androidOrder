package com.zexis.sd.domain.dto.ed;

import java.util.Map;

import com.taskadapter.redmineapi.bean.Tracker;
import com.zexis.sd.domain.dto.Innsue;
import com.zexis.sd.domain.dto.Innsues;
import com.zexis.sd.domain.dto.ProductionCosts;
import com.zexis.sd.infrastructure.entity.Estimes;

import lombok.Getter;

@Getter
public class EdFunction {

	private String function;
	
	private Innsues edInnsues;
	
	public EdFunction(Estimes esTime, Tracker edTracker, Integer reviewId, Map<Integer, Double> rateMap) {
		this.function = esTime.getFunction();
		this.edInnsues = new Innsues();
		
		ProductionCosts cost = 
				ProductionCosts.builder().cost(Double.parseDouble(esTime.getDesign()))
						.rate(rateMap.get(esTime.getCharge())).build();
		
		// 設計
		edInnsues.setExe(
			Innsue.builder()
				.tracker(edTracker)
				.title(this.function + "設計書作成")
				.assignedId(esTime.getCharge())
				.esTime(cost.getEsExecutionCost())
				.planTime(cost.getPlanExecutionCost())
				.build());
		
		// レビューア
		edInnsues.setReviewA(
			Innsue.builder()
				.tracker(edTracker)
				.title(this.function + "レビューア")
				.assignedId(reviewId)
				.esTime(cost.getRevewCost())
				.planTime(cost.getRevewCost())
				.build());
		
		// レビューイ
		edInnsues.setReviewI(
		Innsue.builder()
			.tracker(edTracker)
			.title(this.function + "レビューイ")
			.assignedId(esTime.getCharge())
			.esTime(cost.getRevewCost())
			.planTime(cost.getRevewCost())
			.build());
	}
}
