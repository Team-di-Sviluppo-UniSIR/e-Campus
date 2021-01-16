package DataAggregator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

import JSONParser.JSONParser;
import dataItemClasses.Apertura;
import dataItemClasses.DettaglioApertura;
import dataItemClasses.Dish;
import dataItemClasses.Mensa;
import dataItemClasses.Menu;
import dataItemClasses.Time;
import dbConnection.dbConnectionSetter;

public class CanteenStatusInputIMPL implements CanteenStatusInputIF {

	// Connessione con mongoDB
	MongoCollection<Document> collection = dbConnectionSetter.connectToMongoCollection();

	@Override
	public boolean insertDailyOpeningHours(Time openingHour, Time closingHour) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertCanteenCapacity(int canteenCapacity, Mensa mensa) {

		String nomeMensa = mensa.getNome();

		// preparazione searchQuery
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("nome", nomeMensa);

		// preparazione setObject per update
		BasicDBObject setObject = new BasicDBObject();
		setObject.put("capacita", canteenCapacity);

		// preparazione setQuery da setObject
		BasicDBObject setQuery = new BasicDBObject();
		setQuery.put("$set", setObject);

		// esecuzione query
		UpdateResult queryResult = collection.updateOne(searchQuery, setQuery);

		return queryResult.getModifiedCount() == 1;
	}

	@Override
	public boolean insertDailyMenu(Menu dailyMenu, Mensa mensa, DettaglioApertura dettaglioApertura,
			Apertura apertura) {

		String nomeMensa = mensa.getNome();
		String giornoSett = dettaglioApertura.getGiornoSettimana();
		String tipoPasto = dettaglioApertura.getTipoPasto();
		String data = apertura.getData().toString();

		// metodi per determinazione posizione array di MongoDB in cui inserire
		// DA = Dettaglio Apertura
		Map<Integer, JSONObject> mapDA = getArrIndexDettaglioApertura(nomeMensa, giornoSett, tipoPasto);
		int indexArrayDA = mapDA.keySet().iterator().next();
		JSONObject jsonObjectArrayDA = mapDA.get(indexArrayDA);

		// A = apertura
		//Map<Integer, JSONObject> mapA = getArrIndex(jsonObjectArrayDA, "apertura", "data", data);
		Map<Integer, JSONObject> mapA = getArrIndexApertura(jsonObjectArrayDA, data);
		int indexArrayA = mapA.keySet().iterator().next();

		// preparazione searchQuery
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("nome", nomeMensa);

		// preparazione campi JSON da inserire/aggiornare
		Document updatedMenu = new Document();
		updatedMenu.append("idMenu", dailyMenu.getIdMenu()).append("nomeMenu", dailyMenu.getNomeMenu())
				.append("tipoMenu", dailyMenu.getTipoMenu());

		// preparazione e esecuzione setQuery
		String queryString = "dettaglioApertura.".concat(String.valueOf(indexArrayDA)).concat(".apertura.")
				.concat(String.valueOf(indexArrayA)).concat(".menu");
		BasicDBObject setQuery = new BasicDBObject();
		setQuery.put("$set", new BasicDBObject(queryString, updatedMenu));
		UpdateResult queryResult = collection.updateOne(searchQuery, setQuery);

		return queryResult.getModifiedCount() == 1;
	}

	@Override
	public boolean insertNewDish(Dish dish) {
		// TODO Auto-generated method stub
		return false;
	}

	// metodo di supporto
	private Map<Integer, JSONObject> getArrIndexDettaglioApertura(String nomeMensa, String giornoSett,
			String tipoPasto) {

		// final Bson filterQuery = new Document("nome", mensa.nome);
		Bson filterQuery = Filters.eq("nome", nomeMensa);
		// risultati ottenuti (lista di Document)
		FindIterable<Document> queryRes = collection.find(filterQuery);

		// mi assicuro di ricevere 1 solo risultato
		if (JSONParser.countQueryResults(queryRes) != 1)
			throw new RuntimeException();
		else {
			JSONObject objMensa = new JSONObject(queryRes.first().toJson());

			JSONArray arrayDettagli = objMensa.getJSONArray("dettaglioApertura");
			ArrayList<String> filterList = new ArrayList<String>(
					Arrays.asList("giornoSettimana", giornoSett, "tipoPasto", tipoPasto));

			// restituisce map (indice arrayDettaglioApertura, JSONObject dell'apertura
			// selezionata)
			return filterIntoAndIndex(arrayDettagli, filterList);

		}

	}
	
	private Map<Integer, JSONObject> getArrIndex(JSONObject obj, String nomeVettore, String nomeCampo, String valoreCampo) {

		JSONArray array = obj.getJSONArray(nomeVettore);
		ArrayList<String> filterList = new ArrayList<String>(Arrays.asList(nomeCampo, valoreCampo));
		return filterIntoAndIndex(array, filterList);

	}

	private Map<Integer, JSONObject> getArrIndexApertura(JSONObject objDettaglioApertura, String data) {

		JSONArray arrayAperture = objDettaglioApertura.getJSONArray("apertura");
		ArrayList<String> filterList = new ArrayList<String>(Arrays.asList("data", data));
		return filterIntoAndIndex(arrayAperture, filterList);

	}

	private Map<Integer, JSONObject> filterIntoAndIndex(JSONArray startingArray, ArrayList<String> filteringArray) {
		JSONObject resultObj = null;

		int i;
		for (i = 0; i < startingArray.length(); i++) {
			JSONObject subarrayObj = startingArray.getJSONObject(i);
			switch (filteringArray.size()) {
			case 2: {
				if (subarrayObj.getString(filteringArray.get(0)).equals(filteringArray.get(1))) {
					resultObj = subarrayObj;
					break;
				}
			}
			case 4: {
				if ((subarrayObj.getString(filteringArray.get(0)).equals(filteringArray.get(1)))
						&& (subarrayObj.getString(filteringArray.get(2)).equals(filteringArray.get(3)))) {
					resultObj = subarrayObj;
					break;
				}
			}
			}
		}

		Map<Integer, JSONObject> res = new HashMap<>();
		res.put(i - 1, resultObj);

		return res;
	}

}
