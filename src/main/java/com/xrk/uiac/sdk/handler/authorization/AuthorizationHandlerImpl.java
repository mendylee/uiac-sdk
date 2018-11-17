package com.xrk.uiac.sdk.handler.authorization;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.common.Codec;
import com.xrk.uiac.sdk.entity.SimpleResponseEntity;
import com.xrk.uiac.sdk.entity.UserAuthorizationEntity;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.handler.IAuthorizationHandler;
import com.xrk.uiac.sdk.service.HttpClient;

/**
 * 
 * AuthorizationHandlerImpl: AuthorizationHandlerImpl.java.
 *
 * <br>==========================
 * <br> 公司：广州向日葵信息科技有限公司
 * <br> 开发：shunchiguo<shunchiguo@xiangrikui.com>
 * <br> 版本：1.0
 * <br> 创建时间：2015年7月15日
 * <br> JDK版本：1.7
 * <br>==========================
 */
public class AuthorizationHandlerImpl implements IAuthorizationHandler
{
	private static Logger log = Logger.getLogger(AuthorizationHandlerImpl.class);
	
	private UiacClient uiacClient;
	private String baseUri = "AccessToken";
	public AuthorizationHandlerImpl(UiacClient client) 
	{
	    this.uiacClient = client;
    }

	
	
	@Override
    public UserAuthorizationEntity login(String account, String password) throws UiacException
    {
	    return login(account, password, 0, null);
    }
	
	@Override
	public UserAuthorizationEntity login(String account, String password, Map<String, String> headers) throws UiacException
	{
		return login(account, password, 0, headers);
	}
	
	@Override
    public UserAuthorizationEntity login(String account, String password, long expireTime) throws UiacException
    {
		return login(account, password, expireTime, null);
    }
	
	@Override
	public UserAuthorizationEntity login(String account, String password, long expireTime, Map<String, String> headers) throws UiacException
	{
		long timestamp = new Date().getTime();	    
	    try {
	        password = Codec.hexMD5(String.format("%s%s", Codec.hexMD5(Codec.hexMD5(password)+account), timestamp));
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
	        log.error(String.format("encoder password failed! msg:%s", e.getMessage()), e);
	        throw new UiacException(500, "-1", e.getMessage());
        }
	    
	    String url = uiacClient.formatUrl(baseUri);
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("account", account);
	    params.put("password", password);
	    params.put("timestamp", String.valueOf(timestamp));
	    params.put("scope", "");
	    params.put("expireTime", String.valueOf(expireTime));
	    
	    log.info(String.format("login call: account=%s, password=%s, expireTime=%s", 
				account, password, expireTime));
	    
	    return HttpClient.post(UserAuthorizationEntity.class, url, uiacClient.getHeader("", headers), params);
	}
	
	@Override
    public UserAuthorizationEntity loginUid(String uid, String password) throws UiacException
    {
		return loginUid(uid, password, 0, null);
    }
	
	@Override
	public UserAuthorizationEntity loginUid(String uid, String password, Map<String, String> headers) throws UiacException
	{
		return loginUid(uid, password, 0, headers);
	}
	
	@Override
    public UserAuthorizationEntity loginUid(String uid, String password, long expireTime) throws UiacException
    {
		return loginUid(uid, password, expireTime, null);
    }
	
	@Override
	public UserAuthorizationEntity loginUid(String uid, String password, long expireTime, Map<String, String> headers) throws UiacException
	{
		long timestamp = new Date().getTime();	    
	    try {
	    	password = Codec.hexMD5(password);
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
	        log.error(String.format("encoder password failed! msg:%s", e.getMessage()), e);
	        throw new UiacException(500, "-1", e.getMessage());
        }
	    
	    String uri = String.format("%s/{uid}", baseUri);
		String url = uiacClient.formatUrl(uri, "uid", String.valueOf(uid));
		
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("password", password);
	    params.put("timestamp", String.valueOf(timestamp));
	    params.put("scope", "");
	    params.put("expireTime", String.valueOf(expireTime));
	    
	    log.info(String.format("loginUid call: uid=%s, password=%s", 
	    		uid, password));
	    
	    return HttpClient.post(UserAuthorizationEntity.class, url, uiacClient.getHeader("", headers), params);
	}
	

	
	@Override
    public UserAuthorizationEntity loginCaptcha(String mobile, String captcha) throws UiacException
    {
		return loginCaptcha(mobile, captcha, 0, null);
    }
	
