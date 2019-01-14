package com.buxiu.bootexample.controller.base;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

public class BaseInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body,
			ClientHttpRequestExecution execution) throws IOException {
		HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
		requestWrapper.getHeaders().add("SECURITYKEY", "");
		return execution.execute(requestWrapper, body);
	}
}
