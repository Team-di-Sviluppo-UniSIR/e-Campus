package DataAggregator;

import dataItemClasses.*;

public interface CanteenStatusInputIF {
	
	public boolean insertDailyOpeningHours(Time openingHour, Time closingHour);
	
	public boolean insertCanteenCapacity(int canteenCapacity, Mensa mensa);

	public boolean insertDailyMenu(Menu dailyMenu, Mensa mensa, DettaglioApertura dettaglioApertura, Apertura apertura);
	
	public boolean insertNewDish(Dish dish);
}
