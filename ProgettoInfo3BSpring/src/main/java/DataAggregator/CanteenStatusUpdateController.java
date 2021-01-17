package DataAggregator;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dataItemClasses.Mensa;

@RestController
public class CanteenStatusUpdateController {

	CanteenStatusUpdateIMPL canteenUpdateObj = new CanteenStatusUpdateIMPL();

	/*
	 * localhost:8080/updateCanteenCapacity?nomeMensa=I sapori della
	 * terra&capacita=300
	 */
	@GetMapping("/updateCanteenCapacity")
	@RequestMapping(value = "/updateCanteenCapacity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateCanteenCapacityAPI(@RequestParam(value = "nomeMensa") String nomeMensa,
			@RequestParam(value = "capacita") int capacita) {
		Mensa mensa = new Mensa(0, nomeMensa, 0, null);

		JSONObject response = new JSONObject();

		if (canteenUpdateObj.updateAvailableCapacity(capacita, mensa))
			response.put("status", "aggiornamento avvenuto");
		else
			response.put("status", "impossibile effettuare aggiornamento");

		return response.toString(4);
	}

}
