package CampusDataManagement;

import com.mongodb.MongoClientURI;

public interface CanteenStatusOutputIF {

	public Time getWaitingTime();

	public Menu getAvailablePlates();

	public String getCanteenStatus();

	public int getAvailableSeats(Mensa mensa, DettaglioApertura dettaglioApertura, Apertura apertura,
			MongoClientURI uri);

	public int getCanteenCapacity(Mensa mensa, MongoClientURI uri);

	public double getDishPrice();

	public Time getCanteenETA();

	public DettaglioApertura getOpeningHours();
}
