package com.xrk.uiac.sdk.handler;

import java.util.List;
import java.util.Map;

import com.xrk.uiac.sdk.entity.UserAuthorizationEntity;
import com.xrk.uiac.sdk.exception.UiacException;

/**
 * 
 * IAuthorizationHandler: IAuthorizationHandler.java.
 *
 * <br>==========================
 * <br> 公司：广州向日葵信息科技有限公司
 * <br> 开发：shunchiguo<shunchiguo@xiangrikui.com>
 * <br> 版本：1.0
 * <br> 创建时间：2015年7月15日
 * <br> JDK版本：1.7
 * <br>==========================
 */
public interface IAuthorizationHandler
{
	/**
	 * 
	 * 登录认证服务，并获取到登录信息  
	 *    
	 * @param account		登录的帐户
	 * @param password	登录的密码
	 * @return
	 */
	public	UserAuthorizationEntity login(String account, String password) throws UiacException;
	
	public	UserAuthorizationEntity login(String account, String password, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 登录认证服务，并指定过期时间  
	 *    
	 * @param account		登录的帐户
	 * @param password	登录密码
	 * @param expireTime 过期时间，默认为分钟
	 * @return
	 * @throws UiacException
	 */
	public UserAuthorizationEntity login(String account, String password, long expireTime) throws UiacException;
	
	public UserAuthorizationEntity login(String account, String password, long expireTime, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 使用UID登录  
	 *    
	 * @param uid
	 * @param password
	 * @return
	 * @throws UiacException
	 */
	public UserAuthorizationEntity loginUid(String uid, String password) throws UiacException;
	
	public UserAuthorizationEntity loginUid(String uid, String password, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 使用UID登录，并指定会话过期时间  
	 *    
	 * @param uid
	 * @param password
	 * @param expireTime
	 * @return
	 * @throws UiacException
	 */
	public UserAuthorizationEntity loginUid(String uid, String password, long expireTime) throws UiacException;
	
	public UserAuthorizationEntity loginUid(String uid, String password, long expireTime, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 使用验证码登录  
	 *    
	 * @param mobile
	 * @param captcha
	 * @return
	 * @throws UiacException
	 */
	public UserAuthorizationEntity loginCaptcha(String mobile, String captcha) throws UiacException;
	
	public UserAuthorizationEntity loginCaptcha(String mobile, String captcha, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 使用验证码登录，并指定过期时间  
	 *    
	 * @param mobile
	 * @param captcha
	 * @param expireTime
	 * @return
	 * @throws UiacException
	 */
	public UserAuthorizationEntity loginCaptcha(String mobile, String captcha, long expireTime) throws UiacException;
	
	public UserAuthorizationEntity loginCaptcha(String mobile, String captcha, long expireTime, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 使用绑定的子帐号登录  
	 *    
	 * @param subaccount 子账号ID
	 * @param subAppId 子账号对应的应用ID
	 * @param expireTime 过期时间
	 * @param headers
	 * @return
	 * @throws UiacException
	 */
	public UserAuthorizationEntity loginSubAccount(String subAccount, String subAppId, long expireTime, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 更新Toekn  
	 *    
	 * @param uid
	 * @param refreshToken
	 * @return
	 * @throws UiacException
	 */
	public UserAuthorizationEntity updateToken(long uid, String token, String refreshToken) throws UiacException;
	
	public UserAuthorizationEntity updateToken(long uid, String token, String refreshToken, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 查询用户的已登录Token列表
	 *    
	 * @param uid
	 * @param token
	 * @return
	 * @throws UiacException
	 */
	public List<UserAuthorizationEntity> query(long uid, String token) throws UiacException;
	
	public List<UserAuthorizationEntity> query(long uid, String token, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 验证用户的Token信息  
	 *    
	 * @param uid
	 * @param token
	 * @return
	 * @throws UiacException
	 */
	public UserAuthorizationEntity verify(String token) throws UiacException;
	
	public UserAuthorizationEntity verify(String token, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 注销登录  
	 *    
	 * @param token
	 * @return
	 * @throws UiacException
	 */
	public boolean logout(String token) throws UiacException;
	
	public boolean logout(String token, Map<String, String> headers) throws UiacException;
}
