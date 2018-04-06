package com.ibm.ns.schedule.db;

import java.util.List;
import java.util.Map;


public interface DataBaseService {
	public void storeList(List<Map<String, Object>> list, String collection);
	
	public List<Map<String, Object>> getList(String collection);
	
	public List<Map<String, Object>> getLatestList(String collection);
}
