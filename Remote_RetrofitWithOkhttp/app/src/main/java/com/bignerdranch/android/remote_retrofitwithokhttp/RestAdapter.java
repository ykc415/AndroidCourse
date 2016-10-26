package com.bignerdranch.android.remote_retrofitwithokhttp;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by YKC on 2016. 10. 26..
 */

public class RestAdapter {
    public static final int CONNECT_TIMEOUT = 3;
    public static final int WRITE_TIMEOUT = 5;
    public static final int READ_TIMEOUT = 3;

    private static final String SERVER_URL = "http://openapi.seoul.go.kr:8088";
    private static OkHttpClient client;
    private static ISeoulOpenData service;

    public synchronized static ISeoulOpenData getInstance() {
        if (service == null) {
            // 통신로그를 확인하기 위한 interceptor 설정
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            // 쿠키매니저 설정
            CookieManager manager = new CookieManager();
            manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

            // okHttp 설정..
            // SSL 통한 서버연결인 경우 인증서가 없으면 통신자체가 안된다
            // 이럴경우 회피하기위해 인증서를 무시하는 세팅을 해줄 수 있다.
            client = configureClient(new OkHttpClient().newBuilder())
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .cookieJar(new JavaNetCookieJar(manager))  // 쿠키를 저장하기위한 공간설정 - urlconnection 라이브러리가 필요함
                    .addInterceptor(interceptor) // 로그를 출력해준다
                    .build();

            /*#####################################################################*/

            // retrofit 설정
            service = new Retrofit.Builder()
                    .baseUrl(SERVER_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ISeoulOpenData.class);


        }
        return service;
    }


    public static OkHttpClient.Builder configureClient(final OkHttpClient.Builder builder) {

        X509TrustManager x509TrustManager = new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkServerTrusted(final X509Certificate[] chain, final String authType) {
            }

            @Override
            public void checkClientTrusted(final X509Certificate[] chain,
                                           final String authType) {
            }
        };

        final TrustManager[] certs = new TrustManager[]{x509TrustManager};

        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(null, certs, new SecureRandom());
        } catch (final java.security.GeneralSecurityException ex) {
            ex.printStackTrace();
        }

        try {
            final HostnameVerifier hostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(final String hostname, final SSLSession session) {
                    return true;
                }
            };

            builder.sslSocketFactory(ctx.getSocketFactory(), x509TrustManager).hostnameVerifier(hostnameVerifier);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return builder;
    }
}
