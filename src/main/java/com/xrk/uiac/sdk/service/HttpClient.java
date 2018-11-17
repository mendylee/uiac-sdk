package com.xrk.uiac.sdk.service;

import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.xrk.uiac.sdk.entity.ErrorResponse;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.service.http.HTTP;
import com.xrk.uiac.sdk.service.http.Request;
import com.xrk.uiac.sdk.service.http.Response;

/**
 * 
 * HttpClient: HttpClient.java.
 *
 * <br>==========================
 * <br> 公司：广州向日葵信息科技有限公司
 * <br> 开发：yexx<yexiaoxiao@xiangrikui.com>
 * <br> 版本：1.0
 * <br> 创建时间：2015年9月24日 下午12:49:18
 * <br> JDK版本：1.8
 * <br>==========================
 */
public class HttpClient
{
	private static Logger log = Logger.getLogger(HttpClient.class);
	private static Gson gson = new Gson();
	private static int timeoutSeconds = 60;
	
	public static enum Method
	{
		GET("GET"), POST("POST"), DELETE("DELETE"), PUT("PUT");
		
		private String value;
		
		Method(String value)
		{
			this.value = value;
		}
		
		public String value()
		{
			return value;
		}
	}
	
	public static <T> T get(Class<T> t, String url, Map<String, String> headers, Map<String, String> params) throws UiacException 
	{
		return request(t, Method.GET, url, headers, params);
	}
	
	public static String get(String url, Map<String, String> headers, Map<String, String> params) throws UiacException
	{
		return request(Method.GET, url, headers, params);
	}
	
	public static <T> T post(Class<T> t, String url, Map<String, String> headers, Map<String, String> params) throws UiacException 
	{
		return request(t, Method.POST, url, headers, params);
	}
	
	public static String post(String url, Map<String, String> headers, Map<String, String> params) throws UiacException
	{
		return request(Method.POST, url, headers, params);
	}
	
	public static <T> T put(Class<T> t, String url, Map<String, String> headers, Map<String, String> params) throws UiacException 
	{
		return request(t, Method.PUT, url, headers, params);
	}
	
	public static String put(String url, Map<String, String> headers, Map<String, String> params) throws UiacException
	{
		return request(Method.PUT, url, headers, params);
	}
	
	public static <T> T delete(Class<T> t, String url, Map<String, String> headers, Map<String, String> params) throws UiacException 
	{
		return request(t, Method.DELETE, url, headers, params);
	}
	
	public static String delete(String url, Map<String, String> headers, Map<String, String> params) throws UiacException
	{
		return request(Method.DELETE, url, headers, params);
	}
	
	private static <T> T request(Class<T> t, Method method, String url, Map<String, String> headers, Map<String, String> params) throws UiacException
	{
		String json = request(method, url, headers, params);
		try
		{
			T result = gson.fromJson(json, t);
			return result;
		}
		catch (Exception e)
		{
			log.error(String.format("fail to get object from json, url: %s", url), e);
			throw new UiacException(500, "-1", "fail to get object from json");
		}
	}
	
	private static String request(Method method, String url, Map<String, String> headers, Map<String, String> params) throws UiacException
	{
		log.debug(String.format("request, url: %s, method: %s", url, method.value()));
		
		try
		{
			Response response = processRequest(method, url, headers, params);
			String content = response.getContent();
			log.debug(String.format("request successfully! url: %s, response content: %s", url, content));
			
			if (response.getStatusCode() >= 300)
			{
				log.debug(String.format("http status error, url: %s, content: %s", url, content));
				ErrorResponse errorResponse = gson.fromJson(content, ErrorResponse.class);
				
				String errCode = errorResponse == null ? "-1" : errorResponse.getCode();
	        	String errMsg = errorResponse == null ? content : errorResponse.getMessage();
	        	
				throw new UiacException(response.getStatusCode(), errCode, errMsg);
			}
			return content;
		}
		catch (UiacException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			log.error(String.format("fail to request, url: %s, method: %s", url, method.value()), e);
			throw new UiacException(500, "-1", e.getMessage());
		}
	}
	
	private static Response processRequest(Method method, String url, Map<String, String> headers, Map<String, String> params)
	{
		Request request = new Request(url);
		request.setHeaders(headers);
		request.setParams(params);
		
		Response response = null;
		
		try
		{
			switch (method)
			{
				case GET:
				{
					response = HTTP.GET(request, timeoutSeconds);
					break;
				}
				case POST:
				{
					response = HTTP.POST(request, timeoutSeconds);
					break;
				}
				case DELETE:
				{
					response = HTTP.DELETE(request, timeoutSeconds);
					break;
				}
				case PUT:
				{
					response = HTTP.PUT(request, timeoutSeconds);
					break;
				}
				default:
				{
					log.error(String.format("illegal http method, method: %d, url: %s", method, url));
					throw new IllegalArgumentException("illegal http method");
				}
			}
			
			if (response == null)
			{
				log.error(String.format("response is null, url: %s", url));
				throw new RuntimeException("response is null");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error(String.format("fail to process request, url: %s, method: %s", url, method.value()), e);
			throw e;
		}
		
		return response;
	}
	
	public static void shutdown()
	{
		HTTP.shutdown();
	}
	
	public static void setTimeout(int timeout)
	{
		timeoutSeconds = timeout;
	}
}
