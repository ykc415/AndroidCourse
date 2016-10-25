package com.kodonho.android.remote_httpurlconnection_cookie;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by fastcampus on 2016-10-21.
 */

public class Remote {
    private static final String TAG="REMOTE";
    static CookieManager cookieManager = new CookieManager();

    static final String COOKIE_URL = "http://localHost";

    public static String getData(String webUrl) throws IOException {
        StringBuffer result = new StringBuffer();
        URL url = new URL(webUrl);
        // 아래 한줄로 webUrl 주소에 해당하는 서버와 연결이 된 상태가 된다
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //REST API = GET(조회), POST(입력)   // , DELETE, PUT(수정) - 이 두분은 지원하지 않는 서버가 있음
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK){
            BufferedReader br = new BufferedReader(new InputStreamReader( connection.getInputStream() ) );
            String dataLine = null;
            while( (dataLine = br.readLine()) != null){
                result.append(dataLine);
            }
            br.close();
        }else{
            Log.e(TAG,"HTTP error code="+responseCode);
        }
        return result.toString();
    }

    public static String postData(String webUrl, Map params, String method) throws IOException{
        StringBuffer result = new StringBuffer();
        URL url = new URL(webUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        // post 처리일 경우만 ------------
        connection.setDoOutput(true);
        OutputStream os = connection.getOutputStream();
        // Rest Api 중에 put 과 delete 를 지원하지 않으면 서버측과 협의된 protocol을 사용한다
        // 아래 예시
        if("PUT".equals(method))
            os.write(("method_override=PUT").getBytes());
        else if("DELETE".equals(method))
            os.write(("method_override=DELETE").getBytes());

        ArrayList<String> keyset = new ArrayList<>(params.keySet());
        for(String key :keyset){
            String param = key + "=" +params.get(key) + "&";
            os.write(param.getBytes());
        }
        os.flush();
        os.close();
        // -------------------------------
        // 결과 코드 담기
        int responseCode = connection.getResponseCode();
        //: 200 번은 성공
        if(responseCode == HttpURLConnection.HTTP_OK){
            // 성공했을 경우 서버에서 리턴해주는 데이터를 buffer 에 담은후에
            BufferedReader br = new BufferedReader(new InputStreamReader( connection.getInputStream() ) );
            String dataLine = null;
            // 한줄씩 읽어서 처리한다
            while( (dataLine = br.readLine()) != null){
                result.append(dataLine);
            }
            br.close();
            // 그외의 코드는 실패이므로 실패처리 코드를 로그에 찍어준다
        }else{
            Log.e(TAG,"HTTP error code="+responseCode);
        }

        // 서버측에서 넘겨받은 헤더를 파싱할 예정
        Map<String, List<String>> headers = connection.getHeaderFields();
        // 전체 헤더 로그로 보기
//        ArrayList<String> keySet = new ArrayList<>(headers.keySet());
//        for(String key : keySet){
//            for(String value: headers.get(key)) {
//                Log.i("HTTP HEADER", key + ":" + value );
//            }
//        }

        List<String> cookies = headers.get("Set-Cookie");
        if (cookies != null && cookies.size() > 0) {
            for(String cookie : cookies) {
                Log.e(TAG, "HttpCookie:"+HttpCookie.parse(cookie).get(0));
                cookieManager.getCookieStore().add(URI.create(COOKIE_URL), HttpCookie.parse(cookie).get(0));
            }
        }




        return result.toString();
    }

}
