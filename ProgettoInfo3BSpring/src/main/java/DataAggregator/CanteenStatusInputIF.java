package DataAggregator;

import dataItemClasses.*;

/**
 * Interfaccia CanteenStatusInputIF.
 */
public interface CanteenStatusInputIF {
	
	/**
	 * Inserisce gli orari di apertura e chiusura della mensa specificata
	 *
	 * @param openingHour		l'orario di apertura da inserire
	 * @param closingHour       l'orario di chiusura da inserire
	 * @param mensa             la mensa che si sta cercando
	 * @return se l'operazione di inserimento è andata a buon fine
	 */
	public boolean insertDailyOpeningHours(Time openingHour, Time closingHour, Mensa mensa);
	
	/**
	 * Inserisce la capacità della mensa specificata
	 *
	 * @param canteenCapacity	la capacità da inserire
	 * @param mensa             la mensa che si sta cercando
	 * @return se l'operazione di inserimento è andata a buon fine
	 */
	public boolean insertCanteenCapacity(int canteenCapacity, Mensa mensa);

	/**
	 * Inserisce un nuovo menù per la mensa specificata
	 * 
	 * @param dailyMenu         il menu da inserire
	 * @param mensa             la mensa che si sta cercando
	 * @param dettaglioApertura il dettaglio apertura che si sta cercando
	 * @param apertura          l'apertura che si sta cercando
	 * @return se l'operazione di inserimento è andata a buon fine
	 */
	public boolean insertDailyMenu(Menu dailyMenu, Mensa mensa, DettaglioApertura dettaglioApertura, Apertura apertura);
	
	/**
	 * Inserisce un nuovo piatto per la mensa specificata
	 * 
	 * @param dish				il piatto da inserire
	 * @param dailyMenu         il menu che si sta cercando
	 * @param mensa             la mensa che si sta cercando
	 * @param dettaglioApertura il dettaglio apertura che si sta cercando
	 * @param apertura          l'apertura che si sta cercando
	 * @return se l'operazione di inserimento è andata a buon fine
	 */
	public boolean insertNewDish(Dish dish, Menu dailyMenu, Mensa mensa, DettaglioApertura dettaglioApertura,
			Apertura apertura);
}
