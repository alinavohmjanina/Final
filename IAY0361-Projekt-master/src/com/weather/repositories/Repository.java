package com.weather.repositories;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
//ukazqvaju request, otpravljajet na server zapros s parametrami. prihodjat strokoi v 1     1formate json
abstract public class Repository {
    protected static boolean mock = false;

    public boolean isMock() {
        return mock;
    }

    public static void mock() {
        mock = true;
    }

    protected String apiKey = "23ea3da32859e7f34bdd593f012cf7b9";

    public String getApiKey() {
        return apiKey;
    }

    private final String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    protected final JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();

        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}
