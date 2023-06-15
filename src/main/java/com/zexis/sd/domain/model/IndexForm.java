package com.zexis.sd.domain.model;

import lombok.Data;

@Data
public class IndexForm {

	// プロジェクトリスト
	private String projectJson;
	
	// プロジェクトメンバーリスト
	private String menberJson;
}