	@Override
	public UserAuthorizationEntity loginCaptcha(String mobile, String captcha, Map<String, String> headers) throws UiacException
	{
		return loginCaptcha(mobile, captcha, 0, headers);
	}
	
	@Override
    public UserAuthorizationEntity loginCaptcha(String mobile, String captcha, long expireTime) throws UiacException
    {
		return loginCaptcha(mobile, captcha, expireTime, null);
    }
	
	@Override
	public UserAuthorizationEntity loginCaptcha(String mobile, String captcha, long expireTime, Map<String, String> headers) throws UiacException
	{
		String url = uiacClient.formatUrl(baseUri+"/captcha");
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("mobile", mobile);
	    params.put("captcha", captcha);
	    params.put("scope", "");
	    params.put("expireTime", String.valueOf(expireTime));
	    
	    log.info(String.format("loginCaptcha call: mobile=%s, captcha=%s", 
	    		mobile, captcha));
	    
	    return HttpClient.post(UserAuthorizationEntity.class, url, uiacClient.getHeader("", headers), params);
	}
	
	@Override
    public UserAuthorizationEntity loginSubAccount(String subaccount, String subAppId,
                                                   long expireTime, Map<String, String> headers)
                                                                                                throws UiacException
    {
		String url = uiacClient.formatUrl(baseUri+"/subaccount");
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("subaccount", subaccount);
	    params.put("subAppId", subAppId);
	    params.put("scope", "");
	    params.put("expireTime", String.valueOf(expireTime));
	    
	    log.info(String.format("loginSubAccount call: subaccount=%s, subAppId=%s", 
	    		subaccount, subAppId));
	    
	    return HttpClient.post(UserAuthorizationEntity.class, url, uiacClient.getHeader("", headers), params);
    }

	@Override
    public UserAuthorizationEntity updateToken(long uid, String token, String refreshToken) throws UiacException
    {
		return updateToken(uid, token, refreshToken, null);
    }
	
	@Override
	public UserAuthorizationEntity updateToken(long uid, String token, String refreshToken, Map<String, String> headers) throws UiacException
	{
		String url = uiacClient.formatUrl(baseUri);
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("uid", String.valueOf(uid));
	    params.put("refreshToken", refreshToken);
	    
	    log.info(String.format("updateToken call: uid=%s, token=%s, refreshToken=%s", 
	    		uid, token, refreshToken));
	    
	    return HttpClient.put(UserAuthorizationEntity.class, url, uiacClient.getHeader(token, headers), params);
	}
	
	

    @Override
    public List<UserAuthorizationEntity> query(long uid, String token) throws UiacException
    {
		return query(uid, token, null);
    }
    
    @Override
    public List<UserAuthorizationEntity> query(long uid, String token, Map<String, String> headers) throws UiacException
    {
    	String uri = String.format("%s/{uid}", baseUri);
		String url = uiacClient.formatUrl(uri, "uid", String.valueOf(uid));
		
		Type listType = new TypeToken<List<UserAuthorizationEntity>>() {}.getType();
		String json = HttpClient.get(url, uiacClient.getHeader(token, headers), null);
		return new Gson().fromJson(json, listType);
	    //return (List<UserAuthorizationEntity>)HttpClient.get(List.class, url, uiacClient.getHeader(token));
    }

	@Override
    public UserAuthorizationEntity verify(String token) throws UiacException
    {
		return verify(token, null);
    }
	
	@Override
	public UserAuthorizationEntity verify(String token, Map<String, String> headers) throws UiacException
	{
		String url = uiacClient.formatUrl(baseUri);	    
	    return HttpClient.get(UserAuthorizationEntity.class, url, uiacClient.getHeader(token, headers), null);
	}
	
	

    @Override
    public boolean logout(String token) throws UiacException
    {
		return logout(token, null);
    }
    
    @Override
    public boolean logout(String token, Map<String, String> headers) throws UiacException
    {
    	String url = uiacClient.formatUrl(baseUri);	    
		
		log.info(String.format("logout call: token=%s", 
	    		token));
		
		@SuppressWarnings("unchecked")
        SimpleResponseEntity<Boolean> resp = (SimpleResponseEntity<Boolean>)HttpClient.delete(SimpleResponseEntity.class, url, uiacClient.getHeader(token, headers), null);
		return resp.getResult();
    }
}
