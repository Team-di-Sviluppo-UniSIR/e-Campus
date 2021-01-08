package com.sm.mongo;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class javaMongoConnection {
	public static void main(String[] args) {

		MongoClientURI uri = new MongoClientURI(
				"mongodb://admin:admin@cluster0-shard-00-00.qfzol.mongodb.net:27017,cluster0-shard-00-01.qfzol.mongodb.net:27017,cluster0-shard-00-02.qfzol.mongodb.net:27017/DBCampus?ssl=true&replicaSet=atlas-9gfc0g-shard-0&authSource=admin&retryWrites=true&w=majority");
		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase mongoDB = mongoClient.getDatabase("DBCampus");
		MongoCollection<Document> collection = mongoDB.getCollection("DBCampusCollection");
		Document myDoc = collection.find().first();
		System.out.println(myDoc.toJson());
		
		
	}
}
