package CampusDataManagement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CanteenStatusOutputController {
	
	//QUando viene esposta l'API si stabilisce la connessione con MongoDB
	CanteenStatusOutputIMPL canteenObj=new CanteenStatusOutputIMPL();
	
	@GetMapping("/getCanteenCapacity")
	public int getCanteenCapacityAPI(@RequestParam(value="nomeMensa") String nomeMensa){
		Mensa mensa = new Mensa(0, nomeMensa, 0, null);
		return canteenObj.getCanteenCapacity(mensa);
	}
			
}
