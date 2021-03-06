package CampusDataManagement;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dataItemClasses.Apertura;
import dataItemClasses.DettaglioApertura;
import dataItemClasses.Dish;
import dataItemClasses.Mensa;
import dataItemClasses.Menu;
import dataItemClasses.Time;

/**
 * Interfaccia CanteenStatusOutputIF.
 */
public interface CanteenStatusOutputIF {

	/**
	 * Fornisce una lista di tutte le mense del campus in formato JSON
	 *
	 * @return lista di tutte le mense del campus in formato JSON
	 */
	public List<JSONObject> getAllCanteens();

	/**
	 * Fornisce una lista con tutti i nomi delle mense del campus
	 *
	 * @return lista con i nomi delle mense del campus
	 */
	public List<String> getAllCanteensNames();

	/**
	 * Ritorna la capacità della mensa.
	 *
	 * @param mensa la mensa di cui si vuole conoscere la capacità
	 * @return la capacità della mensa
	 */
	public int getCanteenCapacity(Mensa mensa);

	/**
	 * Ritorna il numero di posti disponibili in una determinata mensa
	 *
	 * @param mensa             la mensa
	 * @param dettaglioApertura il dettaglio apertura
	 * @param apertura          l'apertura
	 * @return i posti disponibili
	 */
	public int getAvailableSeats(Mensa mensa, DettaglioApertura dettaglioApertura, Apertura apertura);

	/**
	 * Ritorna il prezzo di un determinato piatto in una certa mensa
	 *
	 * @param mensa             la mensa
	 * @param dettaglioApertura il dettaglio apertura
	 * @param apertura          l'apertura
	 * @param menu              il menu
	 * @param dish              il dish
	 * @return il prezzo del piatto ricercato
	 */
	public double getDishPrice(Mensa mensa, DettaglioApertura dettaglioApertura, Apertura apertura, Menu menu,
			Dish dish);

	/**
	 * Ritorna tutti i piatti di una certa mensa
	 * 
	 * @param mensa             la mensa
	 * @param dettaglioApertura il dettaglio apertura
	 * @param apertura          l'apertura
	 * @param menu              il menu
	 * @return il menù con i piatti
	 */
	public JSONArray getAvailablePlates(Mensa mensa, DettaglioApertura dettaglioApertura, Apertura apertura, Menu menu);

	/**
	 * Ritorna gli orari di apertura di una mensa.
	 *
	 * @return gli orari di apertura e chiusura di una mensa
	 */
	public DettaglioApertura getOpeningHours();

	/**
	 * Ritorna il dettaglio di apertura di una certa mensa per un certo giorno della
	 * settimana
	 *
	 * @return il dettaglio di apertura
	 */
	public Time getWaitingTime();

	/**
	 * Ritorna il tempo di attesa medio previsto (rif. pseudocodice).
	 *
	 * @return il tempo di attesa previsto
	 */
	public String getCanteenETA();

	/**
	 * Ritorna lo stato di una mensa
	 *
	 * @return lo stato della mensa
	 */
	public String getCanteenStatus();

}
