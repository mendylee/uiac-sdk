package com.xrk.uiac.sdk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.xrk.uiac.sdk.common.CONST;
import com.xrk.uiac.sdk.handler.IAccountHandler;
import com.xrk.uiac.sdk.handler.IAuthorizationHandler;
import com.xrk.uiac.sdk.handler.account.AccountHandlerImpl;
import com.xrk.uiac.sdk.handler.authorization.AuthorizationHandlerImpl;
import com.xrk.uiac.sdk.service.HttpClient;

/**
 * 
 * UiacClient: UiacClient.java.
 *
 * <br>==========================
 * <br> 公司：广州向日葵信息科技有限公司
 * <br> 开发：shunchiguo<shunchiguo@xiangrikui.com>
 * <br> 版本：1.0
 * <br> 创建时间：2015年7月15日
 * <br> JDK版本：1.7
 * <br>==========================
 */
public class UiacClient
{
	private static Logger log = Logger.getLogger(UiacClient.class);
	
	/**
	 * 
	 * 设置单个请求的超时时间，单位是秒 
	 *    
	 * @param seconds
	 */
	public static void setHttpTimeout(int seconds)
	{
		HttpClient.setTimeout(seconds);
	}
	
	/**
	 * 
	 * 设置HTTP客户端的并发数量  
	 *    
	 * @param maxTotal 最大并发数（默认200）
	 */
	public static void setHttpConcurrency(int maxTotal, int maxPerRoute)
	{
		//HttpClient.setConcurrency(maxTotal, maxPerRoute);
	}
	
	/**
	 * 
	 * 停止所有的同步和异步HTTP请求  
	 *    
	 * @throws IOException
	 */
	public static void shutdown()
	{
		HttpClient.shutdown();
	}
	
	/**
	 * 
	 * 创建一个UIAC客户端访问类  
	 *    
	 * @param serviceUrl
	 * @return
	 */
	public static UiacClient Create(String serviceUrl, String appId, String version)
	{
		return new UiacClient(serviceUrl, appId, version);
	}
	
	private String svcUrl;
	private String appId;
	private String version;
	private IAuthorizationHandler auth;
	private IAccountHandler account;
	private UiacClient(String svcUrl, String appId, String version)
	{
		if(!svcUrl.endsWith("/")){
			svcUrl += "/";
		}
		
		this.svcUrl = svcUrl;
		this.appId = appId;
		this.version = version;
		
		log.info(String.format("Create UIAC Client proxy: svcUrl=%s, appId=%s, version=%s", 
					this.svcUrl, this.appId, this.version));
		
		auth = new AuthorizationHandlerImpl(this);
		account = new AccountHandlerImpl(this);
	}
	
	/**
	 * 
	 * 获取UIAC的自定义头信息  
	 *    
	 * @param token
	 * @return
	 */
	public Map<String, String> getHeader(String token, Map<String, String> customHeaders)
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put(CONST.CLIENT_VERSION, version);
		map.put(CONST.APPID, appId);
		map.put(CONST.ACCESS_TOKEN, token);
		
		if (customHeaders != null)
		{
			map.putAll(customHeaders);
		}
		
		return map;
	}
	
	/**
	 * 
	 * 根据请求的子路径生成最终的访问链接  
	 *    
	 * @param uri
	 * @return
	 */
	public String formatUrl(String uri)
	{
		return formatUrl(uri, null);
	}
	
	/**
	 * 
	 * 根据请求的子路径生成最终的访问链接，同时支持路由URL替换，如：
	 * uri=user/{uid}，routeName=uid, routeVal=3，替换后路径为:user/3   
	 *    
	 * @param uri
	 * @param routeName
	 * @param routeVal
	 * @return
	 */
	public String formatUrl(String uri, String routeName, String routeVal)
	{
		Map<String, String> routeMap = new HashMap<String, String>();
		routeMap.put(routeName, routeVal);
		return formatUrl(uri, routeMap);
	}
	
	/**
	 * 
	 * 根据请求的子路径生成最终的访问链接,支持多个路由URL替换，如：
	 *   blog/{uid}/{page}, 假设map值为:{uid:1001, page:1}，最终替换结果为：blog/1001/1
	 *    
	 * @param uri
	 * @param routeMap
	 * @return
	 */
	public String formatUrl(String uri, Map<String, String> routeMap)
	{
		if(uri.startsWith("/")){
			uri = uri.substring(1);
		}
		String url = String.format("%s%s", this.svcUrl, uri);
		
		if(routeMap != null){
			for(Map.Entry<String, String> kv : routeMap.entrySet()){
				String name = kv.getKey();
				Matcher matcher = Pattern.compile("\\{" + name + "\\}").matcher(url);
				int count = 0;
				while(matcher.find()) {
					count++;
				}
				if (count == 0) {
					log.warn(String.format("Can't find route parameter name \"%s\"", name));
					continue;
				}
				try
				{
					url = url.replaceAll("\\{" + name + "\\}", URLEncoder.encode(kv.getValue(), "UTF-8"));
				}
				catch (UnsupportedEncodingException e)
				{
					log.error(String.format("fail to encode url, url: %s, value: %s", uri, kv.getValue()));
					url = null;
				}
			}
		}
		
		return url;
	}
	
	/**
	 * 
	 * 获取认证接口对象  
	 *    
	 * @return
	 */
	public IAuthorizationHandler authorization()
	{
		return auth;
	}
	
	/**
	 * 
	 * 获取账号基本管理接口对象  
	 *    
	 * @return
	 */
	public IAccountHandler account()
	{
		return account;
	}
}
