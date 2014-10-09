package api;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class YandexTranslateApi extends Api {
    protected final String API_KEY = "trnsl.1.1.20141007T183220Z.6daee62b7f2b32ba.3ae864c474fd3f8cb5fc29c7e0b766bdd43ba3ba";

    public YandexTranslateApi() {
        this.API_PROTOCOL = "https";
        this.API_HOST = "translate.yandex.net";
        this.API_PATH = "/api/v1.5/tr.json/";
    }

    public HashMap<String, String> getLanguages(String ui) {
        HashMap<String, String> languages = null;

        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("key", API_KEY));
            params.add(new BasicNameValuePair("ui", ui));

            JSONObject result = getApiMethodJsonResult("getLangs", params);

            if (result != null && result.has("dirs")) {
                JSONArray dirs = result.getJSONArray("dirs");
                int dirsLength = dirs.length();
                boolean hasLangs = result.has("langs");
                JSONObject langs = (hasLangs) ? result.getJSONObject("langs") : null;

                languages = new HashMap<String, String>();

                for (int i = 0; i < dirsLength; i++) {
                    String key = dirs.getString(i);
                    String value = null;

                    if (hasLangs) {
                        String[] parsed = key.split("-", 2);
                        if (parsed.length == 2 && langs.has(parsed[0]) && langs.has(parsed[1])) {
                            value = String.format("%s -> %s", langs.getString(parsed[0]), langs.getString(parsed[1]));
                        }
                    }

                    if (value == null) {
                        value = key;
                    }

                    languages.put(key, value);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return languages;
    }

    public String translate(String text, String direction) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("key", API_KEY));
        params.add(new BasicNameValuePair("text", text));
        params.add(new BasicNameValuePair("lang", direction));

        JSONObject result = getApiMethodJsonResult("translate", params);
        String translation = null;

        if (result != null) {
            try {
                int code = result.getInt("code");
                if (code == 200) {
                    translation = result.getJSONArray("text").getString(0);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return translation;
    }
}