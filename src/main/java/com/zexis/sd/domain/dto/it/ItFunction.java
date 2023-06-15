package com.zexis.sd.domain.dto.it;

import java.util.Map;

import com.taskadapter.redmineapi.bean.Tracker;
import com.zexis.sd.domain.dto.Innsue;

import lombok.Getter;

@Getter
public class ItFunction {
	
	private ItInnsues itInnsues;
	
	public ItFunction(String theme, Tracker itTracker, Double itCost, Integer charge,Integer reviewId, Map<Integer, Double> rateMap) {
		this.itInnsues = new ItInnsues();
		
		ItProductionCosts cost = ItProductionCosts.builder().cost(itCost).rRate(rateMap.get(charge)).build();
		
		// 仕様書
		itInnsues.setSpecification(
			Innsue.builder()
				.tracker(itTracker)
				.title(theme + "仕様書")
				.assignedId(charge)
				.esTime(cost.getEsSpecificationCost())
				.planTime(cost.getPlanSpecificationCost())
				.build());
		
		// レビューア
		itInnsues.setReviewA(
			Innsue.builder()
				.tracker(itTracker)
				.title(theme + "レビューア")
				.assignedId(reviewId)
				.esTime(cost.getRevewCost())
				.planTime(cost.getRevewCost())
				.build());
		
		// レビューイ
		itInnsues.setReviewI(Innsue.builder()
				.tracker(itTracker)
				.title(theme + "レビューイ")
				.assignedId(charge)
				.esTime(cost.getRevewCost())
				.planTime(cost.getRevewCost())
				.build());
		
		
		// 実施
		itInnsues.setReviewI(Innsue.builder()
				.tracker(itTracker)
				.title(theme + "実施")
				.assignedId(charge)
				.esTime(cost.getEsExecutionCost())
				.planTime(cost.getPlanExecutionCost())
				.build());
	}
}
