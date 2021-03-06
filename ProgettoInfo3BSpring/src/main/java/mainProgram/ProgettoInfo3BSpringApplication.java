package mainProgram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import CampusDataManagement.CanteenStatusOutputController;
import DataAggregator.CanteenStatusInputController;

@SpringBootApplication
@ComponentScan(basePackageClasses = CanteenStatusOutputController.class)
@ComponentScan(basePackageClasses = CanteenStatusInputController.class)
public class ProgettoInfo3BSpringApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProgettoInfo3BSpringApplication.class, args);

		/*
		 * // PROVA GET DI TUTTE LE MENSE DEL CAMPUS COME LISTA DI JSON OBJECT
		 * CanteenStatusOutputIF canteenOutput = new CanteenStatusOutputIMPL();
		 * System.out.println("Numero mense trovate: " +
		 * canteenOutput.getAllCanteens().size()); System.out.println("Mense trovate: "
		 * + canteenOutput.getAllCanteens());
		 * 
		 * // PROVA GET DI TUTTI I NOMI DELLE MENSE DEL CAMPUS COME LISTA DI STRING
		 * System.out.println("Nomi mense del campus" +
		 * canteenOutput.getAllCanteensNames());
		 * 
		 * // PROVA CAPACITA' MENSA Mensa m1 = new Mensa(0, "I sapori della terra", 0,
		 * null); System.out.println( "Capacità mensa \"" + m1.getNome() + "\": " +
		 * canteenOutput.getCanteenCapacity(m1) + " persone");
		 * 
		 * // PROVA POSTI AVAILABLE SEATS APERTURA DettaglioApertura d1 = new
		 * DettaglioApertura(0, "Lunedì", "Cena", null, null); Data data1 = new
		 * Data("11", "01", "2021"); Apertura a1 = new Apertura(0, data1, 0, m1, d1);
		 * System.out.println( "Posti disponibili mensa \"" + m1.getNome() + "\": " +
		 * canteenOutput.getAvailableSeats(m1, d1, a1));
		 * 
		 * // PROVA PIATTI DI UN MENU System.out.println("Piatti disponibili: " +
		 * canteenOutput.getAvailablePlates(m1, d1, a1, menu1));
		 * 
		 * 
		 * // PROVA PREZZO PIATTO Menu menu1 = new Menu(0, "cenaLunedì", "Mediterraneo",
		 * a1); Dish piatto1 = new Dish(0, "kiwi", null, 0, 0, 0, menu1);
		 * System.out.println("Costo Piatto \"" + piatto1.getNomePiatto() +
		 * "\" - mensa \"" + m1.getNome() + "\": " + canteenOutput.getDishPrice(m1, d1,
		 * a1, menu1, piatto1) + " € ");
		 * 
		 * // INSERIMENTO // PROVA INSERIMENTO CAPACITA Mensa m1in = new Mensa(0,
		 * "Roadhouse", 0, null); CanteenStatusInputIF canteenInput = new
		 * CanteenStatusInputIMPL(); System.out.println("Campo modificato? " +
		 * canteenInput.insertCanteenCapacity(1000, m1in));
		 * 
		 * // PROVA INSERIMENTO MENU DettaglioApertura da = new DettaglioApertura(0,
		 * "Venerdì", "Cena", null, null); Data data2 = new Data("08", "01", "2021");
		 * Apertura ap = new Apertura(0, data2, 0, m1in, da); Menu menu2 = new Menu(1,
		 * "cenaVenerdì", "menuBBQ", null); System.out.println("Campo inserito? " +
		 * canteenInput.insertDailyMenu(menu2, m1in, da, ap));
		 * 
		 * DettaglioApertura da1 = new DettaglioApertura(0, "Sabato", "Cena", null,
		 * null); Data data3 = new Data("16", "01", "2021"); Apertura ap1 = new
		 * Apertura(0, data3, 0, m1in, da1); Menu menu3 = new Menu(1, "cenaSabato",
		 * "menuBBQ", null); System.out.println("Campo inserito? " +
		 * canteenInput.insertDailyMenu(menu3, m1in, da1, ap1));
		 * 
		 * // PROVA INSERIMENTO PIATTI Dish piatto11 = new Dish(5, "Pasta alle vongole",
		 * "primo", 5, 100, 120, menu2); System.out.println("Campo inserito? " +
		 * canteenInput.insertNewDish(piatto11, menu2, m1in, da, ap)); Dish piatto12 =
		 * new Dish(6, "Pasta allo scoglio", "primo", 6, 100, 120, menu2);
		 * System.out.println("Campo inserito? " + canteenInput.insertNewDish(piatto12,
		 * menu2, m1in, da, ap)); Dish piatto13 = new Dish(7, "Pollo al forno",
		 * "secondo", 4, 200, 150, menu2); System.out.println("Campo inserito? " +
		 * canteenInput.insertNewDish(piatto13, menu2, m1in, da, ap)); Dish piatto14 =
		 * new Dish(8, "Merluzzo all'acquapazza", "secondo", 4.5, 150, 90, menu2);
		 * System.out.println("Campo inserito? " + canteenInput.insertNewDish(piatto14,
		 * menu2, m1in, da, ap));
		 * 
		 * Dish piatto15 = new Dish(1, "Risotto alla milanese", "primo", 3, 230, 170,
		 * menu3); System.out.println("Campo inserito? " +
		 * canteenInput.insertNewDish(piatto15, menu3, m1in, da1, ap1)); Dish piatto16 =
		 * new Dish(2, "Hamburger", "secondo", 4.5, 150, 90, menu2);
		 * System.out.println("Campo inserito? " + canteenInput.insertNewDish(piatto16,
		 * menu3, m1in, da1, ap1));
		 * 
		 * // PROVA UPDATE CAPACITA CanteenStatusUpdateIF canteenUpdate = new
		 * CanteenStatusUpdateIMPL(); System.out.println("Campo modificato? " +
		 * canteenUpdate.updateAvailableCapacity(450, m1in));
		 * 
		 * // PROVA UPDATE PORZIONI DISPONIBILI System.out.println( "Campo modificato? "
		 * + canteenUpdate.updateAvailablePortions(100, piatto16, menu3, m1in, da1,
		 * ap1)); System.out.println( "Campo modificato? " +
		 * canteenUpdate.updateAvailablePortions(100, piatto13, menu2, m1in, da, ap));
		 * System.out .println("Campo modificato? " +
		 * canteenUpdate.updateAvailablePortions(50, piatto1, menu1, m1, d1, a1));
		 */
		}

}
