package com.bignerdranch.android.remote_retrofitwithokhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callHttp();
    }

    public void callHttp(){

        String key = "4c425976676b6f643437665377554c";
        String serviceName = "SeoulRoadNameInfo";
        int begin = 1;
        int end = 5;


        final Call<RemoteData> remoteData = RestAdapter.getInstance().getData(key, serviceName, begin, end);
        remoteData.enqueue(new Callback<RemoteData>() {
            @Override
            public void onResponse(Call<RemoteData> call, Response<RemoteData> response) {
                if(response.isSuccessful()) {
                    RemoteData data = response.body();
                    List<RemoteData.Row> rows = data.getSeoulRoadNameInfo().getRow();
                    for(RemoteData.Row row : rows) {
                        Log.w("[ROW]", "ROAD_NM="+row.getROAD_NM());
                    }
                } else {
                    Log.e("ERROR", response.message());
                }
            }

            @Override
            public void onFailure(Call<RemoteData> call, Throwable t) {

            }
        });
    }
}
