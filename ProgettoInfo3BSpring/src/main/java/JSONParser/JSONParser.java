package JSONParser;

import java.util.ArrayList;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.client.FindIterable;

public class JSONParser {
	
	
	public static JSONObject filterInto(JSONArray startingArray, ArrayList<String> filteringArray) {
		JSONObject resultObj = null;

		for (int i = 0; i < startingArray.length(); i++) {
			JSONObject subarrayObj = startingArray.getJSONObject(i);
			switch (filteringArray.size()) {
			case 2: {
				if (subarrayObj.getString(filteringArray.get(0)).equals(filteringArray.get(1))) {
					resultObj = subarrayObj;
					break;
				}
			}
			case 4: {
				if ((subarrayObj.getString(filteringArray.get(0)).equals(filteringArray.get(1)))
						&& (subarrayObj.getString(filteringArray.get(2)).equals(filteringArray.get(3)))) {
					resultObj = subarrayObj;
					break;
				}
			}
			}
		}

		return resultObj;
	}
	
	
	/**
	 * Conta il numero di documenti restituiti da una query
	 *
	 * @param docToCount dato ottenuto dalla query
	 * @return numero di documenti nel risultato della query
	 */
	public static int countQueryResults(FindIterable<Document> docToCount) {
		int count = 0;
		for (Document d : docToCount) {
			count++;
		}
		return count;
	}

}
