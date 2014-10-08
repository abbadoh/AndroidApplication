package api;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class Api {
    protected String API_PROTOCOL;
    protected String API_HOST;
    protected String API_PATH;

    protected JSONObject getConnectionJsonResult(HttpURLConnection connection) {
        JSONObject result = null;

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line;
            StringBuilder raw = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                raw.append(line);
            }

            reader.close();

            result = new JSONObject(raw.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    protected URL getApiMethodUrl(String method, List<NameValuePair> params) {
        URL url = null;
        String path = API_PATH + method;

        try {
            URI uri = URIUtils.createURI(API_PROTOCOL, API_HOST, -1, path, URLEncodedUtils.format(params, "UTF-8"), null);

            url = uri.toURL();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    protected JSONObject getApiMethodJsonResult(String method, List<NameValuePair> params) {
        JSONObject result = null;
        URL url = getApiMethodUrl(method, params);

        if (url != null) {
            try {
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();

                connection.setRequestMethod("GET");
                connection.connect();

                result = getConnectionJsonResult(connection);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}