package Test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import DataAggregator.CanteenStatusUpdateIMPL;
import dataItemClasses.Apertura;
import dataItemClasses.Data;
import dataItemClasses.DettaglioApertura;
import dataItemClasses.Dish;
import dataItemClasses.Mensa;
import dataItemClasses.Menu;

public class CanteenStatusUpdateTest {

	CanteenStatusUpdateIMPL obj = new CanteenStatusUpdateIMPL();

	@Test
	public void updateAvailableCapacityTest() {
		Mensa m1 = new Mensa(0, "Roadhouse", 0, null);
		assertEquals(obj.updateAvailableCapacity(400, m1), true);
	}

	@Test
	public void updateAvailableCapacityFailureTest() {
		Mensa m1 = new Mensa(0, "Test of failure", 0, null);
		assertEquals(obj.updateAvailableCapacity(400, m1), false);
	}

	@Test
	public void updateAvailablePortionsTest() {
		Mensa m1 = new Mensa(0, "Roadhouse", 0, null);
		Menu menu = new Menu(1, "cenaSabato", "menuBBQ", null);
		Dish piatto = new Dish(2, "Hamburger", "secondo", 4.5, 150, 90, menu);
		DettaglioApertura da = new DettaglioApertura(0, "Sabato", "Cena", null, null);
		Data data = new Data("16", "01", "2021");
		Apertura ap = new Apertura(0, data, 0, m1, da);
		assertEquals(obj.updateAvailablePortions(50, piatto, menu, m1, da, ap), true);
	}

	@Test(expected = RuntimeException.class)
	public void updateAvailablePortionsExceptionTest() {
		Mensa m1 = new Mensa(0, "Test of failure", 0, null);
		Menu menu = new Menu(1, "cenaSabato", "menuBBQ", null);
		Dish piatto = new Dish(2, "Hamburger", "secondo", 4.5, 150, 90, menu);
		DettaglioApertura da = new DettaglioApertura(0, "Sabato", "Cena", null, null);
		Data data = new Data("16", "01", "2021");
		Apertura ap = new Apertura(0, data, 0, m1, da);
		obj.updateAvailablePortions(50, piatto, menu, m1, da, ap);
	}
}
