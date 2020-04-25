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

    private String url;

    public HttpAsyncTask(String url, HttpAsyncTaskListener listener) {
        this.httpAsyncTaskListener = listener;
        this.url = url;
    }

    @Override
    protected Object doInBackground(Void... voids) {
        try {
            return call(this.url);
        }
        catch (JSONException e) {
            return "La réponse http ne correspond pas au format souhaité";
        }
        catch (IOException e) {
            return "InputStream bug";
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


    public Object call(String targetUrl) throws IOException, JSONException {
        URL url = new URL(targetUrl);
        HttpURLConnection client = null;

        try {
            client = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(client.getInputStream());
            String responseBody = convertStreamToString(in);
            JSONObject jsonObject = new JSONObject(responseBody);
            return jsonObject.getJSONArray("items");
        }
        finally {
            if(client != null) {
                client.disconnect();
            }
        }
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
