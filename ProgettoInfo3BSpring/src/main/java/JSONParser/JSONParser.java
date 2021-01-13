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
	
	public static int countQueryResults(FindIterable<Document> docToCount) {
		int count = 0;
		for (Document d : docToCount) {
			count++;
		}

		/*
		 * Introdurre test invece di usare questo metodo
		 * System.out.println("numero di risultati:"+count);
		 */

		return count;
	}
	
	public static Object getKey(JSONArray array, String key, Object filtro) {
		Object value = -1;
		for (int i = 0; i < array.length(); i++) {
			JSONObject item = array.getJSONObject(i);
			if (item.keySet().contains(key)) {
				value = item.get(key);
				if (value.equals((String) filtro))
					break;
			}
		}

		return value;
	}

}
