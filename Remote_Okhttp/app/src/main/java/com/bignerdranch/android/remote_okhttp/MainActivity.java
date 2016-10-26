package com.bignerdranch.android.remote_okhttp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String key = "4c425976676b6f643437665377554c";
        String serviceName = "SeoulRoadNameInfo";
        int begin = 1;
        int end = 5;

        String url = "http://openapi.seoul.go.kr:8088/"+key+"/json/"+serviceName+"/"+begin+"/"+end+"/";

        callHttp(url);
    }

    public void callHttp(String url) {
        new AsyncTask<String,Void,String>() {

            @Override
            protected String doInBackground(String... params) {
                String result = "";
                try{
                    result = getData(params[0]);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Log.e("Result", result);

            }
        }.execute(url);
    }

    public String getData(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
