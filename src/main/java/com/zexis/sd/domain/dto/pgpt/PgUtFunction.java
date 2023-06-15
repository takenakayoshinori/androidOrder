package com.zexis.sd.domain.dto.pgpt;

import java.util.Map;

import com.taskadapter.redmineapi.bean.Tracker;
import com.zexis.sd.domain.dto.Innsue;
import com.zexis.sd.domain.dto.Innsues;
import com.zexis.sd.domain.dto.ProductionCosts;
import com.zexis.sd.infrastructure.entity.Estimes;

import lombok.Getter;

@Getter
public class PgUtFunction {

	private String function;
	
	private Innsues pgInnsues;
	
	private Innsues ptInnsues;
	
	public PgUtFunction(Estimes esTime, Tracker pgTracker, Tracker utTracker, Integer pgReviewId, Integer ptReviewId,
			Map<Integer, Double> rateMap) {
		this.function = esTime.getFunction();
		this.pgInnsues = new Innsues();
		
		ProductionCosts cost = 
		ProductionCosts.builder().cost(Double.parseDouble(esTime.getImplementation())).rate(rateMap.get(esTime.getCharge())).build();
		
		// 設計
		pgInnsues.setExe(
			Innsue.builder()
				.tracker(pgTracker)
				.title(this.function + "開発")
				.assignedId(esTime.getCharge())
				.esTime(cost.getEsExecutionCost())
				.planTime(cost.getPlanExecutionCost())
				.build());
		
		// レビューア
		pgInnsues.setReviewA(
			Innsue.builder()
				.tracker(pgTracker)
				.title(this.function + "レビューア")
				.assignedId(pgReviewId)
				.esTime(cost.getRevewCost())
				.planTime(cost.getRevewCost())
				.build());
		
		// レビューイ
		pgInnsues.setReviewI(
		Innsue.builder()
			.tracker(pgTracker)
			.title(this.function + "レビューイ")
			.assignedId(esTime.getCharge())
			.esTime(cost.getRevewCost())
			.planTime(cost.getRevewCost())
			.build());
		
		this.pgInnsues = new Innsues();
		
		ProductionCosts ptCost = 
		ProductionCosts.builder().cost(Double.parseDouble(esTime.getUnit())).rate(rateMap.get(esTime.getCharge())).build();
		
		// 設計
		ptInnsues.setExe(
			Innsue.builder()
				.tracker(utTracker)
				.title(this.function + "単体テスト")
				.assignedId(esTime.getCharge())
				.esTime(ptCost.getEsExecutionCost())
				.planTime(ptCost.getPlanExecutionCost())
				.build());
		
		// レビューア
		ptInnsues.setReviewA(
			Innsue.builder()
				.tracker(utTracker)
				.title(this.function + "レビューア")
				.assignedId(ptReviewId)
				.esTime(ptCost.getRevewCost())
				.planTime(ptCost.getRevewCost())
				.build());
		
		// レビューイ
		ptInnsues.setReviewI(
		Innsue.builder()
			.tracker(utTracker)
			.title(this.function + "レビューイ")
			.assignedId(esTime.getCharge())
			.esTime(ptCost.getRevewCost())
			.planTime(ptCost.getRevewCost())
			.build());
	}
}
