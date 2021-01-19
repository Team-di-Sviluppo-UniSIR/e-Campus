package CampusDataManagement;

import java.util.List;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dataItemClasses.Apertura;
import dataItemClasses.Data;
import dataItemClasses.DettaglioApertura;
import dataItemClasses.Dish;
import dataItemClasses.Mensa;
import dataItemClasses.Menu;

/**
 * Classe CanteenStatusController.
 */
@RestController
public class CanteenStatusOutputController {

	/** The canteen output obj. */
	// Quando viene esposta l'API si stabilisce la connessione con MongoDB
	CanteenStatusOutputIF canteenOutputObj = new CanteenStatusOutputIMPL();
	
	/**
	 * API per l'ottenimento dei nomi delle mense.
	 *
	 * 
	 * @return lista di String con i nomi di tutte le mense del campus
	 * 
	 */
	/*
	 * localhost:8080/getAllCanteensNames
	 */
	@GetMapping("/getAllCanteensNames")
	@RequestMapping(value = "/getAllCanteensNames", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAllCanteensNamesAPI() {

		JSONObject response = new JSONObject();
		List<String> nomiMense = canteenOutputObj.getAllCanteensNames();
		
		response.put("nomiMense", nomiMense);
		
		try {
			response.put("status", "OK");
		} catch (Exception e) {
			response.put("status", "errore invocazione API");
			response.put("errorMessage", e.getMessage());
		}

		return response.toString(4);
	}

	/**
	 * API per l'ottenimento della capacità di una mensa.
	 *
	 * @param nomeMensa the nome della mensa
	 * @return file JSON contenente la risposta riguardante la capacità della mensa
	 *         ricercata
	 */
	/*
	 * localhost:8080/getCanteenCapacity?nomeMensa=I sapori della terra
	 */
	@GetMapping("/getCanteenCapacity")
	@RequestMapping(value = "/getCanteenCapacity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getCanteenCapacityAPI(@RequestParam(value = "nomeMensa") String nomeMensa) {
		Mensa mensa = new Mensa(0, nomeMensa, 0, null);

		JSONObject response = new JSONObject();
		response.put("nomeMensa", nomeMensa);

		try {
			response.put("canteenCapacity", (int) canteenOutputObj.getCanteenCapacity(mensa));
			response.put("status", "OK");
		} catch (Exception e) {
			response.put("status", "errore invocazione API");
			response.put("errorMessage", e.getMessage());
		}

		return response.toString(4);
	}

	/**
	 * API per l'ottenimento del numero di posti disponibili di una mensa.
	 *
	 * @param nomeMensa       nome della mensa
	 * @param giornoSettimana giorno della settimana
	 * @param tipoPasto       il tipo pasto (colazione/pranzo/cena)
	 * @param data            la data di cui si vuole ricercare l'apertura
	 * @return file JSON contenente al risposta riguardante il numero di posti
	 *         disponibili in una determinata mensa in una certa data
	 */
	/*
	 * localhost:8080/getAvailableSeats?nomeMensa=I sapori della
	 * terra&giornoSettimana=Lunedì&tipoPasto=Pranzo&data=04-01-2021
	 */
	@GetMapping("/getAvailableSeats")
	@RequestMapping(value = "/getAvailableSeats", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAvailableSeatsAPI(@RequestParam(value = "nomeMensa") String nomeMensa,
			@RequestParam(value = "giornoSettimana") String giornoSettimana,
			@RequestParam(value = "tipoPasto") String tipoPasto, @RequestParam(value = "data") String data) {

		Mensa mensa = new Mensa(0, nomeMensa, 0, null);
		DettaglioApertura d1 = new DettaglioApertura(0, giornoSettimana, tipoPasto, null, null);
		String[] giornoMeseAnno = data.split("-");
		Data data1 = new Data(giornoMeseAnno[0], giornoMeseAnno[1], giornoMeseAnno[2]);
		Apertura a1 = new Apertura(0, data1, 0, mensa, d1);

		JSONObject response = new JSONObject();
		response.put("nomeMensa", nomeMensa);
		response.put("giornoSettimana", giornoSettimana);
		response.put("tipoPasto", tipoPasto);
		response.put("data", "" + data);

		try {
			response.put("availableSeats", (int) canteenOutputObj.getAvailableSeats(mensa, d1, a1));
			response.put("status", "OK");
		} catch (Exception e) {
			response.put("status", "errore invocazione API");
			response.put("errorMessage", e.getMessage());
		}

		return response.toString(4);
	}
	
	/**
	 * API per l'ottenimento dei piatti in una certa mensa in una data apertura.
	 *
	 * @param nomeMensa       il nome della mensa
	 * @param giornoSettimana il giorno della settimana
	 * @param tipoPasto       il tipo pasto
	 * @param data            la data
	 * @param nomeMenu        il nome del menu
	 * @param tipoMenu        il tipo di menu
	 * @return the dish price API
	 */
	/*
	 * localhost:8080/getAvailablePlates?nomeMensa=I sapori della
	 * terra&giornoSettimana=Lunedì&tipoPasto=Cena&data=04-01-2021&nomeMenu=
	 * cenaLunedì&tipoMenu=Mediterraneo
	 */
	@GetMapping("/getAvailablePlates")
	@RequestMapping(value = "/getAvailablePlates", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAvailablePlatesAPI(@RequestParam(value = "nomeMensa") String nomeMensa,
			@RequestParam(value = "giornoSettimana") String giornoSettimana,
			@RequestParam(value = "tipoPasto") String tipoPasto, @RequestParam(value = "data") String data,
			@RequestParam(value = "nomeMenu") String nomeMenu, @RequestParam(value = "tipoMenu") String tipoMenu) {

		Mensa mensa = new Mensa(0, nomeMensa, 0, null);
		DettaglioApertura d1 = new DettaglioApertura(0, giornoSettimana, tipoPasto, null, null);
		String[] giornoMeseAnno = data.split("-");
		Data data1 = new Data(giornoMeseAnno[0], giornoMeseAnno[1], giornoMeseAnno[2]);
		Apertura a1 = new Apertura(0, data1, 0, mensa, d1);
		Menu menu1 = new Menu(0, nomeMenu, tipoMenu, a1);

		JSONObject response = new JSONObject();
		response.put("nomeMensa", nomeMensa);
		response.put("giornoSettimana", giornoSettimana);
		response.put("tipoPasto", tipoPasto);
		response.put("data", "" + data);
		response.put("nomeMenu", nomeMenu);
		response.put("tipoMenu", tipoMenu);

		try {
			response.put("availablePlates", canteenOutputObj.getAvailablePlates(mensa, d1, a1, menu1));
			response.put("status", "OK");
		} catch (Exception e) {
			response.put("status", "errore invocazione API");
			response.put("errorMessage", e.getMessage());
		}

		return response.toString(4);
	}

	/**
	 * API per l'ottenimento del prezzo di un determinato piatto in una certa mensa
	 * in una data apertura.
	 *
	 * @param nomeMensa       il nome della mensa
	 * @param giornoSettimana il giorno della settimana
	 * @param tipoPasto       il tipo pasto
	 * @param data            la data
	 * @param nomeMenu        il nome del menu
	 * @param tipoMenu        il tipo di menu
	 * @param nomePiatto      il nome piatto di cui si vuole conoscere il prezzo
	 * @return the dish price API
	 */
	/*
	 * localhost:8080/getDishPrice?nomeMensa=I sapori della
	 * terra&giornoSettimana=Lunedì&tipoPasto=Cena&data=04-01-2021&nomeMenu=
	 * cenaLunedì&tipoMenu=Mediterraneo&nomePiatto=Pasta al salmone
	 */
	@GetMapping("/getDishPrice")
	@RequestMapping(value = "/getDishPrice", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getDishPriceAPI(@RequestParam(value = "nomeMensa") String nomeMensa,
			@RequestParam(value = "giornoSettimana") String giornoSettimana,
			@RequestParam(value = "tipoPasto") String tipoPasto, @RequestParam(value = "data") String data,
			@RequestParam(value = "nomeMenu") String nomeMenu, @RequestParam(value = "tipoMenu") String tipoMenu,
			@RequestParam(value = "nomePiatto") String nomePiatto) {

		Mensa mensa = new Mensa(0, nomeMensa, 0, null);
		DettaglioApertura d1 = new DettaglioApertura(0, giornoSettimana, tipoPasto, null, null);
		String[] giornoMeseAnno = data.split("-");
		Data data1 = new Data(giornoMeseAnno[0], giornoMeseAnno[1], giornoMeseAnno[2]);
		Apertura a1 = new Apertura(0, data1, 0, mensa, d1);
		Menu menu1 = new Menu(0, nomeMenu, tipoMenu, a1);
		Dish piatto1 = new Dish(0, nomePiatto, null, 0, 0, 0, menu1);

		JSONObject response = new JSONObject();
		response.put("nomeMensa", nomeMensa);
		response.put("giornoSettimana", giornoSettimana);
		response.put("tipoPasto", tipoPasto);
		response.put("data", "" + data);
		response.put("nomeMenu", nomeMenu);
		response.put("tipoMenu", tipoMenu);
		response.put("nomePiatto", nomePiatto);

		try {
			response.put("dishPrice", canteenOutputObj.getDishPrice(mensa, d1, a1, menu1, piatto1));
			response.put("status", "OK");
		} catch (Exception e) {
			response.put("status", "errore invocazione API");
			response.put("errorMessage", e.getMessage());
		}

		return response.toString(4);
	}

	
}
