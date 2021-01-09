package testVarieCose;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class javaMongoParserTestFrutta {
	
	//Mari lo so che non ti piace la frutta

	public static void main(String[] args) {
		MongoClientURI uri = new MongoClientURI(
				"mongodb://admin:admin@cluster0-shard-00-00.qfzol.mongodb.net:27017,cluster0-shard-00-01.qfzol.mongodb.net:27017,cluster0-shard-00-02.qfzol.mongodb.net:27017/DBCampus?ssl=true&replicaSet=atlas-9gfc0g-shard-0&authSource=admin&retryWrites=true&w=majority");
		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase mongoDB = mongoClient.getDatabase("DBCampus");
		MongoCollection<Document> collection = mongoDB.getCollection("DBTestFrutta");
		Document myDoc = collection.find().first();
		System.out.println(myDoc.toJson());
		
		//creo file JSON da BSON ricevuto da MongoDB Atlas
		JSONObject Jfrutta = new JSONObject(myDoc.toJson());
		
		//retrieve dei vari campi dal file JSON per costruire oggetto mela
		String frutto = Jfrutta.getString("fruit");
		String size = Jfrutta.getString("size");
		String color = Jfrutta.getString("color");
		Frutto mela = new Frutto(frutto, size, color) ;
		System.out.println(mela.toString());
						
	}

}
