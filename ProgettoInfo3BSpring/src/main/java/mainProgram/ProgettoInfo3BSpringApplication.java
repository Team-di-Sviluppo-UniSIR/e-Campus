package mainProgram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import CampusDataManagement.CanteenStatusOutputController;
import CampusDataManagement.CanteenStatusOutputIF;
import CampusDataManagement.CanteenStatusOutputIMPL;
import DataAggregator.CanteenStatusInputController;
import DataAggregator.CanteenStatusInputIF;
import DataAggregator.CanteenStatusInputIMPL;
import DataAggregator.CanteenStatusUpdateIF;
import DataAggregator.CanteenStatusUpdateIMPL;
import dataItemClasses.Apertura;
import dataItemClasses.Data;
import dataItemClasses.DettaglioApertura;
import dataItemClasses.Dish;
import dataItemClasses.Mensa;
import dataItemClasses.Menu;

@SpringBootApplication
@ComponentScan(basePackageClasses = CanteenStatusOutputController.class)
@ComponentScan(basePackageClasses = CanteenStatusInputController.class)
public class ProgettoInfo3BSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgettoInfo3BSpringApplication.class, args);

		// PROVA CAPACITA' MENSA
		Mensa m1 = new Mensa(0, "I sapori della terra", 0, null);
		CanteenStatusOutputIF canteenOutput = new CanteenStatusOutputIMPL();
		System.out.println(
				"Capacità mensa \"" + m1.getNome() + "\": " + canteenOutput.getCanteenCapacity(m1) + " persone");

		// PROVA POSTI AVAILABLE SEATS APERTURA
		DettaglioApertura d1 = new DettaglioApertura(0, "Lunedì", "Cena", null, null);
		Data data1 = new Data("04", "01", "2021");
		Apertura a1 = new Apertura(0, data1, 0, m1, d1);
		System.out.println(
				"Posti disponibili mensa \"" + m1.getNome() + "\": " + canteenOutput.getAvailableSeats(m1, d1, a1));

		// PROVA PREZZO PIATTO
		Menu menu1 = new Menu(0, "cenaLunedì", "Mediterraneo", a1);
		Dish piatto1 = new Dish(0, "Pasta al salmone", null, 0, 0, 0, menu1);
		System.out.println("Costo Piatto \"" + piatto1.getNomePiatto() + "\" - mensa \"" + m1.getNome() + "\": "
				+ canteenOutput.getDishPrice(m1, d1, a1, menu1, piatto1) + " € ");
		
		// INSERIMENTO
		// PROVA INSERIMENTO CAPACITA
		Mensa m1in = new Mensa(0, "Roadhouse", 0, null);
		CanteenStatusInputIF canteenInput = new CanteenStatusInputIMPL();
		System.out.println("Campo modificato? " + canteenInput.insertCanteenCapacity(9992, m1in));
		
		// PROVA INSERIMENTO MENU
		DettaglioApertura da = new DettaglioApertura(0, "Venerdì", "Cena", null, null);
		Data data2 = new Data("08", "01", "2021");
		Apertura ap = new Apertura(0, data2, 0, m1in, da);
		Menu menu2 = new Menu(1, "Marco", "Giordano", null);
		System.out.println("Campo inserito? " + canteenInput.insertDailyMenu(menu2, m1in, da, ap));
		
		//PROVA INSERIMENTO PIATTO
		Dish piatto11 = new Dish(5, "Pasta alle vongole", "primo", 5, 100, 120, menu2);
		System.out.println("Campo inserito? " + canteenInput.insertNewDish(piatto11, menu2, m1in, da, ap));
		Dish piatto12 = new Dish(5, "Pasta allo scoglio", "primo", 6, 100, 120, menu2);
		System.out.println("Campo inserito? " + canteenInput.insertNewDish(piatto12, menu2, m1in, da, ap));
	
		//PROVA UPDATE CAPACITA
		CanteenStatusUpdateIF canteenUpdate = new CanteenStatusUpdateIMPL();
		System.out.println("Campo modificato? " + canteenUpdate.updateAvailableCapacity(450, m1in));

	}
	
}
