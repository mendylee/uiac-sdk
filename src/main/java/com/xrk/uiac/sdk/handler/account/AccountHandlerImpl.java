package com.xrk.uiac.sdk.handler.account;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xrk.uiac.sdk.UiacClient;
import com.xrk.uiac.sdk.common.ParameterUtils;
import com.xrk.uiac.sdk.entity.CheckParameterResponse;
import com.xrk.uiac.sdk.entity.CreateUserResponse;
import com.xrk.uiac.sdk.entity.GetBindingStatusResponse;
import com.xrk.uiac.sdk.entity.GetUserInfoResponse;
import com.xrk.uiac.sdk.entity.SendCaptchaResponse;
import com.xrk.uiac.sdk.entity.SimpleResponseEntity;
import com.xrk.uiac.sdk.entity.SubAccountInfoResponse;
import com.xrk.uiac.sdk.entity.UserInfo;
import com.xrk.uiac.sdk.exception.UiacException;
import com.xrk.uiac.sdk.handler.IAccountHandler;
import com.xrk.uiac.sdk.service.HttpClient;

@SuppressWarnings("unchecked")
public class AccountHandlerImpl implements IAccountHandler
{
	private static Logger log = Logger.getLogger(AccountHandlerImpl.class);
	
	private UiacClient uiacClient;
	private String baseUri = "Account";
	
	private Gson gson = new Gson();
	
	public AccountHandlerImpl(UiacClient client)
	{
	    this.uiacClient = client;
    }
	
	
	
	@Override
    public CheckParameterResponse checkParameter(String userCode) throws UiacException
    {
		return checkParameter(userCode, null);
    }
	
	@Override
	public CheckParameterResponse checkParameter(String userCode, Map<String, String> headers) throws UiacException
	{
		String url = uiacClient.formatUrl(baseUri + "/parameter");
		Map<String, String> params = new HashMap<String, String>();
		params.put("mobile", userCode);
	    return HttpClient.get(CheckParameterResponse.class, url, uiacClient.getHeader("", headers), params);
	}

	
	
	@Override
    public CreateUserResponse createUser(String userCode, String password, String userInfo, String extendInfo) throws UiacException
    {	    
	    return createUser(userCode, password, userInfo, extendInfo, false, null);
    }
	
	@Override
    public CreateUserResponse createUser(String userCode, String password, String userInfo, String extendInfo, boolean unverified) throws UiacException
    {
		return createUser(userCode, password, userInfo, extendInfo, unverified, null);
    }
	
	@Override
	public CreateUserResponse createUser(String userCode, String password, String userInfo, String extendInfo, boolean unverified, Map<String, String> headers) throws UiacException
	{
		password = ParameterUtils.encodeAccountPassword(password, log);
		
		String url = uiacClient.formatUrl(baseUri + "/user");
		Map<String, String> params = new HashMap<String, String>();
		params.put("mobile", userCode);
		params.put("password", password);
		params.put("userInfo", userInfo);
		params.put("extendInfo", extendInfo);
		params.put("unverified", String.valueOf(unverified));
		
		log.info(String.format("createUser call:mobile=%s, password=%s, userInfo=%s, extendInfo=%s, unverified=%s", 
				userCode, password, userInfo, extendInfo, unverified));
	    return HttpClient.post(CreateUserResponse.class, url, uiacClient.getHeader("", headers), params);
	}

	@Override
    public CreateUserResponse createUser(String userCode, String password, UserInfo userInfo, Map<String, String> extendInfo) throws UiacException
    {
	    return createUser(userCode, password, userInfo, extendInfo, false, null);
    }

	@Override
    public CreateUserResponse createUser(String userCode, String password, UserInfo userInfo, Map<String, String> extendInfo, boolean unverified) throws UiacException
    {
		return createUser(userCode, password, userInfo, extendInfo, unverified, null);
    }
	
	@Override
	public CreateUserResponse createUser(String userCode, String password, UserInfo userInfo, Map<String, String> extendInfo, boolean unverified, Map<String, String> headers) throws UiacException
	{
		String uiStr = userInfo == null ? "" : gson.toJson(userInfo);
		String eiStr = extendInfo == null ? "" : gson.toJson(extendInfo);
		
	    return createUser(userCode, password, uiStr, eiStr, unverified, headers);
	}
	
	

