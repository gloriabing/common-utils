package org.gloria.http;

import okhttp3.*;
import org.gloria.utils.ResourcesUtil;
import org.junit.Test;
import org.omg.SendingContext.RunTime;
import org.w3c.dom.Document;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Create on 2016/12/22 17:46.
 *
 * @author : gloria.
 */
public class HttpsUtilTest {

    @Test
    public void testZhihuLogin() throws Exception {

        Properties props = ResourcesUtil.getResourceAsProperties(HttpsUtil.class.getClassLoader(), "zhihu.properties");

        String url = "https://www.zhihu.com/#signin";
        String body = HttpsUtil.get(url);

        Pattern pattern = Pattern.compile("<input type=\"hidden\" name=\"_xsrf\" value=\"([\\s\\S]*?)\"/>");

        Matcher matcher = pattern.matcher(body);
        String xsrfValue = "";
        if (matcher.find()) {
            xsrfValue = matcher.group(1);
        }

        url = "https://www.zhihu.com/login/email";
        
        Map<String, String> map = new HashMap<>();
        map.put("_xsrf", xsrfValue);
        map.put("remember_me", " true");
        map.put("email", (String) props.get("username"));
        map.put("password", (String) props.get("password"));
        
        
        System.out.println(HttpsUtil.post(url, map));
        url = "https://www.zhihu.com/#signin";
        
        body = HttpsUtil.get(url);

        System.out.println(body);
        
    }

}