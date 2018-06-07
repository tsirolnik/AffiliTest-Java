/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package affilitest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author debian
 */
public class AffiliTestAPI {
    
    private String apiKey;

    public AffiliTestAPI(String apiKey) {
        if(apiKey == null) {
            apiKey = "";
        }
        this.apiKey = apiKey;
    }

    public AffiliTestAPI() {
        this.apiKey = "";
    }

    public JsonElement login(String email, String password) throws Exception {
        CookieHandler.setDefault(new CookieManager());
        HashMap<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        return this.post(Endpoints.LOGIN, params);
    }

    public JsonElement logout() throws Exception {
        return this.post(Endpoints.LOGOUT, null);
    }

    public JsonElement test(String url, String country, String device) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("url", url);
        params.put("device", device);
        params.put("country", country);
        return this.post(Endpoints.TEST, params);
    }

    public JsonElement compareToPreview(String url, String previewURL, String country, String device) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("url", url);
        params.put("previewURL", previewURL);
        params.put("device", device);
        params.put("country", country);
        return this.post(Endpoints.COMPARE_TO_PREVIEW, params);
    }

    public JsonElement appInfo(String url, String packageIdentifier, String country) throws Exception {
        if(url.length() == 0 && packageIdentifier.length() == 0) {
            throw new Exception("Missing parameters");
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("country", country);

        if(url.length() > 0 ) {
            params.put("url", url);
            return this.get(Endpoints.APP_INFO, params);
        }

        params.put("package", packageIdentifier);
        return this.get(Endpoints.APP_INFO, params);
    }

    public JsonElement callsLeft() throws Exception {
        return this.get(Endpoints.CALLS_LEFT, null);
    }



    private JsonElement get(String endpoint, HashMap<String,String> queryParams) throws Exception {
        String params = "";
        if(queryParams != null && queryParams.size() > 0) {
            params = "?" + getPostDataString(queryParams);
        }

        endpoint = endpoint + params;
        HttpsURLConnection conn = getConnection("GET", endpoint);
        String responseBody = getResponseBody(conn);
        
        JsonParser parser = new JsonParser();
        return parser.parse(responseBody);
    }

    private JsonElement post(String endpoint, HashMap<String,String> params) throws Exception {

        HttpsURLConnection conn = getConnection("POST", endpoint);

        conn.setDoInput(true);

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(getPostDataString(params));

        writer.flush();
        writer.close();
        os.close();

        String responseBody = getResponseBody(conn);

        JsonParser parser = new JsonParser();
        return parser.parse(responseBody);
    }

    private HttpsURLConnection getConnection(String method, String addr) throws Exception {
        HttpsURLConnection conn = (HttpsURLConnection) (new URL(addr).openConnection());
        conn.setRequestMethod(method);
        conn.setDoOutput(true);
        if(this.apiKey.length() != 0) {
            conn.addRequestProperty("Authorization", "AT-API " + this.apiKey);
        }
        return conn;
    }

    private String getResponseBody(HttpsURLConnection httpConnection) throws IOException {
        String responseBody = "", responseLine;
        BufferedReader bufferedReader;
        if (200 <= httpConnection.getResponseCode() && httpConnection.getResponseCode() <= 299) {
            bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
        }
        while ((responseLine=bufferedReader.readLine()) != null) {
            responseBody += responseLine;
        }
        return responseBody;
    }


    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }


}
