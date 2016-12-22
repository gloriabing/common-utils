package org.gloria.http;

import okhttp3.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create on 2016/12/22 17:38.
 *
 * @author : gloria.
 */
public class HttpsUtil {

    static OkHttpClient client;
    
    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cookieJar(new CookieJar() {
            private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url.host(), cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url.host());
                return cookies != null ? cookies : new ArrayList<>();
            }
        });
        client = builder.sslSocketFactory(getCertificates()) // getCertifiactes()方法在下面
                .build();
    }
    
    public static String get(String url) throws IOException {
        
        Request request = new Request.Builder()
                .url(url).build();
        Response response = client.newBuilder()
                .followRedirects(false)             //禁止自动重定向
                .followSslRedirects(false)
                .build().newCall(request).execute();
        String body = null; 
        if (response.isSuccessful()) {
            body = response.body().string();
        }
        return body;
    }

    public static String post(String url ,Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        
        params.forEach((k, v) -> builder.add(k, v));
        
        RequestBody body = builder.build();

        Request request = new Request.Builder().url(url).post(body).build();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static SSLSocketFactory getCertificates() {
        try {
            InputStream inputStream = HttpsUtil.class.getClassLoader().getResourceAsStream("*.zhihu.com.cer");
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);

            int index = 0;
            String certificateAlias = Integer.toString(index++);
            keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(inputStream));

            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
