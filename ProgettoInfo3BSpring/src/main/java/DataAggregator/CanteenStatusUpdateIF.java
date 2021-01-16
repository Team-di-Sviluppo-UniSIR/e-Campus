package DataAggregator;

import dataItemClasses.*;

public interface CanteenStatusUpdateIF {
	public boolean updateAvailableCapacity(int actualCapacity, Mensa mensa);

	public boolean updateAvailablePortions(int new_availability, Dish dish, Menu dailyMenu, Mensa mensa, DettaglioApertura dettaglioApertura,
			Apertura apertura);
}
