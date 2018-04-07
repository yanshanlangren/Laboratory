package com.ibm.ns.schedule.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MongoDBDataBaseImpl implements DataBaseService{
	
	@Autowired
	private MongoDBDataBaseDAO dao;
	
	/**
	 * @param list
	 * @param collection
	 */
	@Override
	public void storeList(List<Map<String, Object>> list,String collection) {
		List<Document> documents = new ArrayList<Document>(); 
		for(Map<String,Object> m:list) {
			Document doc=new Document();
			for(String k:m.keySet()) {
				doc.append(k, m.get(k));
			}
			documents.add(doc);
		}
		dao.writeDocuments(documents,collection);
	}
	
	@Override
	public List<Map<String, Object>> getList(String collection) {
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		
		List<Document> res=dao.readDocuments(collection);
		
		for(Document doc:res) {
			Map<String,Object> m=new HashMap<String,Object>();
			for(Object key:doc.keySet()) {
				m.put((String)key, doc.get(key));
			}
			list.add(m);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> getLatestList(String collection) {

		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		
		List<Document> res=dao.readLatest(collection);
		
		for(Document doc:res) {
			Map<String,Object> m=new HashMap<String,Object>();
			for(Object key:doc.keySet()) {
				m.put((String)key, doc.get(key));
			}
			list.add(m);
		}
		
		return list;
	}
	
}