package DataAggregator;

import dataItemClasses.*;

public interface CanteenStatusInputIF {
	public boolean insertDailyMenu(Menu dailyMenu);

	public boolean insertDailyOpeningHours(Time openingHour, Time closingHour);

	public boolean insertCanteenCapacity(int canteenCapacity);

	public boolean insertNewDish(Dish dish);
}
