package org.gloria.http;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

/**
 * <p>
 * 修改历史:											<br>
 * 修改日期    		修改人员   	版本	 		修改内容<br>
 * -------------------------------------------------<br>
 * 2017/12/15 15:39   gloria     1.0    	初始化创建<br>
 * 2017/12/18 16:01   gloria     1.1    	添加重试机制<br>
 * </p>
 *
 * @author gloria
 * @version 1.0
 * @since JDK1.8
 */
public class HttpManager {

    /**
     *  连接超时时间
     */
    private static final int CONNECTION_TIMEOUT = 1000 * 60 * 2;
    /**
     *  指定时间内服务器端没有反应
     */
    private static final int SOCKET_TIMEOUT = 1000 * 40;
    /**
     *  请求超时
     */
    private static final int REQUEST_TIMEOUT = 1000 * 40;
    /**
     *  总的连接数
     */
    private static final int MAX_TOTAL_CONNECTIONS = 5000;

    /**
     *  重试次数
     */
    private static final int RETRY = 3;
    
    /**
     *  默认编码
     */
    private static final String DEFAULT_CHARSET = "utf-8";
    
    private static CloseableHttpClient httpClient;
    private static HttpClientBuilder httpClientBuilder;
    private static RequestConfig requestConfig;
    
    public static CloseableHttpClient httpClient() {
        if (httpClient == null) {
            httpClient = httpClientBuilder().build();
        }
        return httpClient;
    }

    public static CloseableHttpClient httpClient(String charset) {
        if (httpClient == null) {
            httpClient = httpClientBuilder(charset).build();
        }
        return httpClient;
    }

    public static CloseableHttpClient httpClient(String charset, boolean isAutoRetry, Integer limitRetryCount) {
        if (httpClient == null) {
            httpClient = httpClientBuilder(charset, isAutoRetry, limitRetryCount).build();
        }
        return httpClient;
    }
    
    /**
     * 设置连接池配置项参数
     * @return  RequestConfig
     */
    private static RequestConfig createRequestConfig() {
        if (requestConfig == null) {
            requestConfig = RequestConfig.custom()
                    .setConnectTimeout(CONNECTION_TIMEOUT)
                    .setConnectionRequestTimeout(REQUEST_TIMEOUT)
                    .setSocketTimeout(SOCKET_TIMEOUT).build();
        }
        return requestConfig; 
    }
    
    private static HttpClientBuilder httpClientBuilder() {

        return httpClientBuilder(null, false, 0);
    }

    private static HttpClientBuilder httpClientBuilder(String charset) {

        return httpClientBuilder(charset, false, 0);
    }

    private static HttpClientBuilder httpClientBuilder(boolean isAutoRetry, Integer limitRetryCount) {
        return httpClientBuilder(null, isAutoRetry, limitRetryCount);
    }

    /**
     * 获取HttpClientBuilder
     * @param charset
     *                          编码
     * @param isAutoRetry
     *                          是否自动重试
     * @param limitRetryCount
     *                          重试次数
     * @return
     *                          HttpClientBuilder
     */
    private static HttpClientBuilder httpClientBuilder(String charset, 
                                                       boolean isAutoRetry, Integer limitRetryCount) {
        if (httpClientBuilder == null) {
            httpClientBuilder = HttpClients.custom();

            //连接池
            PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
            manager.setMaxTotal(MAX_TOTAL_CONNECTIONS);

            httpClientBuilder.setConnectionManager(manager);
            httpClientBuilder.setDefaultRequestConfig(createRequestConfig());

            //默认编码设置
            ConnectionConfig connConfig = ConnectionConfig.custom()
                    .setCharset(Charset.forName(DEFAULT_CHARSET)).build();
            
            //自定义编码
            if (StringUtils.isNotBlank(charset)) {
                connConfig = ConnectionConfig.custom()
                        .setCharset(Charset.forName(charset)).build();
            }
            httpClientBuilder.setDefaultConnectionConfig(connConfig);

            if (isAutoRetry) {
                //添加重试策略
                httpClientBuilder.setRetryHandler(defaultRetryHandler());
                //.setServiceUnavailableRetryStrategy(createRetryStrategy(limitRetryCount, retryInterval));
            }

        }
        return httpClientBuilder;
    }

    /**
     * 自动重试Handler，可记录判断异常信息
     * @return  HttpRequestRetryHandler
     */
    private static HttpRequestRetryHandler defaultRetryHandler() {
        return (exception, executionCount, context) -> {
            if (executionCount >= RETRY) {
                //超过指定重试次数
                return false;
            }
            if (exception instanceof InterruptedIOException) {
                // 超时
                return false;
            }
            if (exception instanceof UnknownHostException) {
                // Unknown host
                return false;
            }
            if (exception instanceof SSLException) {
                // SSL handshake exception
                return false;
            }
            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            return  !(request instanceof HttpEntityEnclosingRequest);
        };
    }

    /**
     * 自定义自动重试间隔
     * @return ServiceUnavailableRetryStrategy
     */
    private static ServiceUnavailableRetryStrategy createRetryStrategy(Integer limitRetryCount, Integer retryInterval) {
        return new ServiceUnavailableRetryStrategy() {
            /**
             * retry逻辑
             */
            @Override
            public boolean retryRequest(HttpResponse response, int executionCount, HttpContext context) {
                return executionCount <= limitRetryCount;
            }

            /**
             * retry间隔时间
             */
            @Override
            public long getRetryInterval() {
                return retryInterval;
            }
        };
    }
    
}
