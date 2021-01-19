package mvcDashboard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class DashboardBusinessLogic {

	// nomi delle mense, capacità e posti disponibili
	private List<String> nomiMense;
	private List<Integer> capacitaMense;
	private List<Integer> availableSeatsMense;
	private Map<String, List<JSONObject>> availablePlates;

	public DashboardBusinessLogic() {
		nomiMense = new ArrayList<>();
		capacitaMense = new ArrayList<>();
		availableSeatsMense = new ArrayList<>();
		availablePlates = new HashMap<>();
		getNomiMenseFromAPI();
		getCapacitaMensaFromAPI(nomiMense);
		getAvailableSeatsFromAPI(nomiMense);
		getAvailablePlatesFromAPI(nomiMense);
		System.out.println(nomiMense.toString());
		System.out.println(capacitaMense.toString());
		System.out.println(availableSeatsMense.toString());
		System.out.println(availablePlates.toString());
	}

	public List<String> getNomiMense() {
		return nomiMense;
	}

	public List<Integer> getCapacitaMense() {
		return capacitaMense;
	}

	public List<Integer> getAvailableSeatsMense() {
		return availableSeatsMense;
	}

	public Map<String, List<JSONObject>> getAvailablePlates() {
		return availablePlates;
	}

	private void getNomiMenseFromAPI() {
		try {

			// 1. Salvataggio in una stringa della risposta del WebService
			URL url = new URL("http://localhost:8080/getAllCanteensNames");
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

			Iterator<Object> menseIt = o.getJSONArray("nomiMense").iterator();
			while (menseIt.hasNext()) {
				nomiMense.add(menseIt.next().toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getCapacitaMensaFromAPI(List<String> nomiMense) {
		try {
			for (int i = 0; i < nomiMense.size(); i++) {
				// 1. Salvataggio in una stringa della risposta del WebService
				String nome = nomiMense.get(i).replaceAll(" ", "%20");
				URL url = new URL("http://localhost:8080/getCanteenCapacity?nomeMensa=" + nome);

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

				int capacita = o.getInt("canteenCapacity");
				capacitaMense.add(capacita);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getAvailableSeatsFromAPI(List<String> nomiMense) {

		try {
			for (int i = 0; i < nomiMense.size(); i++) {
				String nome = nomiMense.get(i).replaceAll(" ", "%20");

				// 1. Salvataggio in una stringa della risposta del WebService
				URL url = new URL("http://localhost:8080/getAvailableSeats?nomeMensa=" + nome
						+ "&giornoSettimana=Domenica&tipoPasto=Pranzo&data=18-01-2021");

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

				int availableSeats = o.getInt("availableSeats");
				availableSeatsMense.add(availableSeats);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getAvailablePlatesFromAPI(List<String> nomiMense) {

		try {
			for (int i = 0; i < nomiMense.size(); i++) {
				String nome = nomiMense.get(i).replaceAll(" ", "%20");

				// 1. Salvataggio in una stringa della risposta del WebService
				URL url = new URL("http://localhost:8080/getAvailablePlates?nomeMensa=" + nome
						+ "&giornoSettimana=Domenica&tipoPasto=Pranzo&data=18-01-2021&"
						+ "nomeMenu=pranzoProvaAPI&tipoMenu=Mediterraneo");

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
				Iterator<Object> arrayPiatti = o.getJSONArray("Piatti").iterator();
				List<JSONObject> jPiatti = new ArrayList<JSONObject>();

				while (arrayPiatti.hasNext()) {
					jPiatti.add((JSONObject) arrayPiatti.next());
				}

				availablePlates.put(nomiMense.get(i), jPiatti);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
