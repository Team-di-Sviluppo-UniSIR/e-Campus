package CampusDataManagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
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
		Bson filterQuery = Filters.eq("nome", mensa.getNome());
		// risultati ottenuti (lista di Document)
		FindIterable<Document> queryRes = collection.find(filterQuery);

		// mi assicuro di ricevere 1 solo risultato (il nome della mensa è univoco)
		if (countQueryResults(queryRes) != 1)
			throw new RuntimeException();
		else {
			JSONObject objMensa = new JSONObject(queryRes.first().toJson());		
			
			JSONArray arrayDettagli = objMensa.getJSONArray("dettaglioApertura");
			ArrayList<String> filterList = new ArrayList<String>(Arrays.asList("giornoSettimana",dettaglioApertura.getGiornoSettimana(),"tipoPasto",dettaglioApertura.getTipoPasto()));
			JSONObject objDettaglioApertura = filterInto(arrayDettagli, filterList);
			
			JSONArray arrayAperture = objDettaglioApertura.getJSONArray("apertura");
			filterList = new ArrayList<String>(Arrays.asList("data",apertura.getData().toString()));
			JSONObject objApertura = filterInto(arrayAperture, filterList);
			JSONObject objMenu = objApertura.getJSONObject("menu");
			
			JSONArray arrayPiatti = objMenu.getJSONArray("Piatti");
			filterList = new ArrayList<String>(Arrays.asList("nomePiatto",dish.getNomePiatto()));
			JSONObject result = filterInto(arrayPiatti, filterList);
						
			DishPrice = result.getDouble("prezzo");		
			
			//System.out.println("Il prezzo per " + result.getString("nomePiatto") + " è: "+ DishPrice + "€");		
			
			}
		
		return DishPrice;		
	}
	
	public JSONObject filterInto(JSONArray startingArray, ArrayList<String> filteringArray){
		JSONObject resultObj = null;
		
		for(int i=0; i<startingArray.length();i++) {
			JSONObject subarrayObj = startingArray.getJSONObject(i);
			switch (filteringArray.size()) {
				case 2: {
					if(subarrayObj.getString(filteringArray.get(0)).equals(filteringArray.get(1))) {
						resultObj = subarrayObj;
						break;
					}
				}
				case 4: {
					if((subarrayObj.getString(filteringArray.get(0)).equals(filteringArray.get(1)))&&
					   (subarrayObj.getString(filteringArray.get(2)).equals(filteringArray.get(3)))) {
						resultObj = subarrayObj;
						break;
					}
				}
				}
		}
		
		return resultObj;
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
