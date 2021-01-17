package DataAggregator;

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

import CampusDataManagement.*;

// TODO: Auto-generated Javadoc
/**
 * Classe CanteenStatusInputController.
 */
@RestController
public class CanteenStatusInputController {

	// Quando viene esposta l'API si stabilisce la connessione con MongoDB
	CanteenStatusInputIMPL canteenInputObj = new CanteenStatusInputIMPL();

	/**
	 * API per inserimento capacità mensa.
	 *
	 * @param nomeMensa nome della mensa
	 * @param capacita  la capacita della mensa
	 * @return file JSON di risposta
	 */
	/*
	 * localhost:8080/insertCanteenCapacity?nomeMensa=I sapori della
	 * terra&capacita=300
	 */
	@GetMapping("/insertCanteenCapacity")
	@RequestMapping(value = "/insertCanteenCapacity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String insertCanteenCapacityAPI(@RequestParam(value = "nomeMensa") String nomeMensa,
			@RequestParam(value = "capacita") int capacita) {
		Mensa mensa = new Mensa(0, nomeMensa, 0, null);

		JSONObject response = new JSONObject();

		try {
			if (canteenInputObj.insertCanteenCapacity(capacita, mensa))
				response.put("status", "inserimento avvenuto");
			else
				response.put("status", "impossibile effettuare inserimento");
		} catch (Exception e) {
			response.put("status", "errore invocazione API");
			response.put("errorMessage", e.getMessage());
		}

		return response.toString(4);
	}

	/**
	 * API per inserimento menu.
	 *
	 * @param nomeMensa       nome mensa
	 * @param giornoSettimana giorno settimana
	 * @param tipoPasto       tipo pasto
	 * @param data            data
	 * @param idMenu          id menu
	 * @param nomeMenu        nome menu
	 * @param file            JSON di risposta
	 */
	/*
	 * http://localhost:8080/insertDailyMenu?nomeMensa=I sapori della
	 * terra&giornoSettimana=Lunedì&tipoPasto=Cena&data=04-01-2021&idMenu=34&
	 * nomeMenu=cenaLunedì&tipoMenu=Mediterraneo
	 */
	@GetMapping("/insertDailyMenu")
	@RequestMapping(value = "/insertDailyMenu", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String insertDailyMenuAPI(@RequestParam(value = "nomeMensa") String nomeMensa,
			@RequestParam(value = "giornoSettimana") String giornoSettimana,
			@RequestParam(value = "tipoPasto") String tipoPasto, @RequestParam(value = "data") String data,
			@RequestParam(value = "idMenu") int idMenu, @RequestParam(value = "nomeMenu") String nomeMenu,
			@RequestParam(value = "tipoMenu") String tipoMenu) {

		Mensa mensa = new Mensa(0, nomeMensa, 0, null);
		DettaglioApertura d1 = new DettaglioApertura(0, giornoSettimana, tipoPasto, null, null);
		String[] giornoMeseAnno = data.split("-");
		Data data1 = new Data(giornoMeseAnno[0], giornoMeseAnno[1], giornoMeseAnno[2]);
		Apertura apertura = new Apertura(0, data1, 0, mensa, d1);
		Menu menu = new Menu(idMenu, nomeMenu, tipoMenu, apertura);

		JSONObject response = new JSONObject();

		try {
			if (canteenInputObj.insertDailyMenu(menu, mensa, d1, apertura))
				response.put("status", "inserimento avvenuto");
			else
				response.put("status", "impossibile effettuare inserimento");
		} catch (Exception e) {
			response.put("status", "errore invocazione API");
			response.put("errorMessage", e.getMessage());
		}
		return response.toString(4);
	}

	/**
	 * API per inserimento di un nuovo piatto.
	 *
	 * @param nomeMensa           nome mensa
	 * @param giornoSettimana     giorno settimana
	 * @param tipoPasto           tipo pasto
	 * @param data                data
	 * @param nomeMenu            nome menu
	 * @param tipoMenu            tipo menu
	 * @param idPiatto            id piatto
	 * @param nomePiatto          nome piatto
	 * @param tipoPiatto          tipo piatto
	 * @param prezzo              prezzo
	 * @param initialAvailability disponibilità iniziale
	 * @param currentAvailability disponibilità attuale
	 * @return file JSON di risposta
	 */
	/*
	 * localhost:8080/insertNewDish?nomeMensa=I sapori della
	 * terra&giornoSettimana=Lunedì&tipoPasto=Cena&data=04-01-2021&nomeMenu=
	 * cenaLunedì&tipoMenu=Mediterraneo&idPiatto=18&nomePiatto=Lasagne&tipoPiatto=
	 * primo&prezzo=5.0&initialAvailability=100&currentAvailability=100
	 * 
	 */
	@GetMapping("/insertNewDish")
	@RequestMapping(value = "/insertNewDish", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getDishPriceAPI(@RequestParam(value = "nomeMensa") String nomeMensa,
			@RequestParam(value = "giornoSettimana") String giornoSettimana,
			@RequestParam(value = "tipoPasto") String tipoPasto, @RequestParam(value = "data") String data,
			@RequestParam(value = "nomeMenu") String nomeMenu, @RequestParam(value = "tipoMenu") String tipoMenu,
			@RequestParam(value = "idPiatto") int idPiatto, @RequestParam(value = "nomePiatto") String nomePiatto,
			@RequestParam(value = "tipoPiatto") String tipoPiatto, @RequestParam(value = "prezzo") double prezzo,
			@RequestParam(value = "initialAvailability") int initialAvailability,
			@RequestParam(value = "currentAvailability") int currentAvailability) {

		Mensa mensa = new Mensa(0, nomeMensa, 0, null);
		DettaglioApertura d1 = new DettaglioApertura(0, giornoSettimana, tipoPasto, null, null);
		String[] giornoMeseAnno = data.split("-");
		Data data1 = new Data(giornoMeseAnno[0], giornoMeseAnno[1], giornoMeseAnno[2]);
		Apertura apertura = new Apertura(0, data1, 0, mensa, d1);
		Menu menu = new Menu(0, nomeMenu, tipoMenu, apertura);
		Dish piatto = new Dish(idPiatto, nomePiatto, tipoPiatto, prezzo, initialAvailability, currentAvailability,
				menu);

		JSONObject response = new JSONObject();

		try {
			if (canteenInputObj.insertNewDish(piatto, menu, mensa, d1, apertura))
				response.put("status", "inserimento avvenuto");
			else
				response.put("status", "impossibile effettuare inserimento");
		} catch (Exception e) {
			response.put("status", "errore invocazione API");
			response.put("errorMessage", e.getMessage());
		}

		return response.toString(4);
	}
}