	@Override
    public GetUserInfoResponse getUserInfo(long uid, String token) throws UiacException
    {
		return getUserInfo(uid, token, null);
    }
	
	@Override
	public GetUserInfoResponse getUserInfo(long uid, String token, Map<String, String> headers) throws UiacException
	{
		String url = uiacClient.formatUrl(String.format("%s/user/{uid}", baseUri), "uid", String.valueOf(uid));
	    return HttpClient.get(GetUserInfoResponse.class, url, uiacClient.getHeader(token, headers), null);
	}
	
	@Override
	public GetUserInfoResponse getUserInfo(String account, String token) throws UiacException
	{
		return getUserInfo(account, token, null);
	}

	@Override
	public GetUserInfoResponse getUserInfo(String account, String token, Map<String, String> headers) throws UiacException
	{
		String url = uiacClient.formatUrl(baseUri + "/user");
		Map<String, String> params = new HashMap<String, String>();
		params.put("account", account);
		return HttpClient.get(GetUserInfoResponse.class, url, uiacClient.getHeader(token, headers), params);
	}
	
	
	
    @Override
    public boolean updateUserInfo(long uid, String token, String userInfo, String extendInfo) throws UiacException
    {
		return updateUserInfo(uid, token, userInfo, extendInfo, null);
    }
    
    @Override
    public boolean updateUserInfo(long uid, String token, String userInfo, String extendInfo, Map<String, String> headers) throws UiacException
    {
    	String url = uiacClient.formatUrl(String.format("%s/user/{uid}", baseUri), "uid", String.valueOf(uid));
		Map<String, String> params = new HashMap<String, String>();
		params.put("userInfo", userInfo);
		params.put("extendInfo", extendInfo);
		log.info(String.format("updateUserInfo call:uid=%s, token=%s, userInfo=%s, extendInfo=%s", 
				uid, token, userInfo, extendInfo));
		
		SimpleResponseEntity<Boolean> response = (SimpleResponseEntity<Boolean>) HttpClient.put(SimpleResponseEntity.class, url, uiacClient.getHeader(token, headers), params);
	    return response.getResult();
    }

	@Override
    public boolean updateUserInfo(long uid, String token, UserInfo userInfo, Map<String, String> extendInfo) throws UiacException
    {
		return updateUserInfo(uid, token, userInfo, extendInfo, null);
    }
	
	@Override
	public boolean updateUserInfo(long uid, String token, UserInfo userInfo, Map<String, String> extendInfo, Map<String, String> headers) throws UiacException
	{
		String uiStr = userInfo == null ? "" : gson.toJson(userInfo);
		String eiStr = extendInfo == null ? "" : gson.toJson(extendInfo);
		
	    return updateUserInfo(uid, token, uiStr, eiStr, headers);
	}
	
	
	
	@Override
	public boolean rollbackUser(String account, String password) throws UiacException
	{
		return rollbackUser(account, password, null);
	}
	
	@Override
	public boolean rollbackUser(String account, String password, Map<String, String> headers) throws UiacException
	{
		password = ParameterUtils.encodeAccountPassword(password, log);
		
		String url = uiacClient.formatUrl(baseUri + "/user");
		Map<String, String> params = new HashMap<String, String>();
		params.put("userCode", account);
		params.put("password", password);
		log.info(String.format("rollbackUser call:account=%s, password=%s", account, password));
		
		SimpleResponseEntity<Boolean> response = HttpClient.delete(SimpleResponseEntity.class, url, uiacClient.getHeader("", headers), params);
		return response.getResult();
	}
	
	

    @Override
    public boolean updatePassword(long uid, String token, String oldPwd, String password) throws UiacException
    {
    	return updatePassword(uid, token, oldPwd, password, false, null);
    }
    
    @Override
    public boolean updatePassword(long uid, String token, String oldPwd, String password, boolean unverified) throws UiacException
    {
    	return updatePassword(uid, token, oldPwd, password, unverified, null);
    }
    
