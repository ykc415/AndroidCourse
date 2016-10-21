package com.bignerdranch.android.remote_httpurlconnection;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by YKC on 2016. 10. 21..
 */

public class Remote {

    private static final String TAG="REMOTE";

    public static String getData(String webUrl) throws IOException {
        StringBuffer result = new StringBuffer();
        URL url = new URL(webUrl);

        // 아래 한줄로 webUrl 주소에 해당하는 서버와 연결이 된 상태가 된다
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // REST API = GET(조회), POST(입력) //  DELETE, PUT(수정) - 이 두부분은 지원하지 않는 서버가 있음
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String dataLine = null;
            while((dataLine = br.readLine()) != null) {
                result.append(dataLine);
            }
            br.close();
        } else {
            Log.e(TAG, "HTTP error code = " + responseCode);
        }

        return result.toString();
    }


}
