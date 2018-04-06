package com.ibm.ns.schedule.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.ns.schedule.Constants;
import com.ibm.ns.schedule.logger.LogService;
import com.ibm.ns.schedule.logger.LogUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

@Component
public class MongoDBDataBaseDAO {

	private static MongoDatabase mongoDatabase=null;

	private List<Document> result=null;
	
	@Autowired
	private LogService logger; 

	/**
	 * 获取DB实例
	 * @return
	 */
	private static MongoDatabase getMongoDBInstance() {
		if(mongoDatabase==null) {
			MongoClient mongoClient = new MongoClient( Constants.MONGODB_HOST , Constants.MONGDB_PORT );
		       
	        // 连接到数据库
	        mongoDatabase = mongoClient.getDatabase(Constants.MONGODB_NAME); 
		}
		return mongoDatabase;
	}

	/**
	 * 存入单条
	 * @param doc
	 * @param collection
	 */
	static void writeDocument(Document doc, String collection) {
		MongoCollection<Document> dbcollection = getMongoDBInstance().getCollection(collection);
        dbcollection.insertOne(doc);
	}
	
	/**
	 * 存入多条
	 * @param doc
	 * @param collection
	 */
	void writeDocuments(List<Document> docs, String collection) {
		MongoCollection<Document> dbcollection = getMongoDBInstance().getCollection(collection);
		dbcollection.insertMany(docs);
	}
	
	/**
	 * 读取所有记录
	 * @param collection
	 * @return
	 */
	List<Document> readDocuments(String collection) {
		List<Document> list=new ArrayList<Document>();
		MongoCollection<Document> dbcollection = getMongoDBInstance().getCollection(collection);
		FindIterable<Document> findIterable = dbcollection.find();  
		MongoCursor<Document> mongoCursor = findIterable.iterator();  
	    while(mongoCursor.hasNext()){
	    	Document doc=mongoCursor.next();
	    	list.add(doc);
	    	logger.debug(MongoDBDataBaseDAO.class, "message read from MongoDB: "+doc, null);
	    } 
	    return list;
	}
	
	List<Document> readLatest(String collection){
		List<Document> list=new ArrayList<Document>();
		MongoCollection<Document> dbcollection = getMongoDBInstance().getCollection(collection);

        List<? extends Bson> pipeline = Arrays.asList(
                new Document()
                        .append("$group", new Document()
                                .append("_id", new Document()
                                        .append("text", "$text")
                                )
                                .append("MAX(children\u1390text)", new Document()
                                        .append("$max", "$children.text")
                                )
                        ), 
                new Document()
                        .append("$project", new Document()
                                .append("_id", 0)
                                .append("text", "$_id.text")
                                .append("MAX(children\u1390text)", "$MAX(children\u1390text)")
                        )
        );
        
        AggregateIterable aggregateIterable=dbcollection.aggregate(pipeline);
		
        MongoCursor<Document> mongoCursor = aggregateIterable.iterator();  
	    while(mongoCursor.hasNext()){
	    	Document doc=mongoCursor.next();
	    	Document query=new Document();
	    	query.append("text",doc.get("text"));
	    	query.append("children.text",((ArrayList<Object>)doc.get("MAX(children\u1390text)")).get(0));
	    	FindIterable resIt=dbcollection.find(query).limit(1);
	    	MongoCursor<Document> it=resIt.iterator();
	    	Document res=null;
	    	while(it.hasNext()) {
	    		res=it.next();
	    	}
	    	list.add(res);
	    	logger.debug(MongoDBDataBaseDAO.class, "message read from MongoDB: "+res, null);
	    } 
	    return list;
		
	}
	
	public static void main(String[] args) {
//		List<Document> list=readLatest("data_a");
//		for(Document doc:list) {
//			System.out.println(doc);
//		}
	}
}
