package com.kodonho.android.remote_httpurlconnection_cookie;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp; //
    SharedPreferences.Editor editor;

    EditText etId;
    EditText etPassword;
    Button btnSignin;
    TextView tvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etId = (EditText) findViewById(R.id.etId);
        etPassword = (EditText) findViewById(R.id.etPassword);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnSignin = (Button) findViewById(R.id.butSign);
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();
            }
        });

        sp = getApplicationContext().getSharedPreferences("cookie", 0); //
        editor = sp.edit();

        String keyID= "USERID";
        String keyPW= "USERPW";
        tvResult.setText(keyID+"="+sp.getString("USERID", "") + ";" + keyPW+"="+sp.getString("USERPW",""));


    }

    private void signin(){

        Map userInfo = new HashMap();
        userInfo.put("user_id",etId.getText().toString());
        userInfo.put("user_pw",etPassword.getText().toString());

        new AsyncTask<Map,Void,String>(){
            ProgressDialog progress;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progress = new ProgressDialog(MainActivity.this);
                progress.setTitle("다운로드");
                progress.setMessage("downloading...");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setCancelable(false);
                progress.show();
            }
            @Override
            protected String doInBackground(Map... params) {
                String result = "";
                String url = "http://192.168.0.150:8080/setCookies.jsp";
                try {
                    result = Remote.postData(url, params[0], "POST");
                }catch(Exception e){
                    e.printStackTrace();
                }
                return result;
            }
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                List<HttpCookie> cookies = Remote.cookieManager.getCookieStore().getCookies();
                StringBuffer cookieString = new StringBuffer();


                for(HttpCookie cookie : cookies) {
                    cookieString.append(cookie.getName() + "=" + cookie.getValue() + "\n");
                    editor.putString(cookie.getName(), cookie.getValue());
                    // 삭제 editor.remove("키"), 전체삭제 editor.clear();
                }
                editor.commit();


                tvResult.setText("Cookie:"+cookieString.toString());
                progress.dismiss();
            }
        }.execute(userInfo);
    }
}
