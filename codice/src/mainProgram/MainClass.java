package mainProgram;

import CampusDataManagement.Apertura;
import CampusDataManagement.CanteenStatusOutputIF;
import CampusDataManagement.CanteenStatusOutputIMPL;
import CampusDataManagement.Data;
import CampusDataManagement.DettaglioApertura;
import CampusDataManagement.Mensa;

public class MainClass {

	public static void main(String[] args) {
		//PROVA CAPACITA' MENSA
		Mensa m1 = new Mensa(0, "I sapori della terra", 0, null);
		CanteenStatusOutputIF canteenOutput = new CanteenStatusOutputIMPL();
		System.out.println("Capacità mensa \""  + m1.nome + "\": "  + canteenOutput.getCanteenCapacity(m1));
		
		//PROVA POSTI AVAILABLE SEATS APERTURA
		DettaglioApertura d1 = new DettaglioApertura(0, "Lunedì", "Pranzo", null, null);
		Data data1= new Data("04", "01", "2021");
		Apertura a1 = new Apertura(0, data1, 0, m1, d1);
		System.out.println("posti disponibili mensa \""  + m1.nome + "\": " 
		+ canteenOutput.getAvailableSeats(m1,d1,a1));
	}

}
