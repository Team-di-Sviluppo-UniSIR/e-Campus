package CampusDataManagement;

public interface CanteenStatusOutputIF {

	public Time getWaitingTime();

	public Menu getAvailablePlates();

	public String getCanteenStatus();

	public int getAvailableSeats(Mensa mensa, DettaglioApertura dettaglioApertura, Apertura apertura);

	public int getCanteenCapacity(Mensa mensa);

	public double getDishPrice();

	public Time getCanteenETA();

	public DettaglioApertura getOpeningHours();
}
