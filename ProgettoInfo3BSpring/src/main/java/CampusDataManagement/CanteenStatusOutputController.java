package CampusDataManagement;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mongodb.MongoClientURI;

import mainProgram.ProgettoInfo3BSpringApplication;


public class CanteenStatusOutputController {
	
	CanteenStatusOutputIMPL canteenObj=new CanteenStatusOutputIMPL();
	MongoClientURI uri = ProgettoInfo3BSpringApplication.connectToMongo();
	
	//dobbiamo dirgli che tutti i parametri si mappano sulla richiesta --> RequestParam
	@GetMapping("/getCanteenCapacity")
	public int getCanteenCapacityAPI(@RequestParam(value="nomeMensa", defaultValue="xxx") String nomeMensa){
		Mensa mensa = new Mensa(0, nomeMensa, 0, null);

		//testtt
		Mensa mensa2 = new Mensa(0,"I sapori della terra", 0, null);
		System.out.println("Provaaa APIII: "+canteenObj.getCanteenCapacity(mensa2, uri));
		
		return canteenObj.getCanteenCapacity(mensa,uri);
	}
			
}
