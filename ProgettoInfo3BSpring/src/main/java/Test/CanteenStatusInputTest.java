package Test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import DataAggregator.CanteenStatusInputIMPL;
import dataItemClasses.Apertura;
import dataItemClasses.Data;
import dataItemClasses.DettaglioApertura;
import dataItemClasses.Dish;
import dataItemClasses.Mensa;
import dataItemClasses.Menu;

public class CanteenStatusInputTest {

	CanteenStatusInputIMPL obj = new CanteenStatusInputIMPL();

	@Test
	public void insertCanteenCapacityTest() {
		Mensa m1 = new Mensa(0, "Roadhouse", 0, null);
		assertEquals(obj.insertCanteenCapacity(400, m1),true);
	}
	
	@Test
	public void insertCanteenCapacityFailureTest() {
	    Mensa m1 = new Mensa(0, "Test of failure", 0, null);
	    assertEquals(obj.insertCanteenCapacity(400, m1),false);
	}
	
	@Test
	public void insertDailyMenuTest() {
		Mensa m1 = new Mensa(0, "Roadhouse", 0, null);
		Menu menu = new Menu(1, "cenaSabato", "menuBBQ", null);
		DettaglioApertura da = new DettaglioApertura(0, "Sabato", "Cena", null, null);
		Data data = new Data("23", "01", "2021");
		Apertura ap = new Apertura(0, data, 0, m1, da);
		assertEquals(obj.insertDailyMenu(menu, m1, da, ap),true);
	}
	
	@Test(expected = RuntimeException.class)
	public void insertDailyMenuExceptionTest() {
		Mensa m1 = new Mensa(0, "Test of failure", 0, null);
		Menu menu = new Menu(1, "cenaSabato", "menuBBQ", null);
		DettaglioApertura da = new DettaglioApertura(0, "Sabato", "Cena", null, null);
		Data data = new Data("23", "01", "2021");
		Apertura ap = new Apertura(0, data, 0, m1, da);
		obj.insertDailyMenu(menu, m1, da, ap);
	}
	
	@Test
	public void insertNewDishTest() {
		Mensa m1 = new Mensa(0, "Roadhouse", 0, null);
		Menu menu = new Menu(1, "cenaSabato", "menuBBQ", null);
		Dish piatto = new Dish(2, "Pasta al ragù di cinghiale", "primo", 4, 130, 100, menu);
		DettaglioApertura da = new DettaglioApertura(0, "Sabato", "Cena", null, null);
		Data data = new Data("23", "01", "2021");
		Apertura ap = new Apertura(0, data, 0, m1, da);
		assertEquals(obj.insertNewDish(piatto, menu, m1, da, ap),true);
	}
	
	@Test(expected = RuntimeException.class)
	public void insertNewDishExceptionTest() {
		Mensa m1 = new Mensa(0, "Test of failure", 0, null);
		Menu menu = new Menu(1, "cenaSabato", "menuBBQ", null);
		Dish piatto = new Dish(2, "Pasta al ragù di cinghiale", "primo", 4, 130, 100, menu);
		DettaglioApertura da = new DettaglioApertura(0, "Sabato", "Cena", null, null);
		Data data = new Data("23", "01", "2021");
		Apertura ap = new Apertura(0, data, 0, m1, da);
		obj.insertNewDish(piatto, menu, m1, da, ap);
	}
	
	@Test
	public void unimplementedMethodTest() {
		assertEquals(obj.insertDailyOpeningHours(null, null, null),false);	
	}	
}
