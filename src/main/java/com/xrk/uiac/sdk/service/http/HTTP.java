package com.xrk.uiac.sdk.service.http;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.google.common.util.concurrent.CheckedFuture;

/**
 * 
 * 引用hws-http客户端，修改日志输出方式
 * 增加delete、put方法
 *
 * <br>==========================
 * <br> 公司：广州向日葵信息科技有限公司
 * <br> 开发：yexx<yexiaoxiao@xiangrikui.com>
 * <br> 版本：1.0
 * <br> 创建时间：2015年9月24日 下午3:41:14
 * <br> JDK版本：1.8
 * <br>==========================
 */
public class HTTP
{
	private static Logger log = Logger.getLogger(HTTP.class);
	
	/**
	 * 
	 * get请求，同步处理
	 *    
	 * @param request
	 * @param seconds
	 * @return
	 */
	public static Response GET(Request request, int seconds) 
	{
        CheckedFuture<com.ning.http.client.Response, HttpIoException> future = SimpleHttpClient.getInstance().doGet(request.url, request.getParams(), request.getHeaders(), request.getClientCookies(), null);
        try 
        {
        	com.ning.http.client.Response response= future.checkedGet(seconds, TimeUnit.SECONDS);
        	if (response == null) 
        	{
        		log.error(String.format("HTTP GET, cannot connected to server, timeout, seconds: %d", seconds));
        		throw new RuntimeException("HTTP GET, cannot connected to server, timeout");
        	}
            return new Response(response);
        } 
        catch (Exception e) 
        {
        	log.error("HTTP GET, runtime error", e);
            throw new RuntimeException(e);
        }
	}
	
	/**
	 * 
	 * delete请求，同步处理 
	 *    
	 * @param request
	 * @param seconds
	 * @return
	 */
	public static Response DELETE(Request request, int seconds)
	{
		CheckedFuture<com.ning.http.client.Response, HttpIoException> future = SimpleHttpClient.getInstance().doDelete(request.url, request.getParams(), request.getHeaders(), request.getClientCookies(), null);
        try 
        {
        	com.ning.http.client.Response response= future.checkedGet(seconds, TimeUnit.SECONDS);
        	if (response == null) 
        	{
        		log.error(String.format("HTTP DELETE, cannot connected to server, timeout, seconds: %d", seconds));
        		throw new RuntimeException("HTTP DELETE, cannot connected to server, timeout");
        	}
            return new Response(response);
        } 
        catch (Exception e) 
        {
        	log.error("HTTP DELETE, runtime error", e);
            throw new RuntimeException(e);
        }
	}
	
	/**
	 * 
	 * put请求，同步处理
	 *    
	 * @param request
	 * @param seconds
	 * @return
	 */
	public static Response PUT(Request request, int seconds)
	{
		CheckedFuture<com.ning.http.client.Response, HttpIoException> future = SimpleHttpClient.getInstance().doPut(request.url, request.getParams(), request.getHeaders(), request.getClientCookies(), null);
        try 
        {
        	com.ning.http.client.Response response= future.checkedGet(seconds, TimeUnit.SECONDS);
        	if (response == null) 
        	{
        		log.error(String.format("HTTP PUT, cannot connected to server, timeout, seconds: %d", seconds));
        		throw new RuntimeException("HTTP PUT, cannot connected to server, timeout");
        	}
            return new Response(response);
        } 
        catch (Exception e) 
        {
        	log.error("HTTP PUT, runtime error", e);
            throw new RuntimeException(e);
        }
	}
	
	/**
	 * 
	 * post请求，同步处理
	 *    
	 * @param request
	 * @param seconds
	 * @return
	 */
	public static Response POST(Request request, int seconds) 
	{
        CheckedFuture<com.ning.http.client.Response, HttpIoException> future = SimpleHttpClient.getInstance().doPost(request.url, request.getParams(), request.getHeaders(), request.getClientCookies(), null);
        try 
        {
        	com.ning.http.client.Response response = future.checkedGet(seconds, TimeUnit.SECONDS);
        	if (response == null) 
        	{
        		log.error(String.format("HTTP POST, cannot connected to server, timeout, seconds: %d", seconds));
        		throw new RuntimeException("HTTP POST, cannot connected to server, timeout");
        	}
            return new Response(response);
        } 
        catch (Exception e) 
        {
        	log.error("HTTP POST, runtime error", e);
            throw new RuntimeException(e);
        }
	}
	
	public static void shutdown()
	{
		SimpleHttpClient.getInstance().shutdown();
	}
}