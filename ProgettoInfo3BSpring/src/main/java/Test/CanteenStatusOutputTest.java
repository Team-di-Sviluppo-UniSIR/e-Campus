package Test;
import CampusDataManagement.CanteenStatusOutputIMPL.*;
import mainProgram.ProgettoInfo3BSpringApplication;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.mongodb.MongoClientURI;

import CampusDataManagement.Apertura;
import CampusDataManagement.CanteenStatusOutputIMPL;
import CampusDataManagement.Data;
import CampusDataManagement.DettaglioApertura;
import CampusDataManagement.Dish;
import CampusDataManagement.Mensa;
import CampusDataManagement.Menu;

public class CanteenStatusOutputTest {
	CanteenStatusOutputIMPL obj = new CanteenStatusOutputIMPL();
	MongoClientURI uri = ProgettoInfo3BSpringApplication.connectToMongo();
	
	@Test
	public void getCanteenCapacityTest() {
		Mensa m1 = new Mensa(0, "I sapori della terra", 0, null);
		assertEquals(obj.getCanteenCapacity(m1, uri),200);
	}
	
	@Test
	public void getAvailableSeatsTest() {
		Mensa m1 = new Mensa(0, "I sapori della terra", 0, null);
		DettaglioApertura d1 = new DettaglioApertura(0, "Lunedì", "Cena", null, null);
		Data data1 = new Data("04", "01", "2021");
		Apertura a1 = new Apertura(0, data1, 0, m1, d1);
		assertEquals(obj.getAvailableSeats(m1, d1, a1, uri),150);
	}
	
	@Test
	public void getDishPriceTest() {
		Mensa m1 = new Mensa(0, "I sapori della terra", 0, null);
		DettaglioApertura d1 = new DettaglioApertura(0, "Lunedì", "Cena", null, null);
		Data data1 = new Data("04", "01", "2021");
		Apertura a1 = new Apertura(0, data1, 0, m1, d1);
		Menu menu1 = new Menu(0, "pranzoLunedì", "Mediterraneo", a1);
		Dish piatto1 = new Dish(0, "Pasta al salmone", null, 0, 0, 0, menu1);
		assertEquals(obj.getDishPrice(m1, d1, a1, menu1, piatto1, uri),2.0,0);
	}
	
	

}
