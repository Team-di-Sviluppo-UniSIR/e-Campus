package CampusDataManagement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.MongoClientURI;

import mainProgram.ProgettoInfo3BSpringApplication;


@RestController
public class CanteenStatusOutputController {
	
	CanteenStatusOutputIMPL canteenObj=new CanteenStatusOutputIMPL();
	
	//dobbiamo dirgli che tutti i parametri si mappano sulla richiesta --> RequestParam
	@GetMapping("/getCanteenCapacity")
	public int getCanteenCapacityAPI(@RequestParam(value="nomeMensa", defaultValue="xxx") String nomeMensa){
		System.out.println("richiesta ricevuta");
		
		Mensa mensa = new Mensa(0, nomeMensa, 0, null);
		
		return canteenObj.getCanteenCapacity(mensa);
	}
			
}
