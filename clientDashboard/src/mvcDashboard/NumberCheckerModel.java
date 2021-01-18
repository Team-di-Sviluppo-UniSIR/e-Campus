package mvcDashboard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import org.json.JSONObject;

/*
 *  Definiamo il modello come "Observable". Una classe model che estende
 *  "Observable" può essere gestita in modo molto più semplice, in quanto ogni
 *  modifica fatta al suo contenuto, genera in automatico una notifica
 *  per gli observer
 */
public class NumberCheckerModel extends Observable {

	// nomi delle mense
	List<String> nomiMense;

	// Campo privato, la "memoria" vera e propria del modello
	private ArrayList<Integer> numbers;
	private boolean outcome;

	// Costruttore: chiama il reset per azzerare la lista dei numeri
	NumberCheckerModel() {
		nomiMense = new ArrayList<>();
		getNomiMenseFromAPI();
		init();

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
			System.out.println("Stringa costruita: " + sb);

			// 2. Parsing della stringa come oggetto JSON, e output dei contenuti
			JSONObject o = new JSONObject(s);

			System.out.println("iterator:" + o.getJSONArray("nomiMense").iterator().next());

			Iterator<Object> menseIt = o.getJSONArray("nomiMense").iterator();
			while (menseIt.hasNext()) {
				nomiMense.add(menseIt.next().toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Reset del valore iniziale
	public void init() {

		System.out.println("[MODEL] reset ");
		numbers = new ArrayList<Integer>();
		outcome = false;
		// Comunica un cambio dello stato
		setChanged();
		// Notifica gli observer (la view)
		notifyObservers();
		System.out.println("[MOSEL] Observers notified (reset)");
	}

	// Controlla se il numero passato Ã¨ o meno giÃ  visto
	// Attenzione: non dalla GUI ma dal controller
	public void checkNumber(String num) {
		outcome = false;
		for (Integer d : numbers)
			if (d.equals(new Integer(num)))
				outcome = true;
		if (!outcome)
			numbers.add(new Integer(num));
		System.out.println("[MODEL] Check numbers " + num);
		// Comunica un cambio dello stato
		setChanged();
		// Notifica gli observer (la view)
		notifyObservers();
		System.out.println("[MDOEL] Observers notified (checkNumber)");
	}

	// Ritorna il valore dell'outcome
	public String getValue() {
		if (outcome)
			return "GiÃ  visto";
		return "Nuovo";
	}
}
