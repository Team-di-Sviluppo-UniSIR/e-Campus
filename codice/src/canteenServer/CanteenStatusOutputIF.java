package canteenServer;

public interface CanteenStatusOutputIF {

	public Time getWaitingTime();

	public Menu getAvailablePlates();

	public String getCanteenStatus();

	public int getAvailableSeats();

	public int getCanteenCapacity(Mensa mensa);

	public double getDishPrice();

	public Time getCanteenETA();

	public DettaglioApertura getOpeningHours();
}
