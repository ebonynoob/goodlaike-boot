package com.goodlaike.framework.resttemplate.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

/**
 * ErrorHolder
 * @author ：liuxing
 * @since ：2015-06-16 11:01
 */
public class ErrorHolder {

    private HttpStatus statusCode;

    private String statusText;

    private String responseBody;

    private HttpHeaders responseHeaders;

    public ErrorHolder() {

    }

    public ErrorHolder(HttpStatus statusCode, String statusText, String responseBody, HttpHeaders headers) {
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.responseBody = responseBody;
        this.responseHeaders = headers;
    }

    public ErrorHolder(String statusText) {
        this.statusText = statusText;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public HttpHeaders getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(HttpHeaders responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public static ErrorHolder build(Exception exception) {
        if (exception instanceof HttpServerErrorException) {
            HttpServerErrorException e = (HttpServerErrorException) exception;
            return new ErrorHolder(e.getStatusCode(), e.getStatusText(), e.getResponseBodyAsString(), e.getResponseHeaders());
        }

        if (exception instanceof HttpClientErrorException) {
            HttpClientErrorException e = (HttpClientErrorException) exception;
            return new ErrorHolder(e.getStatusCode(), e.getStatusText(), e.getResponseBodyAsString(), e.getResponseHeaders());
        }

        return new ErrorHolder(exception.getMessage());
    }
}
