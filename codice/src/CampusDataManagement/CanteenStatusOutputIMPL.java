package CampusDataManagement;

import java.util.Iterator;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Filters.*;

public class CanteenStatusOutputIMPL implements CanteenStatusOutputIF {

	@Override
	public Time getWaitingTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Menu getAvailablePlates() {
		// Mi restituisce tutti i piatti di una mensa in una determinata apertura
		return null;
	}

	@Override
	public String getCanteenStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAvailableSeats(Mensa mensa, DettaglioApertura dettaglioApertura, Apertura apertura) {
		
		// -1 = errore
		int postiDisponibili = -1;

		// stabilisco la connessione
		// database: DBCampus, collezione: DBCampusCollection
		MongoClientURI uri = new MongoClientURI(
				"mongodb://admin:admin@cluster0-shard-00-00.qfzol.mongodb.net:27017,cluster0-shard-00-01.qfzol.mongodb.net:27017,cluster0-shard-00-02.qfzol.mongodb.net:27017/DBCampus?ssl=true&replicaSet=atlas-9gfc0g-shard-0&authSource=admin&retryWrites=true&w=majority");
		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase mongoDB = mongoClient.getDatabase("DBCampus");
		MongoCollection<Document> collection = mongoDB.getCollection("DBCampusCollection");

		// preparazione filtro di query
		
		//final Bson filterQuery = new Document("nome", mensa.nome);
		Bson filterQuery = Filters.and(Filters.eq("nome", mensa.nome),
						  Filters.eq("dettaglioApertura.giornoSettimana", dettaglioApertura.giornoSettimana),
						  Filters.eq("dettaglioApertura.apertura.data", apertura.data.toString())); 
		 
		// risultati ottenuti (lista di Document)
		FindIterable<Document> queryRes = collection.find(filterQuery);

		// mi assicuro di ricevere 1 solo risultato (il nome della mensa è univoco)
		if (countQueryResults(queryRes) != 1)
			throw new RuntimeException(); // definire nostra eccezione
		else {  
			JSONObject JMensa1 = new JSONObject(queryRes.first().toJson());
			
			System.out.println(JMensa1.toString());
			postiDisponibili = JMensa1.getJSONObject("dettaglioApertura").getJSONObject("apertura").getInt("availableSeats");
		}

		return postiDisponibili;
	}
	
	private static int countQueryResults(FindIterable<Document> docToCount) {
		int count=0;
		for(Document d: docToCount) {
			count++;
		}
		
		/*	Introdurre test invece di usare questo metodo
			System.out.println("numero di risultati:"+count);*/
		
		return count;
	}

	public int getCanteenCapacity(Mensa mensa) {

		// -1 = errore
		int capacita = -1;

		// stabilisco la connessione
		// database: DBCampus, collezione: DBCampusCollection
		MongoClientURI uri = new MongoClientURI(
				"mongodb://admin:admin@cluster0-shard-00-00.qfzol.mongodb.net:27017,cluster0-shard-00-01.qfzol.mongodb.net:27017,cluster0-shard-00-02.qfzol.mongodb.net:27017/DBCampus?ssl=true&replicaSet=atlas-9gfc0g-shard-0&authSource=admin&retryWrites=true&w=majority");
		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase mongoDB = mongoClient.getDatabase("DBCampus");
		MongoCollection<Document> collection = mongoDB.getCollection("DBCampusCollection");

		// preparazione filtro di query
		final Bson filterQuery = new Document("nome", mensa.nome);

		// risultati ottenuti (lista di Document)
		FindIterable<Document> queryRes = collection.find(filterQuery);

		// mi assicuro di ricevere 1 solo risultato (il nome della mensa è univoco)
		if (countQueryResults(queryRes) != 1)
			throw new RuntimeException(); // definire nostra eccezione
		else {
			JSONObject JMensa = new JSONObject(queryRes.first().toJson());
			capacita = JMensa.getInt("capacita");
		}

		return capacita;
	}

	@Override
	public double getDishPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Time getCanteenETA() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DettaglioApertura getOpeningHours() {
		// TODO Auto-generated method stub
		return null;
	}

}
