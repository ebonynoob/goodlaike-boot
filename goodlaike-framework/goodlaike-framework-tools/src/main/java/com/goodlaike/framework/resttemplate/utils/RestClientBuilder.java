package com.goodlaike.framework.resttemplate.utils;


import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.goodlaike.framework.resttemplate.config.ResourceUtils;

/**
 * 使用spring的restTemplate替代httpclient工具
 * @author ：liuxing
 * @since ：2015-05-18 08:48
 */
public class RestClientBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestClientBuilder.class);

    /**
     * 保持长连接的时间
     */
    private static final String TIME_TOLIVE = "timeToLive";
    /**
     * 总连接数
     */
    private static final String MAX_TOTAL = "maxTotal";
    /**
     * 同路由的并发数
     */
    private static final String MAX_PERROUTE = "maxPerRoute";
    /**
     * 重试开关
     */
    private static final String REQUEST_SENTRETRY_ENABLED = "requestSentRetryEnabled";
    /**
     * 重试次数
     */
    private static final String RETRY_COUNT = "retryCount";
    /**
     * 连接超时
     */
    private static final String CONNECT_TIMEOUT = "connectTimeout";

    /**
     * 数据读取超时时间，即SocketTimeout
     */
    private static final String READ_TIMEOUT = "readTimeout";

    /**
     * 获取连接的等待时间
     */
    private static final String CONNECTION_REQUEST_TIMEOUT = "connectionRequestTimeout";

    private static RestTemplate restTemplate;

    static {
        SeProperties config = ResourceUtils.getRestConfig();

        // 长连接保持30秒
        long timeToLive = config.getLongValue(TIME_TOLIVE, 30L);
        PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager(timeToLive, TimeUnit.SECONDS);

        // 总连接数
        int maxTotal = config.getIntValue(MAX_TOTAL, 200);
        pollingConnectionManager.setMaxTotal(maxTotal);

        // 同路由的并发数
        int maxPerRoute = config.getIntValue(MAX_PERROUTE, 200);
        pollingConnectionManager.setDefaultMaxPerRoute(maxPerRoute);

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(pollingConnectionManager);

        // 重试次数，默认是3次，没有开启
        int retryCount = config.getIntValue(RETRY_COUNT, 2);
        boolean requestSentRetryEnabled = config.getBooleanValue(REQUEST_SENTRETRY_ENABLED, true);
        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(retryCount, requestSentRetryEnabled));

        // 保持长连接配置，需要在头添加Keep-Alive
        httpClientBuilder.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE);

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6"));
        headers.add(new BasicHeader("Connection", "keep-alive"));

        httpClientBuilder.setDefaultHeaders(headers);

        HttpClient httpClient = httpClientBuilder.build();

        // httpClient连接配置，底层是配置RequestConfig
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

        // 连接超时
        int connectTimeout = config.getIntValue(CONNECT_TIMEOUT, 5000);
        clientHttpRequestFactory.setConnectTimeout(connectTimeout);

        // 数据读取超时时间，即SocketTimeout
        int readTimeout = config.getIntValue(READ_TIMEOUT, 5000);
        clientHttpRequestFactory.setReadTimeout(readTimeout);

        // 连接不够用的等待时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的
        int connectionRequestTimeout = config.getIntValue(CONNECTION_REQUEST_TIMEOUT, 200);
        clientHttpRequestFactory.setConnectionRequestTimeout(connectionRequestTimeout);

        // 添加内容转换器
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("utf-8")));
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new MappingJackson2XmlHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        messageConverters.add(new ByteArrayHttpMessageConverter());

        restTemplate = new RestTemplate(messageConverters);
        restTemplate.setRequestFactory(clientHttpRequestFactory);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

        LOGGER.debug("RestClient初始化完成");
    }

    private RestClientBuilder() {

    }

    public static RestTemplate build() {
        return restTemplate;
    }

}
