package DataAggregator;

import dataItemClasses.*;

public interface CanteenStatusUpdateIF {
	public boolean updateAvailableCapacity(int actualCapacity);

	public boolean updateAvailablePortions(Dish dish);
}
