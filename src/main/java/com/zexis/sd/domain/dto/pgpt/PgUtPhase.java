package com.zexis.sd.domain.dto.pgpt;

import java.util.List;

import lombok.Data;

@Data
public class PgUtPhase {
	private List<PgUtFunction> functions;
}
