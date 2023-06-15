package com.zexis.sd.service;

import org.springframework.stereotype.Component;

import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;

@Component
public class RedmineManagerComp {
	private RedmineManager mgr;
	
	public RedmineManagerComp() {
        String uri = "https://zexis-sd.cloudmine.jp";
        String apiAccessKey = "d255b1c6e34925548b9dffefc6ffb4ee7fd860f1";

        mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
	}
	
	public RedmineManager getRedmineManager() {
		return mgr;
	}
}
