package CampusDataManagement;

import JSONParser.*;

import dataItemClasses.Apertura;
import dataItemClasses.DettaglioApertura;
import dataItemClasses.Dish;
import dataItemClasses.Mensa;
import dataItemClasses.Menu;
import dataItemClasses.Time;
import dbConnection.dbConnectionSetter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

/**
 * Classe CanteenStatusOutputIMPL.
 */
public class CanteenStatusOutputIMPL implements CanteenStatusOutputIF {

	// Connessione con mongoDB
	MongoCollection<Document> collection = dbConnectionSetter.connectToMongoCollection();

	/**
	 * Ritorna tutte le mense presenti nel datbase.
	 *
	 * @return tutte le mense del campus
	 */
	public List<JSONObject> getAllCanteens() {

		List<JSONObject> jMense = new ArrayList<JSONObject>();

		// Estrazione e inserimento in jMense di tutte le mense della collezione
		// "DBCampusCollection"
		FindIterable<Document> iterDoc = collection.find();
		Iterator<Document> it = iterDoc.iterator();
		while (it.hasNext()) {
			JSONObject jMensaTemp = new JSONObject(it.next().toJson());
			jMense.add(jMensaTemp);
		}

		return jMense;
	}

	/**
	 * Ritorna tutti nomi delle mense presenti nel datbase.
	 *
	 * @return tutte i nomi delle mense del campus
	 */
	public List<String> getAllCanteensNames() {

		List<JSONObject> jMense = getAllCanteens();
		List<String> sMense = new ArrayList<String>();

		for (JSONObject jMensa : jMense) {
			String sMensaTemp = jMensa.getString("nome");
			sMense.add(sMensaTemp);
		}
		return sMense;
	}

	/**
	 * Ritorna la capacità della mensa.
	 *
	 * @param mensa la mensa che si sta cercando
	 * @return i posti della mensa totali
	 */
	public int getCanteenCapacity(Mensa mensa) {
		// -1 = errore
		int capacita = -1;

		// preparazione filtro di query
		final Bson filterQuery = new Document("nome", mensa.getNome());

		// risultati ottenuti (lista di Document)
		FindIterable<Document> queryRes = collection.find(filterQuery);

		// mi assicuro di ricevere 1 solo risultato (il nome della mensa è univoco)
		if (JSONParser.countQueryResults(queryRes) != 1)
			throw new RuntimeException();
		else {
			JSONObject JMensa = new JSONObject(queryRes.first().toJson());
			capacita = JMensa.getInt("capacita");
		}

		return capacita;
	}

	/**
	 * Ritorna il numero di posti disponibili.
	 *
	 * @param mensa             la mensa che si sta cercando
	 * @param dettaglioApertura il dettaglio apertura che si sta cercando
	 * @param apertura          l'apertura che si sta cercando
	 * @return i posti disponibili della mensa cercata nella determinata apertura
	 */
	@Override
	public int getAvailableSeats(Mensa mensa, DettaglioApertura dettaglioApertura, Apertura apertura) {

		// -1 = errore
		int postiDisponibili = -1;

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
			JSONObject result = JSONParser.filterInto(arrayAperture, filterList);

			postiDisponibili = result.getInt("availableSeats");

		}

		return postiDisponibili;
	}

	/**
	 * Ritorna i piatti disponibili.
	 *
	 * @param mensa             la mensa che si sta cercando
	 * @param dettaglioApertura il dettaglio apertura che si sta cercando
	 * @param apertura          l'apertura che si sta cercando
	 * @param menu              il menu che si sta cercando
	 * @return i piatti disponibili nel momento richiesto in una determinata mensa
	 */
	@Override
	public JSONArray getAvailablePlates(Mensa mensa, DettaglioApertura dettaglioApertura, Apertura apertura,
			Menu menu) {

		// Lista di oggetti piatto ritornata
		JSONArray jPiatti = new JSONArray();
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

			jPiatti = objMenu.getJSONArray("Piatti");
		}

		return jPiatti;
	}

	/**
	 * Ritorna il prezzo di un piatto.
	 *
	 * @param mensa             la mensa che si sta cercando
	 * @param dettaglioApertura il dettaglio apertura che si sta cercando
	 * @param apertura          l'apertura che si sta cercando
	 * @param menu              il menu che si sta cercando
	 * @param dish              il piatto che si sta cercando
	 * @return il prezzo del determinato piatto cercato nella determinata mensa nel
	 *         determinato giorno
	 */
	@Override
	public double getDishPrice(Mensa mensa, DettaglioApertura dettaglioApertura, Apertura apertura, Menu menu,
			Dish dish) {

		// -1 = errore
		Double DishPrice = -1.0;

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
	 * Ritorna gli orari di apertura.
	 *
	 * @return gli orari di apertura di una certa mensa in un certo giorno
	 */
	@Override
	public DettaglioApertura getOpeningHours() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Ritorna il dettaglio di apertura.
	 *
	 * @return il dettaglio di apertura
	 */
	@Override
	public Time getWaitingTime() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Ritorna il tempo di attesa medio per una certa mensa.
	 *
	 * @return il tempo di attesa medio in caso di mensa piena
	 */
	@Override
	public String getCanteenETA() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Ritorna lo stato di apertura.
	 *
	 * @return se una mensa è aperta o chiusa in quell'istante
	 */
	@Override
	public String getCanteenStatus() {
		// TODO Auto-generated method stub
		return null;
	}

}
