package org.gloria.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * <p>
 * 修改历史:											<br>
 * 修改日期    		修改人员   	版本	 		修改内容<br>
 * -------------------------------------------------<br>
 * 2017/12/15 20:11   gloria     1.0    	初始化创建<br>
 * </p>
 *
 * @author gloria
 * @version 1.0
 * @since JDK1.8
 */
public class HttpClientUtil {
    
    private static final Integer BODY_NULL = -1;

    public static ResponseBody doGet(String url) {
        return doGet(url, null, null);
    }
    
    public static ResponseBody doGet(String url, String charset) {
        return doGet(url, charset, null);
    }
    
    public static ResponseBody doGet(String url, Map<String, String> headers) {
        return doGet(url, null, headers);
    }
    
    /**
     * GET请求
     * @param url
     *                  目标url
     * @param charset
     *                  编码
     * @param headers
     *                  自定义头信息
     * @return
     *                  ResponseBody
     */
    public static ResponseBody doGet(String url, String charset, Map<String, String> headers) {
        ResponseBody responseBody = new ResponseBody();
        
        HttpGet httpGet = new HttpGet(url);

        //设置Header
        if (headers != null && !headers.isEmpty()) {
            for (String key : headers.keySet()) {
                httpGet.addHeader(key, headers.get(key));
            }
        }
        CloseableHttpResponse response = null;
        try {
            response = HttpManager.httpClient(null, true, 3).execute(httpGet);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    responseBody.setBody(EntityUtils.toString(entity, charset));
                    responseBody.setCode(HttpStatus.SC_OK);
                } else {
                    responseBody.setBody(null);
                    responseBody.setCode(BODY_NULL);
                }
                //释放连接
                EntityUtils.consume(entity);
            } else {
                responseBody.setBody(null);
                responseBody.setCode(response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            //TODO 异常处理
            e.printStackTrace();
        } finally {
            //释放连接
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseBody;
    }


    private static String downLoadFile(CloseableHttpResponse response, String fileFullPath) throws IllegalStateException, IOException {
        File file = new File(fileFullPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        InputStream in = response.getEntity().getContent();
        FileOutputStream out = new FileOutputStream(new File(fileFullPath));
        byte[] b = new byte[1024];
        int len = 0;
        while ((len = in.read(b)) != -1) {
            out.write(b, 0, len);
        }
        in.close();
        out.close();
        return "download " + fileFullPath + " success!";
    }
}
