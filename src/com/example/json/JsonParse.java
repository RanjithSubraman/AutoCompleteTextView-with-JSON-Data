package com.example.json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.pojo.SuggestGetSet;

public class JsonParse {
	double currentLatitude, currentLongitude;

	public JsonParse() {
	}

	public JsonParse(double current_latitude, double current_longitude) {
		this.currentLatitude = current_latitude;
		this.currentLongitude = current_longitude;
	}

	public List<SuggestGetSet> getParseJsonWCF(String sName) {
		List<SuggestGetSet> ListData = new ArrayList<SuggestGetSet>();
		try {
			String temp = sName.replace(" ", "%20");
			URL js = new URL(
					"http://api.goeuro.com/api/v2/position/suggest/de/" + temp);
			URLConnection jc = js.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					jc.getInputStream()));
			String line = reader.readLine();
			// JSONObject jsonResponse = new JSONObject(line);
			JSONArray jsonArray = new JSONArray(line);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject r = jsonArray.getJSONObject(i);
				ListData.add(new SuggestGetSet(r.getString("_id"), r
						.getString("fullName")));
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return ListData;

	}

}
