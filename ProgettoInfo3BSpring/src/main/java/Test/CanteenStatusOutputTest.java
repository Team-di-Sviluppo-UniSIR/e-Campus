package Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

import CampusDataManagement.CanteenStatusOutputIMPL;
import dataItemClasses.Apertura;
import dataItemClasses.Data;
import dataItemClasses.DettaglioApertura;
import dataItemClasses.Dish;
import dataItemClasses.Mensa;
import dataItemClasses.Menu;

public class CanteenStatusOutputTest {
	
	CanteenStatusOutputIMPL obj = new CanteenStatusOutputIMPL();
	
	@Test
	public void getAllCanteensTest() {
		assertNotNull("", obj.getAllCanteens());
	}
	
	@Test
	public void getAllCanteensNamesTest() {
		assertNotNull("", obj.getAllCanteensNames());
	}
	
	@Test
	public void getCanteenCapacityTest() {
		Mensa m1 = new Mensa(0, "I sapori della terra", 0, null);
		assertEquals(obj.getCanteenCapacity(m1),300);
	}
	
	@Test(expected = RuntimeException.class)
	public void getCanteenCapacityExceptionTest() {
	    Mensa m1 = new Mensa(0, "Test of failure", 0, null);
		obj.getCanteenCapacity(m1);
	}
	
	@Test
	public void getAvailableSeatsTest() {
		Mensa m1 = new Mensa(0, "I sapori della terra", 0, null);
		DettaglioApertura d1 = new DettaglioApertura(0, "Lunedì", "Cena", null, null);
		Data data1 = new Data("04", "01", "2021");
		Apertura a1 = new Apertura(0, data1, 0, m1, d1);
		assertEquals(obj.getAvailableSeats(m1, d1, a1),150);
	}
	
	@Test(expected = RuntimeException.class)
	public void getAvailableSeatsExceptionTest() {
	    Mensa m1 = new Mensa(0, "Test of failure", 0, null);
	    DettaglioApertura d1 = new DettaglioApertura(0, "Lunedì", "Cena", null, null);
		Data data1 = new Data("04", "01", "2021");
		Apertura a1 = new Apertura(0, data1, 0, m1, d1);
		obj.getAvailableSeats(m1, d1, a1);
	}
	
	@Test
	public void getAvailablePlatesTest() {
		Mensa m1 = new Mensa(0, "I sapori della terra", 0, null);
		DettaglioApertura d1 = new DettaglioApertura(0, "Lunedì", "Cena", null, null);
		Data data1 = new Data("04", "01", "2021");
		Apertura a1 = new Apertura(0, data1, 0, m1, d1);
		Menu menu1 = new Menu(0, "pranzoLunedì", "Mediterraneo", a1);
		assertNotNull("", obj.getAvailablePlates(m1, d1, a1, menu1));	
	}
	
	@Test(expected = RuntimeException.class)
	public void getAvailablePlatesExceptionTest() {
		Mensa m1 = new Mensa(0, "Test of failure", 0, null);
		DettaglioApertura d1 = new DettaglioApertura(0, "Lunedì", "Cena", null, null);
		Data data1 = new Data("04", "01", "2021");
		Apertura a1 = new Apertura(0, data1, 0, m1, d1);
		Menu menu1 = new Menu(0, "pranzoLunedì", "Mediterraneo", a1);
		assertNotNull("", obj.getAvailablePlates(m1, d1, a1, menu1));	
	}
	
	@Test
	public void getDishPriceTest() {
		Mensa m1 = new Mensa(0, "I sapori della terra", 0, null);
		DettaglioApertura d1 = new DettaglioApertura(0, "Lunedì", "Cena", null, null);
		Data data1 = new Data("04", "01", "2021");
		Apertura a1 = new Apertura(0, data1, 0, m1, d1);
		Menu menu1 = new Menu(0, "pranzoLunedì", "Mediterraneo", a1);
		Dish piatto1 = new Dish(0, "Pasta al salmone", null, 0, 0, 0, menu1);
		assertEquals(obj.getDishPrice(m1, d1, a1, menu1, piatto1),2.0,0);
	}
	
	@Test(expected = RuntimeException.class)
	public void getDishPriceExceptionTest() {
	    Mensa m1 = new Mensa(0, "Test of failure", 0, null);
	    DettaglioApertura d1 = new DettaglioApertura(0, "Lunedì", "Cena", null, null);
		Data data1 = new Data("04", "01", "2021");
		Apertura a1 = new Apertura(0, data1, 0, m1, d1);
		Menu menu1 = new Menu(0, "pranzoLunedì", "Mediterraneo", a1);
		Dish piatto1 = new Dish(0, "Pasta al salmone", null, 0, 0, 0, menu1);
		obj.getDishPrice(m1, d1, a1, menu1, piatto1);
	}
	
	@Test
	public void unimplementedMethodTest() {
		assertEquals(obj.getWaitingTime(),null);
		assertEquals(obj.getCanteenStatus(),null);
		assertEquals(obj.getCanteenETA(),null);
		assertEquals(obj.getOpeningHours(),null);	
	}	
	
}
