package com.zexis.sd.domain.dto.it;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Builder;

@Builder
public class ItProductionCosts {
	
	// 係数
	private Double coefficient;
	
	// レビュー割合
	private Double rRate;
	
	// 実施割合
	private Double eRate;
	
	// コスト
	private Double cost;
	
	/**
	 * 全工数 レビュー割合
	 * @return
	 */
	public Double getRevewCost() {
		return new BigDecimal((cost - (cost * eRate)) * rRate).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
	
	/**
	 * 見積設計書工数
	 * 見積工数 - (レビュー工数 × 2)  - (見積実施工数)
	 * @return
	 */
	public Double getEsSpecificationCost() {
		return new BigDecimal(cost - (getRevewCost() * 2) - getEsExecutionCost()).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
	
	
	/**
	 * 見積実施工数
	 * @return
	 */
	public Double getEsExecutionCost() {
		return cost * eRate;
	}
	
	/**
	 * 実行設計書工数
	 * @return
	 */
	public Double getPlanExecutionCost() {
		return getEsExecutionCost() * coefficient;
	}
	

	/**
	 * 見積実施工数
	 * @return
	 */
	public Double getPlanSpecificationCost() {
		return getEsSpecificationCost() * coefficient;
	}
}
