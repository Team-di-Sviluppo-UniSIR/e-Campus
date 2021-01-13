package CampusDataManagement;

import com.mongodb.MongoClientURI;

public interface CanteenStatusOutputIF {

	public Time getWaitingTime();

	public Menu getAvailablePlates();

	public String getCanteenStatus();

	public int getAvailableSeats(Mensa mensa, DettaglioApertura dettaglioApertura, Apertura apertura);

	public int getCanteenCapacity(Mensa mensa);

	public double getDishPrice(Mensa mensa, DettaglioApertura dettaglioApertura, Apertura apertura, Menu menu, 
			Dish dish);

	public Time getCanteenETA();

	public DettaglioApertura getOpeningHours();
}
