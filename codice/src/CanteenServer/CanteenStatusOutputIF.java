package CanteenServer;

public interface CanteenStatusOutputIF {

	public Time getWaitingTime();

	public Menu getAvailablePlates();

	public String getCanteenStatus();

	public int getAvailableSeats();

	public int getCanteenCapacity();

	public double getDishPrice();

	public Time getCanteenETA();

	public DettaglioApertura getOpeningHours();
}
