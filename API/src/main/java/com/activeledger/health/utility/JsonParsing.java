package com.activeledger.health.utility;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParsing {
	
	
	private Map<String, String> map;
	
	
	public JsonParsing()
	{
		map=new HashMap<>();
	}

	public void getArray(Object object2) throws ParseException {

		JSONArray jsonArr = (JSONArray) object2;

		for (int k = 0; k < jsonArr.length(); k++) {

			if (jsonArr.get(k) instanceof JSONObject) {
				parseJson((JSONObject) jsonArr.get(k));
			}

		}
	}

	public Map<String, String> parseJson(JSONObject jsonObject) throws ParseException {

		Set<String> set = jsonObject.keySet();
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			Object obj = iterator.next();
			if (jsonObject.get(obj.toString()) instanceof JSONArray) {
				getArray(jsonObject.get(obj.toString()));
			} else {
				if (jsonObject.get(obj.toString()) instanceof JSONObject) {
					parseJson((JSONObject) jsonObject.get(obj.toString()));
				} else {

					map.put(obj.toString(), jsonObject.get(obj.toString()).toString());

				}
			}
		}
		return map;
	}
}
