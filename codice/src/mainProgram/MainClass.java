package mainProgram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import com.mongodb.MongoClientURI;

import CampusDataManagement.Apertura;
import CampusDataManagement.CanteenStatusOutputIF;
import CampusDataManagement.CanteenStatusOutputIMPL;
import CampusDataManagement.Data;
import CampusDataManagement.DettaglioApertura;
import CampusDataManagement.Mensa;

public class MainClass {

	public static MongoClientURI connectToMongo() {
		// reading config file
		String mongoConnectionString = null;
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("src" + File.separator + "mainProgram" + File.separator + "config.txt"));
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
		// getting uri to mongo
		MongoClientURI uri = connectToMongo();

		// PROVA CAPACITA' MENSA
		Mensa m1 = new Mensa(0, "I sapori della terra", 0, null);
		CanteenStatusOutputIF canteenOutput = new CanteenStatusOutputIMPL();
		System.out.println("Capacità mensa \"" + m1.getNome() + "\": " + canteenOutput.getCanteenCapacity(m1, uri));

		// PROVA POSTI AVAILABLE SEATS APERTURA
		DettaglioApertura d1 = new DettaglioApertura(0, "Lunedì", "Pranzo", null, null);
		Data data1 = new Data("04", "01", "2021");
		Apertura a1 = new Apertura(0, data1, 0, m1, d1);
		System.out.println("Posti disponibili mensa \"" + m1.getNome() + "\": "
				+ canteenOutput.getAvailableSeats(m1, d1, a1, uri));
	}

}
