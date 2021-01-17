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

/**
 * Classe CanteenStatusUpdateController.
 */
@RestController
public class CanteenStatusUpdateController {

	/** The canteen update obj. */
	CanteenStatusUpdateIMPL canteenUpdateObj = new CanteenStatusUpdateIMPL();

	/**
	 * API per aggiornamento della capacità della mensa.
	 *
	 * @param nomeMensa nome mensa
	 * @param capacita  nuova capacità della mensa
	 * @return file JSON di risposta
	 */
	/*
	 * localhost:8080/updateAvailableCapacity?nomeMensa=I sapori della
	 * terra&capacita=300
	 */
	@GetMapping("/updateAvailableCapacity")
	@RequestMapping(value = "/updateAvailableCapacity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateCanteenCapacityAPI(@RequestParam(value = "nomeMensa") String nomeMensa,
			@RequestParam(value = "capacita") int capacita) {
		Mensa mensa = new Mensa(0, nomeMensa, 0, null);

		JSONObject response = new JSONObject();

		try {
			if (canteenUpdateObj.updateAvailableCapacity(capacita, mensa))
				response.put("status", "aggiornamento avvenuto");
			else
				response.put("status", "impossibile effettuare aggiornamento");
		} catch (Exception e) {
			response.put("status", "errore invocazione API");
			response.put("errorMessage", e.getMessage());
		}

		return response.toString(4);
	}

	/**
	 * API per aggiornamento della disponibilità delle porzioni di un piatto.
	 *
	 * @param nomeMensa       nome mensa
	 * @param giornoSettimana giorno della settimana
	 * @param tipoPasto       tipo pasto
	 * @param data            data
	 * @param nomeMenu        nome menu
	 * @param tipoMenu        tipo menu
	 * @param nomePiatto      nome piatto
	 * @param newAvailability nuova disponibilità di porzioni per il piatto
	 * @return file JSON di risposta
	 */
	/*
	 * localhost:8080/updateAvailablePortions?nomeMensa=I sapori della
	 * terra&giornoSettimana=Lunedì&tipoPasto=Cena&data=04-01-2021&nomeMenu=
	 * pranzoLunedì&tipoMenu=Mediterraneo&nomePiatto=Pasta al
	 * pomodoro&newAvailability=100
	 */
	@GetMapping("/updateAvailablePortions")
	@RequestMapping(value = "/updateAvailablePortions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getDishPriceAPI(@RequestParam(value = "nomeMensa") String nomeMensa,
			@RequestParam(value = "giornoSettimana") String giornoSettimana,
			@RequestParam(value = "tipoPasto") String tipoPasto, @RequestParam(value = "data") String data,
			@RequestParam(value = "nomeMenu") String nomeMenu, @RequestParam(value = "tipoMenu") String tipoMenu,
			@RequestParam(value = "nomePiatto") String nomePiatto,
			@RequestParam(value = "newAvailability") int newAvailability) {

		Mensa mensa = new Mensa(0, nomeMensa, 0, null);
		DettaglioApertura d1 = new DettaglioApertura(0, giornoSettimana, tipoPasto, null, null);
		String[] giornoMeseAnno = data.split("-");
		Data data1 = new Data(giornoMeseAnno[0], giornoMeseAnno[1], giornoMeseAnno[2]);
		Apertura apertura = new Apertura(0, data1, 0, mensa, d1);
		Menu menu = new Menu(0, nomeMenu, tipoMenu, apertura);
		Dish piatto = new Dish(0, nomePiatto, null, 0, 0, newAvailability, menu);

		JSONObject response = new JSONObject();

		try {
			if (canteenUpdateObj.updateAvailablePortions(newAvailability, piatto, menu, mensa, d1, apertura))
				response.put("status", "aggiornamento avvenuto");
			else
				response.put("status", "impossibile effettuare aggiornamento");
		} catch (Exception e) {
			response.put("status", "errore invocazione API");
			response.put("errorMessage", e.getMessage());
		}

		return response.toString(4);
	}

}
