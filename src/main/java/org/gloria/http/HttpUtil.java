package org.gloria.http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Create on 2016/12/16 21:52.
 *
 * @author : gloria.
 */
public class HttpUtil {

    private static OkHttpClient httpClient = null;

    static {

        httpClient = new OkHttpClient();

    }

    /**
     * 普通Get请求，并禁止自动重定向
     * @param url
     * @return
     */
    public static String getBodyNoRedirect(String url) {

        String body = null;
        try {
            Request request = new Request.Builder()
                    .url(url).build();
            Response response = httpClient.newBuilder()
                    .followRedirects(false)             //禁止自动重定向
                    .followSslRedirects(false)
                    .build().newCall(request).execute();

            if (response.isSuccessful()) {
                body = response.body().string();
            }

        } catch (IOException e) {
            System.out.println("get response body error ");
        }

        return body;
    }

    /**
     * 普通Get请求，如有重定向，自动处理
     * @param url
     * @return
     */
    public static String getBody(String url) {

        String body = null;
        try {
            Request request = new Request.Builder()
                    .url(url).build();
            Response response = httpClient.newCall(request).execute();

            if (response.isSuccessful()) {
                body = response.body().string();
            }

        } catch (IOException e) {
            System.out.println("get response body error ");
        }

        return body;
    }

}
