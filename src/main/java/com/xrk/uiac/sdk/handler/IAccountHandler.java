package com.xrk.uiac.sdk.handler;

import java.util.List;
import java.util.Map;

import com.xrk.uiac.sdk.entity.CheckParameterResponse;
import com.xrk.uiac.sdk.entity.CreateUserResponse;
import com.xrk.uiac.sdk.entity.GetBindingStatusResponse;
import com.xrk.uiac.sdk.entity.SubAccountInfoResponse;
import com.xrk.uiac.sdk.entity.GetUserInfoResponse;
import com.xrk.uiac.sdk.entity.SendCaptchaResponse;
import com.xrk.uiac.sdk.entity.UserInfo;
import com.xrk.uiac.sdk.exception.UiacException;

/**
 * 
 * 用户模块接口
 *
 * <br>==========================
 * <br> 公司：广州向日葵信息科技有限公司
 * <br> 开发：yexx<yexiaoxiao@xiangrikui.com>
 * <br> 版本：1.0
 * <br> 创建时间：2015年7月15日
 * <br> JDK版本：1.7
 * <br>==========================
 */
public interface IAccountHandler
{
	/**
	 * 
	 * 验证用户参数是否被占用，暂时只支持手机号
	 *    
	 * @param userCode			用户编码，暂时默认手机号
	 * @return
	 * @throws UiacException
	 */
	public CheckParameterResponse checkParameter(String userCode) throws UiacException;
	
	public CheckParameterResponse checkParameter(String userCode, Map<String, String> headers) throws UiacException;
	
	
	/**
	 * 
	 * 创建用户
	 *    
	 * @param userCode			用户编码，默认为手机号
	 * @param password			用户密码
	 * @param userInfo			用户基本信息，JSON串
	 * @param extendInfo		用户扩展信息，JSON串
	 * @return	
	 * @throws UiacException
	 */
	public CreateUserResponse createUser(String userCode, String password, String userInfo, String extendInfo) throws UiacException;
	
	public CreateUserResponse createUser(String userCode, String password, String userInfo, String extendInfo, boolean unverified) throws UiacException;
	
