package DataAggregator;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

import dataItemClasses.Dish;
import dataItemClasses.Mensa;
import dbConnection.dbConnectionSetter;

public class CanteenStatusUpdateIMPL implements CanteenStatusUpdateIF {

	@Override
	public boolean updateAvailableCapacity(int actualCapacity, Mensa mensa) {
		
		MongoCollection<Document> collection = dbConnectionSetter.connectToMongoCollection();
		
		//cerco l'oggetto su cui fare update (in questo caso mensa)
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("nome", mensa.getNome());
		
		//creo il filtro
		BasicDBObject putQuery = new BasicDBObject();
		putQuery.put("capacita", actualCapacity);
		
		//imposto la query
		BasicDBObject setQuery = new BasicDBObject();
		setQuery.put("$set", putQuery);
		
		//faccio update
		collection.findOneAndUpdate(searchQuery, setQuery);
		
		return true;
	}

	@Override
	public boolean updateAvailablePortions(Dish dish) {
		// TODO Auto-generated method stub
		return false;
	}

}
