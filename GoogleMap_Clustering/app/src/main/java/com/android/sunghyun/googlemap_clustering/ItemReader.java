package com.android.sunghyun.googlemap_clustering;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by YKC on 2016. 11. 11..
 */

public class ItemReader {
    private static final String REGEX_INPUT_BOUNDARY_BEGINNING = "\\A";

    public List<Item> read(InputStream inputStream) throws JSONException {
        List<Item> items = new ArrayList<Item>();
        String json = new Scanner(inputStream).useDelimiter(REGEX_INPUT_BOUNDARY_BEGINNING).next();
        JSONArray array = new JSONArray(json);

        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            double lat = object.getDouble("lat");
            double lng = object.getDouble("lng");
            items.add(new Item(lat, lng));
        }

        return items;
    }
}
