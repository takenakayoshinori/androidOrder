package com.zexis.sd.domain.dto;


import com.zexis.sd.domain.dto.ed.EdPhase;
import com.zexis.sd.domain.dto.it.ItPhase;
import com.zexis.sd.domain.dto.pgpt.PgUtPhase;

import lombok.Data;

@Data
public class Theme {

	public EdPhase edPhase;
	
	public PgUtPhase pgUtPhase;
	
	public ItPhase itPhase;
}