	public CreateUserResponse createUser(String userCode, String password, String userInfo, String extendInfo, boolean unverified, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 创建用户
	 *    
	 * @param userCode			用户编码，默认为手机号
	 * @param password			用户密码
	 * @param userInfo			用户信息，UserInfo类
	 * @param extendInfo		用户扩展信息，Map类型，键值对
	 * @return
	 * @throws UiacException
	 */
	public CreateUserResponse createUser(String userCode, String password, UserInfo userInfo, Map<String, String> extendInfo) throws UiacException;
	
	public CreateUserResponse createUser(String userCode, String password, UserInfo userInfo, Map<String, String> extendInfo, boolean unverified) throws UiacException;
	
	public CreateUserResponse createUser(String userCode, String password, UserInfo userInfo, Map<String, String> extendInfo, boolean unverified, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 通过uid获取用户基本信息
	 *    		
	 * @param uid			
	 * @param token			
	 * @return
	 * @throws UiacException
	 */
	public GetUserInfoResponse getUserInfo(long uid, String token) throws UiacException;
	
	public GetUserInfoResponse getUserInfo(long uid, String token, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 通过用户账号获取用户基本信息 
	 *    
	 * @param account
	 * @param token
	 * @return
	 * @throws UiacException
	 */
	public GetUserInfoResponse getUserInfo(String account, String token) throws UiacException;
	
	public GetUserInfoResponse getUserInfo(String account, String token, Map<String, String> headers) throws UiacException;
	
	
	/**
	 * 
	 * 更新用户基本信息  
	 *    
	 * @param uid			uid
	 * @param token			token
	 * @param userInfo		用户基本信息，JSON串
	 * @param extendInfo	用户扩展信息，JSON串
	 * @return
	 * @throws UiacException
	 */
	public boolean updateUserInfo(long uid, String token, String userInfo, String extendInfo) throws UiacException;
	
	public boolean updateUserInfo(long uid, String token, String userInfo, String extendInfo, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 更新用户基本信息 
	 *    
	 * @param uid			uid
	 * @param token			token
	 * @param userInfo		用户基本信息，UserInfo类
	 * @param extendInfo	用户扩展信息，Map类型，键值对
	 * @return
	 * @throws UiacException
	 */
	public boolean updateUserInfo(long uid, String token, UserInfo userInfo, Map<String, String> extendInfo) throws UiacException;
	
	public boolean updateUserInfo(long uid, String token, UserInfo userInfo, Map<String, String> extendInfo, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 输入账号和密码，回滚账号
	 * 将物理删除账号！！！
	 * 慎用此接口！！！ 
	 *    
	 * @param account
	 * @param password
	 * @return
	 * @throws UiacException
	 */
	public boolean rollbackUser(String account, String password) throws UiacException;
	
	public boolean rollbackUser(String account, String password, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 更新用户密码，用于修改密码的场景
	 *    
	 * @param uid			uid
	 * @param token			token
	 * @param oldPwd		旧密码
	 * @param password		新密码
	 * @return
	 * @throws UiacException
	 */
	public boolean updatePassword(long uid, String token, String oldPwd, String password) throws UiacException;
	
	public boolean updatePassword(long uid, String token, String oldPwd, String password, boolean unverified) throws UiacException;
	
	public boolean updatePassword(long uid, String token, String oldPwd, String password, boolean unverified, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 重置用户密码，一般用于忘记密码的场景
	 *    
	 * @param uid			uid
	 * @param password		新密码
	 * @return
	 * @throws UiacException
	 */
	public boolean resetPassword(long uid, String password) throws UiacException;
	
	public boolean resetPassword(long uid, String password, boolean unverified) throws UiacException;
	
	public boolean resetPassword(long uid, String password, boolean unverified, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 禁用账号，只有管理员账号才有权限  
	 *    
	 * @param uid			管理员账号uid
	 * @param targetId		目标uid
	 * @param token			token
	 * @return
	 * @throws UiacException
	 */
	public boolean disableUser(long uid, long targetId, String token) throws UiacException;
	
	public boolean disableUser(long uid, long targetId, String token, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 解禁账号，只有管理员账号才有权限  
	 *    
	 * @param uid			管理员账号uid
	 * @param targetId		目标uid
	 * @param token			token
	 * @return
	 * @throws UiacException
	 */
	public boolean enableUser(long uid, long targetId, String token) throws UiacException;
	
	public boolean enableUser(long uid, long targetId, String token, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 获取目标用户的禁用状态，只有管理员账号才有权限 
	 *    
	 * @param uid			管理员账号uid
	 * @param targetId		目标uid
	 * @param token			token
	 * @return
	 * @throws UiacException
	 */
	public boolean getUserStatus(long uid, long targetId, String token) throws UiacException;
	
	public boolean getUserStatus(long uid, long targetId, String token, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 获取手机、邮箱的绑定状态，暂时只支持手机绑定状态的返回
	 *    
	 * @param uid
	 * @param token
	 * @return
	 * @throws UiacException
	 */
	public GetBindingStatusResponse getBindingStatus(long uid, String token) throws UiacException;
	
	public GetBindingStatusResponse getBindingStatus(long uid, String token, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 绑定手机号
	 *    
	 * @param uid
	 * @param mobile
	 * @param token
	 * @return
	 * @throws UiacException
	 */
	public boolean bindMobile(long uid, String mobile, String token) throws UiacException;
	
	public boolean bindMobile(long uid, String mobile, String token, boolean unverified) throws UiacException;
	
	public boolean bindMobile(long uid, String mobile, String token, boolean unverified, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 手机号解绑 
	 *    
	 * @param uid
	 * @param token
	 * @return
	 * @throws UiacException
	 */
	public boolean unBindMobile(long uid, String token) throws UiacException;
	
	public boolean unBindMobile(long uid, String token, boolean unverified) throws UiacException;
	
	public boolean unBindMobile(long uid, String token, boolean unverified, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 绑定子账号 
	 *    
	 * @param uid			uid
	 * @param subAccount	子应用的账号名称
	 * @param subAppId		子应用id
	 * @param token			token
	 * @return
	 * @throws UiacException
	 */
	public boolean bindSubAccount(long uid, int subAppId, String subAccount, String token) throws UiacException;
	
	public boolean bindSubAccount(long uid, int subAppId, String subAccount, String token, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 解绑子账号
	 *    
	 * @param uid			uid
	 * @param subAppId		子应用id
	 * @param subAccount 子应用账号
	 * @param token
	 * @return
	 * @throws UiacException
	 */
	public boolean unBindSubAccount(long uid, int subAppId, String subAccount, String token) throws UiacException;
	
	public boolean unBindSubAccount(long uid, int subAppId, String subAccount, String token, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 获取当前用户绑定的子账号列表 
	 *    
	 * @param uid
	 * @param token
	 * @return
	 * @throws UiacException
	 */
	public List<SubAccountInfoResponse> getSubAccountList(long uid, String token) throws UiacException;
	
	public List<SubAccountInfoResponse> getSubAccountList(long uid, String token, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 根据子账号信息查询子账号绑定信息，如果查询不到则返回null
	 *    
	 * @param subAppId
	 * @param subAccount
	 * @param token
	 * @param headers
	 * @return
	 * @throws UiacException
	 */
	public SubAccountInfoResponse getSubAccount(int subAppId, String subAccount, Map<String, String> headers) throws UiacException;
	/**
	 * 
	 * 发送验证码 
	 *    
	 * @param mobile		手机号
	 * @param checkType		验证类型，1-注册，2-重置密码，3-绑定，4-登录
	 * @return
	 * @throws UiacException
	 */
	public SendCaptchaResponse sendCaptcha(String mobile, int checkType) throws UiacException;
	
	public SendCaptchaResponse sendCaptcha(String mobile, int checkType, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 校验验证码
	 *    
	 * @param mobile		手机号
	 * @param captcha		用户输入的验证码
	 * @param checkType		验证类型，1-注册，2-重置密码，3-绑定，4-登录
	 * @return
	 * @throws UiacException
	 */
	public boolean validateCaptcha(String mobile, String captcha, int checkType) throws UiacException;
	
	public boolean validateCaptcha(String mobile, String captcha, int checkType, Map<String, String> headers) throws UiacException;
	
	/**
	 * 
	 * 生成一个问吧的临时用户id 
	 *    
	 * @return
	 * @throws UiacException
	 */
	public long getWenbaTempId() throws UiacException;
	
	public long getWenbaTempId(Map<String, String> headers) throws UiacException;
}
