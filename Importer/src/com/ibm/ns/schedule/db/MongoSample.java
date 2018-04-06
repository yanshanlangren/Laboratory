package com.ibm.ns.schedule.db;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoSample {

	public static void main( String args[] ){
	      try{   
	         MongoClient mongoClient = new MongoClient( "9.110.168.184" , 27017 );
//	         MongoClient mongoClient = new MongoClient( "192.168.0.111" , 27017 );
	       
	         MongoDatabase mongoDatabase = mongoClient.getDatabase("test");  
	       System.out.println("Connect to database successfully");
	       
	       MongoCollection<Document> collection = mongoDatabase.getCollection("runoob");
	       
	       System.out.println("���� test ѡ��ɹ�");
	       
	    /**
	     * insert
	     */
	       Document document = new Document("title", "MongoDB").  
	       append("description", "database").  
	       append("likes", 100).  
	       append("by", "Fly");  
	       List<Document> documents = new ArrayList<Document>();  
	       documents.add(document);  
	       collection.insertMany(documents);  
	       System.out.println("�ĵ�����ɹ�");
	       
	    /**
	     * read
	     */
	       FindIterable<Document> findIterable = collection.find();  
	       MongoCursor<Document> mongoCursor = findIterable.iterator();  
	       while(mongoCursor.hasNext()){  
	          System.out.println(mongoCursor.next());  
	       }  
	       
	       //change 
	       collection.updateMany(Filters.eq("likes", 100), new Document("$set",new Document("likes",200)));  
	       //read again
	       FindIterable<Document> fi = collection.find();  
	       MongoCursor<Document> mc = fi.iterator();  
	       while(mc.hasNext()){  
	          System.out.println(mc.next());  
	       }  
	       
	       
	       //delete one 
	       collection.deleteOne(Filters.eq("likes", 200));  
	       //delete multiple
	       collection.deleteMany (Filters.eq("likes", 200));  
	       //read
	       FindIterable<Document> findIterable1 = collection.find();  
	       MongoCursor<Document> mongoCursor1 = findIterable1.iterator();  
	       while(mongoCursor1.hasNext()){  
	         System.out.println(mongoCursor1.next());  
	       }  
	       
	      }catch(Exception e){
	        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	     }
	   }
}
