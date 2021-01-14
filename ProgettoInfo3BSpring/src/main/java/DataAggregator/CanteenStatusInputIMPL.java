package DataAggregator;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;

import dataItemClasses.Dish;
import dataItemClasses.Mensa;
import dataItemClasses.Menu;
import dataItemClasses.Time;
import dbConnection.dbConnectionSetter;

public class CanteenStatusInputIMPL implements CanteenStatusInputIF{
	
	//Connessione con  mongoDB 
	MongoCollection<Document> collection = dbConnectionSetter.connectToMongoCollection();

	@Override
	public boolean insertDailyMenu(Menu dailyMenu) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertDailyOpeningHours(Time openingHour, Time closingHour) {
		// TODO Auto-generated method stub
		return false;
	}

	
	/*
	  db.getCollection('DBCampusCollection').update(
   		{ nome: "Roadhouse" },
   			{ $set:
      		{
        	capacita: NumberInt(999)
      	}
   			}
)
	
	 */
	
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
		UpdateResult queryResult =collection.updateOne(searchQuery, setQuery);
			
		return queryResult.getModifiedCount()==1;
	}

	@Override
	public boolean insertNewDish(Dish dish) {
		// TODO Auto-generated method stub
		return false;
	}

}
