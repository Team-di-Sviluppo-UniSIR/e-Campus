package DataAggregator;

import dataItemClasses.*;

public interface CanteenStatusUpdateIF {
	public boolean updateAvailableCapacity(int actualCapacity, Mensa mensa);

	public boolean updateAvailablePortions(Dish dish);
}
