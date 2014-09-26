package network;

import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Rustam on 25.09.2014.
 */
public class Network {

    private String handleInputStream(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder result = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        try {
            JSONObject jsonObject = new JSONObject(result.toString());
            String wind = jsonObject.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("wind").getString("speed");
            return wind;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }


    public String urlConnection() throws IOException {
        URL url = new URL("https://simple-weather.p.mashape.com/weatherdata?lat=55.755826&lng=37.617300");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Mashape-Key", "BC7dEK5spKmshP1fibz5g45eMlGcp1qZHH1jsnHEjzp7hJ4tIp");
        connection.connect();
        int code = connection.getResponseCode();
        if (code == 200) {
            InputStream in = connection.getInputStream();
            String wind = handleInputStream(in);

            return wind;
        }

        return "";
    }
}