package mvcRistoratore;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;

public class RistoratoreBusinessLogic {

	public String insertCanteenCapacityFromAPI(String nomeMensa, int capacita) {
		String status="";
		try {

			// 1. Salvataggio in una stringa della risposta del WebService
			URL url = new URL("http://localhost:8080/insertCanteenCapacity?nomeMensa=" + nomeMensa.replace(" ", "%20")
					+ "&capacita=" + Integer.toString(capacita));
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
			bufferedReader.close();
			String s = sb.toString();

			// 2. Parsing della stringa come oggetto JSON, e output dei contenuti
			JSONObject o = new JSONObject(s);

			status = o.getString("status");

		} catch (Exception e) {
			e.printStackTrace();
		}
	return status;
	}

	public String insertNewMenuFromAPI(String nomeMensa, String giornoSettimana, String tipoApertura, String data,
			String nomeMenu, String tipoMenu) {

		int idMenu = 10 + (int) (Math.random() * 1000);
		String status = "";
		try {

			// 1. Salvataggio in una stringa della risposta del WebService
			URL url = new URL("http://localhost:8080/insertDailyMenu?nomeMensa=" + nomeMensa.replace(" ", "%20")
					+ "&giornoSettimana=" + giornoSettimana + "&tipoPasto=" + tipoApertura + "&data="
					+ data.replace("/", "-") + "&idMenu=" + idMenu + "&nomeMenu=" + nomeMenu + "&tipoMenu=" + tipoMenu);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
			bufferedReader.close();
			String s = sb.toString();

			// 2. Parsing della stringa come oggetto JSON, e output dei contenuti
			JSONObject o = new JSONObject(s);

			status = o.getString("status");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public String insertDishFromAPI(String nomeMensa, String giornoSettimana, String tipoApertura, String data,
			String nomePiatto, String tipoPiatto, Double prezzo, int dispIniziale) {

		int idPiatto = 10 + (int) (Math.random() * 1000);
		String status ="";
		try {

			// 1. Salvataggio in una stringa della risposta del WebService
			URL url = new URL("http://localhost:8080/insertNewDish?nomeMensa=" + nomeMensa.replace(" ", "%20")
					+ "&giornoSettimana=" + giornoSettimana + "&tipoPasto=" + tipoApertura + "&data="
					+ data.replace("/", "-") + "&nomeMenu=pranzoDomenica&tipoMenu=vegetariano&idPiatto=" + idPiatto
					+ "&nomePiatto=" + nomePiatto.replace(" ", "%20") + "&tipoPiatto=" + tipoPiatto + "&prezzo="
					+ Double.toString(prezzo) + "&initialAvailability=" + Integer.toString(dispIniziale)
					+ "&currentAvailability=" + Integer.toString(dispIniziale));
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
			bufferedReader.close();
			String s = sb.toString();

			// 2. Parsing della stringa come oggetto JSON, e output dei contenuti
			JSONObject o = new JSONObject(s);

			status = o.getString("status");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}
