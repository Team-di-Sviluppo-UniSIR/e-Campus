package mainProgram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mongodb.MongoClientURI;

import CampusDataManagement.Apertura;
import CampusDataManagement.CanteenStatusOutputIF;
import CampusDataManagement.CanteenStatusOutputIMPL;
import CampusDataManagement.Data;
import CampusDataManagement.DettaglioApertura;
import CampusDataManagement.Dish;
import CampusDataManagement.Mensa;
import CampusDataManagement.Menu;

@SpringBootApplication
public class ProgettoInfo3BSpringApplication {

	public static MongoClientURI connectToMongo() {
		System.out.println();
		// reading config file
		String mongoConnectionString = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src" + File.separator + "main" + File.separator
					+ "java" + File.separator + "mainProgram" + File.separator + "config.txt"));
			mongoConnectionString = mongoConnectionString = reader.readLine();
			reader.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

		// stabilisco la connessione
		// database: DBCampus, collezione: DBCampusCollection
		MongoClientURI uri = new MongoClientURI(mongoConnectionString);
		return uri;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProgettoInfo3BSpringApplication.class, args);

		// getting uri to mongo
		MongoClientURI uri = connectToMongo();

		// PROVA CAPACITA' MENSA
		Mensa m1 = new Mensa(0, "I sapori della terra", 0, null);
		CanteenStatusOutputIF canteenOutput = new CanteenStatusOutputIMPL();
		// System.out.println("Capacità mensa \"" + m1.getNome() + "\": " +
		// canteenOutput.getCanteenCapacity(m1, uri));

		// PROVA POSTI AVAILABLE SEATS APERTURA
		DettaglioApertura d1 = new DettaglioApertura(0, "Lunedì", "Cena", null, null);
		Data data1 = new Data("04", "01", "2021");
		Apertura a1 = new Apertura(0, data1, 0, m1, d1);
		// System.out.println("Posti disponibili mensa \"" + m1.getNome() + "\": "+
		// canteenOutput.getAvailableSeats(m1, d1, a1, uri));

		// PROVA PREZZO PIATTO
		Menu menu1 = new Menu(0, "pranzoLunedì", "Mediterraneo", a1);
		Dish piatto1 = new Dish(0, "Pasta al salmone", null, 0, 0, 0, menu1);
		System.out.println("Costo Piatto \"" + piatto1.getNomePiatto() + "\" - mensa \"" + m1.getNome() + "\": "
				+ canteenOutput.getDishPrice(m1, d1, a1, menu1, piatto1, uri) + " € ");
	}

}
