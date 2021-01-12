package CampusDataManagement;

import java.util.Iterator;

import javax.management.Query;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONArray;
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
import com.mongodb.util.JSON;

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
	
	private static Object getKey(JSONArray array, String key, Object filtro) {
		Object value = -1;
		for (int i = 0; i < array.length(); i++) {
			JSONObject item = array.getJSONObject(i);
			if (item.keySet().contains(key)) {
				value = item.get(key);
				if(value.equals((String)filtro))
					break;
			}
		}

		return value;
	}

	@Override
	public int getAvailableSeats(Mensa mensa, DettaglioApertura dettaglioApertura, Apertura apertura,
			MongoClientURI uri) {

		// -1 = errore
		int postiDisponibili = -1;

		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase mongoDB = mongoClient.getDatabase("DBCampus");
		MongoCollection<Document> collection = mongoDB.getCollection("DBCampusCollection");

		// final Bson filterQuery = new Document("nome", mensa.nome);
		Bson filterQuery = Filters.and(Filters.eq("nome", mensa.getNome()),
				Filters.eq("dettaglioApertura.giornoSettimana", dettaglioApertura.getGiornoSettimana()),
				Filters.eq("dettaglioApertura.apertura.data", apertura.getData().toString()));

		// risultati ottenuti (lista di Document)
		FindIterable<Document> queryRes = collection.find(filterQuery);

		// mi assicuro di ricevere 1 solo risultato (il nome della mensa è univoco)
		if (countQueryResults(queryRes) != 1)
			throw new RuntimeException(); // definire nostra eccezione
		else {
			JSONObject JMensa1 = new JSONObject(queryRes.first().toJson());
			JSONArray dettaglio = JMensa1.getJSONArray("dettaglioApertura");
			JSONArray obj = (JSONArray) getKey(dettaglio, "apertura", dettaglioApertura.getGiornoSettimana());
			postiDisponibili = (int) getKey(obj, "availableSeats", apertura.getData().toString());
		}

		return postiDisponibili;
	}

	private static int countQueryResults(FindIterable<Document> docToCount) {
		int count = 0;
		for (Document d : docToCount) {
			count++;
		}

		/*
		 * Introdurre test invece di usare questo metodo
		 * System.out.println("numero di risultati:"+count);
		 */

		return count;
	}

	public int getCanteenCapacity(Mensa mensa, MongoClientURI uri) {
		// -1 = errore
		int capacita = -1;

		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase mongoDB = mongoClient.getDatabase("DBCampus");
		MongoCollection<Document> collection = mongoDB.getCollection("DBCampusCollection");

		// preparazione filtro di query
		final Bson filterQuery = new Document("nome", mensa.getNome());

		// risultati ottenuti (lista di Document)
		FindIterable<Document> queryRes = collection.find(filterQuery);

		// mi assicuro di ricevere 1 solo risultato (il nome della mensa � univoco)
		if (countQueryResults(queryRes) != 1)
			throw new RuntimeException(); // definire nostra eccezione
		else {
			JSONObject JMensa = new JSONObject(queryRes.first().toJson());
			capacita = JMensa.getInt("capacita");
		}

		return capacita;
	}

	@Override
	public double getDishPrice(Mensa mensa, DettaglioApertura dettaglioApertura, Apertura apertura, Menu menu, 
			Dish dish, MongoClientURI uri) {
		
		// -1 = errore
		Double DishPrice = -1.0;

		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase mongoDB = mongoClient.getDatabase("DBCampus");
		MongoCollection<Document> collection = mongoDB.getCollection("DBCampusCollection");
		

		// final Bson filterQuery = new Document("nome", mensa.nome);
		Bson filterQuery = Filters.and(Filters.eq("nome", mensa.getNome()),
		    Filters.eq("dettaglioApertura.giornoSettimana", dettaglioApertura.getGiornoSettimana()),
			Filters.eq("dettaglioApertura.apertura.data", apertura.getData().toString()),
			Filters.eq("dettaglioApertura.apertura.menu.nomeMenu", menu.getNomeMenu()),
			Filters.eq("dettaglioApertura.apertura.menu.Piatti.nomePiatto", dish.getNomePiatto()));

		
		// risultati ottenuti (lista di Document)
		FindIterable<Document> queryRes = collection.find(filterQuery);

		// mi assicuro di ricevere 1 solo risultato (il nome della mensa è univoco)
		if (countQueryResults(queryRes) != 1)
			throw new RuntimeException();
		else {
			
			JSONObject JMensa1 = new JSONObject(queryRes.first().toJson());		
			

			JSONArray arr = JMensa1.getJSONArray("dettaglioApertura");
			//mi deve restituire 2
			System.out.println("Numero array: "+arr.length());
			
			JSONObject res=null;
			//deve restituire 2 volte 2
			//for per ciclare su "dettaglioApertura"
			for(int i=0; i<arr.length();i++) {
				JSONObject subArrObj = arr.getJSONObject(i);
				
				if( subArrObj.getString("giornoSettimana").equals("Lunedì")&&subArrObj.getString("tipoPasto").equals("Cena") ) {
					res=subArrObj;
					break;
				}
			}
			
			//res contiene l'oggetto cercato nel sottoArray "dettaglioApertura"
			arr=res.getJSONArray("apertura");
			for(int i=0; i<arr.length();i++) {
				JSONObject subArrObj = arr.getJSONObject(i);
				
				System.out.println("TESSST"+subArrObj.toString());
				
				if( subArrObj.getString("data").equals("11/01/2021")) {
					res=subArrObj;
					break;
				}
			}
			
			//res contiene l'oggetto cercato nel sottoArray "apertura"
			JSONObject menuObject=res.getJSONObject("menu");
			
			System.out.println("I miei menu "+menuObject.toString());
			//TO DO: risolvere problema p P
			arr=menuObject.getJSONArray("Piatti");
			for(int i=0; i<arr.length();i++) {
				JSONObject subArrObj = arr.getJSONObject(i);
				
				System.out.println("TESSST"+subArrObj.toString());
				
				if( subArrObj.getString("nomePiatto").equals("kiwi")) {
					res=subArrObj;
					break;
				}
			}
			
			//ora res è l'oggetto che volevamo
			System.out.println("Il prezzo per " + res.getString("nomePiatto") + " è: "+res.getDouble("prezzo")+"€");
								
			}
		
				return -1;		
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
