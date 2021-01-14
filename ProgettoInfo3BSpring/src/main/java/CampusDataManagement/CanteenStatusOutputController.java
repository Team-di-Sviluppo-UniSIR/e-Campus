package CampusDataManagement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dataItemClasses.Apertura;
import dataItemClasses.Data;
import dataItemClasses.DettaglioApertura;
import dataItemClasses.Dish;
import dataItemClasses.Mensa;
import dataItemClasses.Menu;

@RestController
public class CanteenStatusOutputController {

	// QUando viene esposta l'API si stabilisce la connessione con MongoDB
	CanteenStatusOutputIMPL canteenObj = new CanteenStatusOutputIMPL();

	/*
	 * localhost:8080/getCanteenCapacity?nomeMensa=I sapori della terra
	 */
	@GetMapping("/getCanteenCapacity")
	public int getCanteenCapacityAPI(@RequestParam(value = "nomeMensa") String nomeMensa) {
		Mensa mensa = new Mensa(0, nomeMensa, 0, null);
		return canteenObj.getCanteenCapacity(mensa);
	}

	/*
	 * localhost:8080/getAvailableSeats?nomeMensa=I sapori della
	 * terra&giornoSettimana=Lunedì&tipoPasto=Pranzo&data=04-01-2021
	 */
	@GetMapping("/getAvailableSeats")
	public int getAvailableSeatsAPI(@RequestParam(value = "nomeMensa") String nomeMensa,
			@RequestParam(value = "giornoSettimana") String giornoSettimana,
			@RequestParam(value = "tipoPasto") String tipoPasto, @RequestParam(value = "data") String data) {

		Mensa mensa = new Mensa(0, nomeMensa, 0, null);
		DettaglioApertura d1 = new DettaglioApertura(0, giornoSettimana, tipoPasto, null, null);
		String[] giornoMeseAnno = data.split("-");
		Data data1 = new Data(giornoMeseAnno[0], giornoMeseAnno[1], giornoMeseAnno[2]);
		Apertura a1 = new Apertura(0, data1, 0, mensa, d1);
		return canteenObj.getAvailableSeats(mensa, d1, a1);
	}

	/*
	 * localhost:8080/getDishPrice?nomeMensa=I sapori della
	 * terra&giornoSettimana=Lunedì&tipoPasto=Cena&data=04-01-2021&nomeMenu=
	 * cenaLunedì&tipoMenu=Mediterraneo&nomePiatto=Pasta al salmone
	 */
	@GetMapping("/getDishPrice")
	public double getDishPriceAPI(@RequestParam(value = "nomeMensa") String nomeMensa,
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

		return canteenObj.getDishPrice(mensa, d1, a1, menu1, piatto1);
	}

}