    @Override
    public boolean updatePassword(long uid, String token, String oldPwd, String password, boolean unverified, Map<String, String> headers) throws UiacException
    {
    	oldPwd = ParameterUtils.encodeAccountPassword(oldPwd, log);
		password = ParameterUtils.encodeAccountPassword(password, log);
		
		String url = uiacClient.formatUrl(String.format("%s/password/{uid}", baseUri), "uid", String.valueOf(uid));
		Map<String, String> params = new HashMap<String, String>();
		params.put("oldPwd", oldPwd);
		params.put("password", password);
		params.put("unverified", String.valueOf(unverified));
		
		log.info(String.format("updatePassword call:uid=%s, token=%s, oldPwd=%s, password=%s, unverified=%s", 
				uid, token, oldPwd, password, unverified));
		
		SimpleResponseEntity<Boolean> response = (SimpleResponseEntity<Boolean>) HttpClient.put(SimpleResponseEntity.class, url, uiacClient.getHeader(token, headers), params);
	    return response.getResult();
    }
    
    
    
    @Override
    public boolean resetPassword(long uid, String password) throws UiacException
    {
    	return resetPassword(uid, password, false, null);
    }

	@Override
    public boolean resetPassword(long uid, String password, boolean unverified) throws UiacException
    {
		return resetPassword(uid, password, unverified, null);
    }

	@Override
	public boolean resetPassword(long uid, String password, boolean unverified, Map<String, String> headers) throws UiacException
	{
		password = ParameterUtils.encodeAccountPassword(password, log);
    	
		String url = uiacClient.formatUrl(String.format("%s/password/{uid}", baseUri), "uid", String.valueOf(uid));
		Map<String, String> params = new HashMap<String, String>();
		params.put("password", password);
		params.put("unverified", String.valueOf(unverified));
		
		log.info(String.format("resetPassword call:uid=%s, password=%s, unverified=%s", 
				uid, password, unverified));
		
		SimpleResponseEntity<Boolean> response = (SimpleResponseEntity<Boolean>) HttpClient.post(SimpleResponseEntity.class, url, uiacClient.getHeader("", headers), params);
	    return response.getResult();
	}
	
	

    @Override
    public boolean disableUser(long uid, long targetId, String token) throws UiacException
    {
    	return disableUser(uid, targetId, token, null);
    }
    
    @Override
    public boolean disableUser(long uid, long targetId, String token, Map<String, String> headers) throws UiacException
    {
    	String url = uiacClient.formatUrl(String.format("%s/status/{uid}", baseUri), "uid", String.valueOf(uid));
		Map<String, String> params = new HashMap<String, String>();
		params.put("targetId", String.valueOf(targetId));
		
		log.info(String.format("disableUser call:uid=%s, targetId=%s, token=%s", 
				uid, targetId, token));
		
		SimpleResponseEntity<Boolean> response = (SimpleResponseEntity<Boolean>) HttpClient.put(SimpleResponseEntity.class, url, uiacClient.getHeader(token, headers), params);
	    return response.getResult();
    }
    

    
    @Override
    public boolean enableUser(long uid, long targetId, String token) throws UiacException
    {
    	return enableUser(uid, targetId, token, null);
    }
	
    @Override
    public boolean enableUser(long uid, long targetId, String token, Map<String, String> headers) throws UiacException
    {
    	String url = uiacClient.formatUrl(String.format("%s/status/{uid}", baseUri), "uid", String.valueOf(uid));
		Map<String, String> params = new HashMap<String, String>();
		params.put("targetId", String.valueOf(targetId));
		
		log.info(String.format("enableUser call:uid=%s, targetId=%s, token=%s", 
				uid, targetId, token));
		
		SimpleResponseEntity<Boolean> response = (SimpleResponseEntity<Boolean>) HttpClient.delete(SimpleResponseEntity.class, url, uiacClient.getHeader(token, headers), params);
	    return response.getResult();
    }

    
    
	@Override
    public boolean getUserStatus(long uid, long targetId, String token) throws UiacException
    {
		return getUserStatus(uid, targetId, token, null);
    }
	
	@Override
	public boolean getUserStatus(long uid, long targetId, String token, Map<String, String> headers) throws UiacException
	{
		String url = uiacClient.formatUrl(String.format("%s/status/{uid}", baseUri), "uid", String.valueOf(uid));
		Map<String, String> params = new HashMap<String, String>();
		params.put("targetId", String.valueOf(targetId));
		SimpleResponseEntity<Boolean> response = (SimpleResponseEntity<Boolean>) HttpClient.get(SimpleResponseEntity.class, url, uiacClient.getHeader(token, headers), params);
	    return response.getResult();
	}
	
	

