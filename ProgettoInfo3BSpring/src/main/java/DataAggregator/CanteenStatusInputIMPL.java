package DataAggregator;

import java.util.HashMap;
import java.util.Map;

import org.bson.BasicBSONObject;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.hamcrest.DiagnosingMatcher;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

import ch.qos.logback.core.filter.Filter;
import dataItemClasses.Apertura;
import dataItemClasses.DettaglioApertura;
import dataItemClasses.Dish;
import dataItemClasses.Mensa;
import dataItemClasses.Menu;
import dataItemClasses.Time;
import dbConnection.dbConnectionSetter;

public class CanteenStatusInputIMPL implements CanteenStatusInputIF{
	
	//Connessione con  mongoDB 
	MongoCollection<Document> collection = dbConnectionSetter.connectToMongoCollection();

	@Override
	public boolean insertDailyOpeningHours(Time openingHour, Time closingHour) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertCanteenCapacity(int canteenCapacity, Mensa mensa) {
		
		String nomeMensa=mensa.getNome();
		
		//preparazione searchQuery
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("nome", nomeMensa);
		
		//preparazione setObject per update
		BasicDBObject setObject = new BasicDBObject();
		setObject.put("capacita", canteenCapacity);

		//preparazione setQuery da setObject
		BasicDBObject setQuery = new BasicDBObject();
		setQuery.put("$set", setObject);
	
		//esecuzione query
		UpdateResult queryResult = collection.updateOne(searchQuery, setQuery);
			
		return queryResult.getModifiedCount()==1;
	}
	
	@Override
	public boolean insertDailyMenu(Menu dailyMenu, Mensa mensa, DettaglioApertura dettaglioApertura, Apertura apertura) {

		 Document mensa1 = new Document();
		 mensa1.append("nome", mensa.getNome())
		 			.append(null, mensa1)
		 			.append("giorno", dettaglioApertura.getGiornoSettimana())
		 			.append("tipoPasto", dettaglioApertura.getTipoPasto())
		 			.append("data", apertura.getData().toString());
		
		Document menu1 = new Document();
	    menu1.append("idMenu", dailyMenu.getIdMenu())
	    		.append("nomeMenu", dailyMenu.getNomeMenu())
	            .append("tipoMenu", dailyMenu.getTipoMenu());
		
        collection.findOneAndUpdate(mensa1, new Document("$set",menu1));

        return true;
		
	}
	
	/*
	 * 
	 */


	@Override
	public boolean insertNewDish(Dish dish) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
