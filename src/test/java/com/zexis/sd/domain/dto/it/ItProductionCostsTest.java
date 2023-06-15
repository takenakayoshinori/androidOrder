package com.zexis.sd.domain.dto.it;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ItProductionCostsTest {

	@Test
	void test() {
		ItProductionCosts cost = 
		ItProductionCosts.builder()
			.cost(40.0)
			// 係数
			.coefficient(1.0)
			// レビュー割合
			.rRate(0.0625)
			// 実施割合
			.eRate(0.65)
			.build();
		
		// レビュー
		assertEquals(cost.getRevewCost(), 0.88);
		// 仕様書（見積）
		assertEquals(cost.getEsSpecificationCost(), 12.24);
		// 仕様書（実行）
		assertEquals(cost.getPlanSpecificationCost(), 15.91);
		
	}

}