	@Override
    public GetBindingStatusResponse getBindingStatus(long uid, String token) throws UiacException
    {
		return getBindingStatus(uid, token, null);
    }
	
	@Override
	public GetBindingStatusResponse getBindingStatus(long uid, String token, Map<String, String> headers) throws UiacException
	{
		String url = uiacClient.formatUrl(String.format("%s/bind/{uid}", baseUri), "uid", String.valueOf(uid));
	    return HttpClient.get(GetBindingStatusResponse.class, url, uiacClient.getHeader(token, headers), null);
	}
	
	

	@Override
    public boolean bindMobile(long uid, String mobile, String token) throws UiacException
    {
		return bindMobile(uid, mobile, token, false, null);
    }
	
	@Override
    public boolean bindMobile(long uid, String mobile, String token, boolean unverified) throws UiacException
    {
		return bindMobile(uid, mobile, token, unverified, null);
    }
	
	@Override
	public boolean bindMobile(long uid, String mobile, String token, boolean unverified, Map<String, String> headers) throws UiacException
	{
		String url = uiacClient.formatUrl(String.format("%s/bind/{uid}", baseUri), "uid", String.valueOf(uid));
		Map<String, String> params = new HashMap<String, String>();
		params.put("mobile", mobile);
		params.put("unverified", String.valueOf(unverified));
		
		log.info(String.format("bindMobile call:uid=%s, mobile=%s, token=%s, unverified=%s", 
				uid, mobile, token, unverified));
		
		SimpleResponseEntity<Boolean> response = (SimpleResponseEntity<Boolean>) HttpClient.put(SimpleResponseEntity.class, url, uiacClient.getHeader(token, headers), params);
	    return response.getResult();
	}
	
	
	
	@Override
    public boolean unBindMobile(long uid, String token) throws UiacException
    {
		return unBindMobile(uid, token, false, null);
    }
	
	@Override
    public boolean unBindMobile(long uid, String token, boolean unverified) throws UiacException
    {
		return unBindMobile(uid, token, unverified, null);
    }
	
	@Override
	public boolean unBindMobile(long uid, String token, boolean unverified, Map<String, String> headers) throws UiacException
	{
		String url = uiacClient.formatUrl(String.format("%s/bind/{uid}", baseUri), "uid", String.valueOf(uid));
		Map<String, String> params = new HashMap<String, String>();
		params.put("unverified", String.valueOf(unverified));
		
		log.info(String.format("unBindMobile call:uid=%s,token=%s, unverified=%s", 
				uid, token, unverified));
		
		SimpleResponseEntity<Boolean> response = (SimpleResponseEntity<Boolean>) HttpClient.delete(SimpleResponseEntity.class, url, uiacClient.getHeader(token, headers), params);
	    return response.getResult();
	}
	
	

	@Override
    public boolean bindSubAccount(long uid, int subAppId, String subAccount, String token) throws UiacException
    {
		return bindSubAccount(uid, subAppId, subAccount, token, null);
    }
	
	@Override
	public boolean bindSubAccount(long uid, int subAppId, String subAccount, String token, Map<String, String> headers) throws UiacException
	{
		String url = uiacClient.formatUrl(String.format("%s/subAccount/{uid}", baseUri), "uid", String.valueOf(uid));
		Map<String, String> params = new HashMap<String, String>();
		params.put("tempId", subAccount);
		params.put("subAppId", String.valueOf(subAppId));
		
		log.info(String.format("bindSubAccount call:uid=%s,token=%s, subAccount=%s, subAppId=%s", 
				uid, token, subAccount, subAppId));
		
		SimpleResponseEntity<Boolean> response = (SimpleResponseEntity<Boolean>) HttpClient.post(SimpleResponseEntity.class, url, uiacClient.getHeader(token, headers), params);
		return response.getResult();
	}
	
	

	@Override
    public boolean unBindSubAccount(long uid, int subAppId, String subAccount, String token) throws UiacException
    {
		return unBindSubAccount(uid, subAppId, subAccount, token, null);
    }
	
