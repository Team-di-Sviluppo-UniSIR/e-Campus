package CampusDataManagement;

import JSONParser.*;

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

/**
 * The Class CanteenStatusOutputIMPL.
 */
public class CanteenStatusOutputIMPL implements CanteenStatusOutputIF {

	/**
	 * Gets the waiting time.
	 *
	 * @return il tempo stimato d'attesa
	 */
	@Override
	public Time getWaitingTime() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the available plates.
	 *
	 * @return i piatti disponibili nel momento richiesto in una determinata mensa
	 */
	@Override
	public Menu getAvailablePlates() {
		// Mi restituisce tutti i piatti di una mensa in una determinata apertura
		return null;
	}

	/**
	 * Gets the canteen status.
	 *
	 * @return se una mensa è aperta o chiusa in quell'istante
	 */
	@Override
	public String getCanteenStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the available seats.
	 *
	 * @param mensa             la mensa che si sta cercando
	 * @param dettaglioApertura il dettaglio apertura che si sta cercando
	 * @param apertura          l'apertura che si sta cercando
	 * @param uri               uri per la connessione a mongoDB fortnito da metodo
	 *                          ad hoc
	 * @return i posti disponibili della mensa cercata nella determinata apertura
	 */
	@Override
	public int getAvailableSeats(Mensa mensa, DettaglioApertura dettaglioApertura, Apertura apertura,
			MongoClientURI uri) {

		// -1 = errore
		int postiDisponibili = -1;

		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase mongoDB = mongoClient.getDatabase("DBCampus");
		MongoCollection<Document> collection = mongoDB.getCollection("DBCampusCollection");

		// final Bson filterQuery = new Document("nome", mensa.nome);
		Bson filterQuery = Filters.eq("nome", mensa.getNome());
		// risultati ottenuti (lista di Document)
		FindIterable<Document> queryRes = collection.find(filterQuery);

		// mi assicuro di ricevere 1 solo risultato (il nome della mensa è univoco)
		if (JSONParser.countQueryResults(queryRes) != 1)
			throw new RuntimeException(); // definire nostra eccezione
		else {
			JSONObject objMensa = new JSONObject(queryRes.first().toJson());
			
			JSONArray arrayDettagli = objMensa.getJSONArray("dettaglioApertura");
			ArrayList<String> filterList = new ArrayList<String>(Arrays.asList("giornoSettimana", dettaglioApertura.getGiornoSettimana()));
			JSONObject objDettaglioApertura = JSONParser.filterInto(arrayDettagli, filterList);

			JSONArray arrayAperture = objDettaglioApertura.getJSONArray("apertura");
			filterList = new ArrayList<String>(Arrays.asList("data", apertura.getData().toString()));
			JSONObject result = JSONParser.filterInto(arrayAperture, filterList);
			
			postiDisponibili = result.getInt("availableSeats");
			
		}

		return postiDisponibili;
	}

	/**
	 * Gets the canteen capacity.
	 *
	 * @param mensa la mensa che si sta cercando
	 * @param uri   uri per la connessione a mongoDB fortnito da metodo ad hoc
	 * @return i posti della mensa totali
	 */
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
		if (JSONParser.countQueryResults(queryRes) != 1)
			throw new RuntimeException(); // definire nostra eccezione
		else {
			JSONObject JMensa = new JSONObject(queryRes.first().toJson());
			capacita = JMensa.getInt("capacita");
		}

		return capacita;
	}

	/**
	 * Gets the dish price.
	 *
	 * @param mensa             la mensa che si sta cercando
	 * @param dettaglioApertura il dettaglio apertura che si sta cercando
	 * @param apertura          l'apertura che si sta cercando
	 * @param menu              il menu che si sta cercando
	 * @param dish              il piatto che si sta cercando
	 * @param uri               uri per la connessione a mongoDB fortnito da metodo
	 *                          ad hoc
	 * @return il prezzo del determinato piatto cercato nella determinata mensa nel
	 *         determinato giorno
	 */
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
		if (JSONParser.countQueryResults(queryRes) != 1)
			throw new RuntimeException();
		else {
			JSONObject objMensa = new JSONObject(queryRes.first().toJson());

			JSONArray arrayDettagli = objMensa.getJSONArray("dettaglioApertura");
			ArrayList<String> filterList = new ArrayList<String>(Arrays.asList("giornoSettimana",
					dettaglioApertura.getGiornoSettimana(), "tipoPasto", dettaglioApertura.getTipoPasto()));
			JSONObject objDettaglioApertura = JSONParser.filterInto(arrayDettagli, filterList);

			JSONArray arrayAperture = objDettaglioApertura.getJSONArray("apertura");
			filterList = new ArrayList<String>(Arrays.asList("data", apertura.getData().toString()));
			JSONObject objApertura = JSONParser.filterInto(arrayAperture, filterList);
			JSONObject objMenu = objApertura.getJSONObject("menu");

			JSONArray arrayPiatti = objMenu.getJSONArray("Piatti");
			filterList = new ArrayList<String>(Arrays.asList("nomePiatto", dish.getNomePiatto()));
			JSONObject result = JSONParser.filterInto(arrayPiatti, filterList);

			DishPrice = result.getDouble("prezzo");

		}

		return DishPrice;
	}

	/**
	 * Gets the canteen ETA.
	 *
	 * @return ritorna il tempo di attesa medio in caso di mensa piena
	 */
	@Override
	public Time getCanteenETA() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the opening hours.
	 *
	 * @return gli orari di apertura di una certa mensa in un certo giorno
	 */
	@Override
	public DettaglioApertura getOpeningHours() {
		// TODO Auto-generated method stub
		return null;
	}

}
