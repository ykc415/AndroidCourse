package com.bignerdranch.android.remote_httpurlconnection;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button_call);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getNaver();
            }
        });
    }

    private void getNaver() {

        new AsyncTask<Void, Void, String>() {

            ProgressDialog mProgressDialog;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mProgressDialog = new ProgressDialog(MainActivity.this);
                mProgressDialog.setTitle("다운로드");
                mProgressDialog.setMessage("downloading...");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();
            }

            @Override
            protected String doInBackground(Void... params) {
                String result = "";

                try {
                    result = Remote.getData("http://openapi.seoul.go.kr:8088/5178664a797069633936795147706d/json/CardSubwayStatisticsService/1/5/201306/");
                }catch(Exception e) {
                    e.printStackTrace();
                }
                return result;
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                StringBuffer sb = new StringBuffer();
                try {
                    // 전체 문자열을 JSON 오브젝트로 변환
                    JSONObject json = new JSONObject(s);
                    // 특정 키를 가진 단일 값을 꺼낸다
                    JSONObject topObject = json.getJSONObject("CardSubwayStatisticsService");
                    // 특정 키를 가진 배열 형태의 값을 꺼낸다
                    JSONArray rows = topObject.getJSONArray("row");


                    int rows_count = rows.length();
                    for (int i = 0; i < rows_count; i++) {
                        // 반복문을 돌면서 index로 값을 꺼낸다
                        JSONObject result = (JSONObject) rows.get(i);
                        String subway = result.getString("SUB_STA_NM");
                        sb.append(subway + "\n");
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }

                tv.setText(sb.toString());
                mProgressDialog.dismiss();

            }
        }.execute();

    }
}
