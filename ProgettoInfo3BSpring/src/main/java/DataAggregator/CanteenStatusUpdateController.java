package DataAggregator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dataItemClasses.Mensa;

@RestController
public class CanteenStatusUpdateController {

	// Quando viene esposta l'API si stabilisce la connessione con MongoDB
	CanteenStatusInputIMPL canteenInputObj = new CanteenStatusInputIMPL();

	@GetMapping("/insertCanteenCapacity")
	public boolean getCanteenCapacityAPI(@RequestParam(value = "nomeMensa") String nomeMensa,
			@RequestParam(value = "capacita") int capacita) {
		Mensa mensa = new Mensa(0, nomeMensa, 0, null);
		return canteenInputObj.insertCanteenCapacity(capacita, mensa);
	}

}
