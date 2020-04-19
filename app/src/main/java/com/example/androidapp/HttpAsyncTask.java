package com.example.androidapp;

import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpAsyncTask extends AsyncTask<Void,Void,Object> {

    interface HttpAsyncTaskListener{
        void webServiceDone(String result);
        void webServiceError(Exception e);
    }

    private HttpAsyncTaskListener httpAsyncTaskListener;

    private String products_url;
    private String method = "GET";

    public HttpAsyncTask(String products_url, String method, HttpAsyncTaskListener listener) {
        httpAsyncTaskListener = listener;
        this.products_url = products_url;
        this.method = method;
    }

    @Override
    protected Object doInBackground(Void... voids) {
        try {
            return call(products_url);
        }
        catch (IOException | JSONException e) {
            return "La réponse http ne correspond pas au format souhaité";
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if(o instanceof Exception){
            httpAsyncTaskListener.webServiceError((Exception)o);
        }
        else
            httpAsyncTaskListener.webServiceDone(o.toString());
    }


    public Object call(String products_url) throws IOException, JSONException {
        URL url = new URL(products_url);
        HttpURLConnection client = null;

        client = (HttpURLConnection) url.openConnection();
        client.setRequestMethod("GET");
        client.setRequestProperty("Content-Type", "application/json; utf-8");
        client.setRequestProperty("Accept", "application/json");

        InputStream in = new BufferedInputStream(client.getInputStream());
        String responseBody = convertStreamToString(in);

        JSONObject jsonObject = new JSONObject(responseBody);
        return jsonObject.getJSONArray("items");
    }

    private String convertStreamToString(InputStream is) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

            StringBuilder stringBuffer = new StringBuilder();
            String line;

            String NL = System.getProperty("line.separator");
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line).append(NL);
            }
            bufferedReader.close();
            return stringBuffer.toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