	@Override
	public boolean unBindSubAccount(long uid, int subAppId, String subAccount, String token, Map<String, String> headers) throws UiacException
	{
		String url = uiacClient.formatUrl(String.format("%s/subAccount/{uid}", baseUri), "uid", String.valueOf(uid));
		Map<String, String> params = new HashMap<String, String>();
		params.put("subAppId", String.valueOf(subAppId));
		params.put("subAccount", subAccount);
		
		log.info(String.format("unBindSubAccount call:uid=%s,token=%s,  subAppId=%s, subAccount=%s", 
				uid, token,  subAppId, subAccount));
		
		SimpleResponseEntity<Boolean> response = (SimpleResponseEntity<Boolean>) HttpClient.delete(SimpleResponseEntity.class, url, uiacClient.getHeader(token, headers), params);
		return response.getResult();
	}
	
	@Override
    public List<SubAccountInfoResponse> getSubAccountList(long uid, String token) throws UiacException
    {
		return getSubAccountList(uid, token, null);
    }
	
	@Override
	public List<SubAccountInfoResponse> getSubAccountList(long uid, String token, Map<String, String> headers) throws UiacException
	{
		String url = uiacClient.formatUrl(String.format("%s/subAccount/{uid}", baseUri), "uid", String.valueOf(uid));
		String responseStr = HttpClient.get(url, uiacClient.getHeader(token, headers), null);
		
		List<SubAccountInfoResponse> response;
		
		try
		{
			Type type = new TypeToken<List<SubAccountInfoResponse>>() {}.getType();
			response = gson.fromJson(responseStr, type);
		}
		catch (Exception e)
		{
			log.error(String.format("fail to get list from json! msg:%s", e.getMessage()), e);
	        throw new UiacException(500, "-1", e.getMessage()); 
		}
		
	    return response;
	}
	
	@Override
	public SubAccountInfoResponse getSubAccount(int subAppId, String subAccount, Map<String, String> headers) throws UiacException
	{
		String url = uiacClient.formatUrl(String.format("%s/subAccount", baseUri));
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("subAccount", subAccount);
		params.put("subAppId", String.valueOf(subAppId));		
		
		return HttpClient.get(SubAccountInfoResponse.class, url, uiacClient.getHeader("", headers), params);		
	}

	@Override
    public SendCaptchaResponse sendCaptcha(String mobile, int checkType) throws UiacException
    {
		return sendCaptcha(mobile, checkType, null);
    }
	
	@Override
	public SendCaptchaResponse sendCaptcha(String mobile, int checkType, Map<String, String> headers) throws UiacException
	{
		String url = uiacClient.formatUrl(baseUri + "/captcha");
		Map<String, String> params = new HashMap<String, String>();
		params.put("mobile", mobile);
		params.put("checkType", String.valueOf(checkType));
		
		log.info(String.format("sendCaptcha mobile=%s,checkType=%s", 
				mobile,  checkType));
		
	    return HttpClient.put(SendCaptchaResponse.class, url, uiacClient.getHeader("", headers), params);
	}
	
	

	@Override
    public boolean validateCaptcha(String mobile, String captcha, int checkType) throws UiacException
    {
		return validateCaptcha(mobile, captcha, checkType, null);
    }
	
	@Override
	public boolean validateCaptcha(String mobile, String captcha, int checkType, Map<String, String> headers) throws UiacException
	{
		String url = uiacClient.formatUrl(baseUri + "/captcha");
		Map<String, String> params = new HashMap<String, String>();
		params.put("mobile", mobile);
		params.put("captcha", captcha);
		params.put("checkType", String.valueOf(checkType));
		
		log.info(String.format("sendCaptcha mobile=%s, captcha=%s, checkType=%s", 
				mobile, captcha, checkType));
		
		SimpleResponseEntity<Boolean> response = HttpClient.get(SimpleResponseEntity.class, url, uiacClient.getHeader("", headers), params);
		return response.getResult();
	}
	
	
	
	@Override
	public long getWenbaTempId() throws UiacException
	{
	    return getWenbaTempId(null);
	}
	
	@Override
	public long getWenbaTempId(Map<String, String> headers) throws UiacException
	{
		String url = uiacClient.formatUrl(baseUri + "/wenba/tempId");
	    
	    log.info("getWenbaTempId.....");
	    
	    String strResp = HttpClient.get(url, uiacClient.getHeader("", headers), null);
	    Type type = new TypeToken<SimpleResponseEntity<Long>>() {}.getType();
	    SimpleResponseEntity<Long> response = gson.fromJson(strResp, type);
	    
	    return response.getResult();
	}
}
