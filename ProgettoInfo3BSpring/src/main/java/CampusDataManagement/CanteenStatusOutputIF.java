package CampusDataManagement;

import dataItemClasses.Apertura;
import dataItemClasses.DettaglioApertura;
import dataItemClasses.Dish;
import dataItemClasses.Mensa;
import dataItemClasses.Menu;
import dataItemClasses.Time;

public interface CanteenStatusOutputIF {
	
	public int getCanteenCapacity(Mensa mensa);

	public int getAvailableSeats(Mensa mensa, DettaglioApertura dettaglioApertura, Apertura apertura);

	public double getDishPrice(Mensa mensa, DettaglioApertura dettaglioApertura, Apertura apertura, Menu menu, Dish dish);

	public Menu getAvailablePlates();
	
	public DettaglioApertura getOpeningHours();
	
	public Time getWaitingTime();
	
	public String getCanteenETA();

	public String getCanteenStatus();

	
}
