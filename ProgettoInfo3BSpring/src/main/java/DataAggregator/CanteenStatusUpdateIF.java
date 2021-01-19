package DataAggregator;

import dataItemClasses.*;

public interface CanteenStatusUpdateIF {

	/**
	 * Modifica la capacità della mensa specificata
	 *
	 * @param actualCapacity la nuova capacità da inserire
	 * @param mensa          la mensa che si sta cercando
	 * @return se l'operazione di modifica è andata a buon fine
	 */
	public boolean updateAvailableCapacity(int actualCapacity, Mensa mensa);

	/**
	 * Modifica la disponibiltà corrente di un dato piatto per la mensa specificata
	 * 
	 * @param new_availability  la nuova disponibilità corrente da inserire
	 * @param dish              il piatto che si sta cercando
	 * @param dailyMenu         il menu che si sta cercando
	 * @param mensa             la mensa che si sta cercando
	 * @param dettaglioApertura il dettaglio apertura che si sta cercando
	 * @param apertura          l'apertura che si sta cercando
	 * @return se l'operazione di modifica è andata a buon fine
	 */
	public boolean updateAvailablePortions(int new_availability, Dish dish, Menu dailyMenu, Mensa mensa,
			DettaglioApertura dettaglioApertura, Apertura apertura);
}
