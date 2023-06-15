package com.zexis.sd.domain.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Builder;

@Builder
public class ProductionCosts {
	
	// 係数
	private Double coefficient;
	
	// 割合
	private Double rate;
	
	// 元工数
	private Double cost;
	
	
	/**
	 * レビューは人によらず一定
	 * @return
	 */
	public Double getRevewCost() {
		return new BigDecimal(cost * rate).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
	
	
	public Double getEsExecutionCost() {
		return cost - (getRevewCost() * 2);
	}
	
	public Double getPlanExecutionCost() {
		return getEsExecutionCost() * coefficient;
	}
}
