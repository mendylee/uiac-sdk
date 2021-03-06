package com.xrk.uiac.sdk.test;

/**
 * 业务的详细异常码定义
 * BUSINESS_CODE: BUSINESS_CODE.java.
 *
 * <br>==========================
 * <br> 公司：广州向日葵信息科技有限公司
 * <br> 开发：shunchiguo<shunchiguo@xiangrikui.com>
 * <br> 版本：1.0
 * <br> 创建时间：2015年5月8日
 * <br> JDK版本：1.7
 * <br>==========================
 */
public class BUSINESS_CODE
{
	/**
	 * 授权访问码已过期
	 */
	public static final String ACCESS_TOKEN_EXPIRE = "10000001";
	/**
	 * 授权访问码无效
	 */
	public static final String ACCESS_TOKEN_INVALID = "10000002";
	/**
	 * AppID无效
	 */
	public static final String APP_ID_INVALID= "10000003";
	/**
	 * 时间戳无效
	 */
	public static final String TIMESTAMP_INVALID = "10000004";
	/**
	 * 用户不存在
	 */
	public static final String USER_INVALID= "10000005";
	/**
	 * 无效参数
	 */
	public static final String PARAMER_INVAILD = "10000006";
	/**
	 * HTTP请求方法无效
	 */
	public static final String HTTP_METHOD_INVALID = "10000007";
	/**
	 * 内部服务异常
	 */
	public static final String INTERNAL_SERVER_ERROR = "10000020";
	/**
	 * 当前操作的验证码校验失败
	 */
	public static final String CAPTCHA_VALIDATION_WRONG = "10000021";
	/**
	 * 当前操作的验证码校验结果已过期
	 */
	public static final String CAPTCHA_VALIDATION_EXPIRE = "10000022";
	/**
	 * uid不合法
	 */
	public static final String UID_INVALID = "10000023";
	/**
	 * 手机号不合法
	 */
	public static final String MOBILE_INVALID = "10000024";
	
	
	/*****授权信息异常******/
	
	public static final String AUTH_PASSWORD_INVALID = "10010101"; 
	
	public static final String AUTH_REFRESH_TOKEN_INVALID = "10010201";
	
	public static final String AUTH_REFRESH_TOKEN_WRONG = "10010202";
	
	public static final String AUTH_USER_NOT_LOGIN = "10010301";
	 	
	
	/***** 用户管理模块异常码 ******/
	
	/**
	 * 用户注册, 密码格式不合法
	 */
	public static final String CREATE_USER_PASSWORD_INVALID = "10020401";
	/**
	 * 用户注册, 用户基础信息不合法
	 */
	public static final String CREATE_USER_USERINFO_INVALID = "10020402";
	/**
	 * 用户注册, 用户扩展信息不合法
	 */
	public static final String CREATE_USER_EXTENDINFO_INVALID = "10020403";
	/**
	 * 用户注册, 账号已存在
	 */
	public static final String CREATE_USER_USER_EXISTS = "10020499";

	
	/**
	 * 更新用户信息, 用户基础信息不合法
	 */
	public static final String UPDATE_USERINFO_BASEINFO_INVALID = "10020601";
	/**
	 * 更新用户信息, 用户扩展信息不合法
	 */
	public static final String UPDATE_USERINFO_EXTENDINFO_INVALID = "10020602";
	
	
	/**
	 * 修改密码, 密码格式不合法
	 */
	public static final String UPDATE_PASSWORD_PASSWORD_INVALID = "10020701";
	/**
	 * 修改密码, 原始密码错误
	 */
	public static final String UPDATE_PASSWORD_OLD_PASSWORD_WRONG = "10020702";
	
	
	/**
	 * 重置密码, 密码格式不合法
	 */
	public static final String RESET_PASSWORD_PASSWORD_INVALID = "10020801";
	
	
	/**
	 * 禁用账号, 重复操作
	 */
	public static final String DISABLE_USER_OPERATION_REPEATED = "10021601";
	/**
	 * 禁用账号, 没有操作权限
	 */
	public static final String DISABLE_USER_NOT_ALLOWED = "10021699";
	
	
	/**
	 * 解禁账号, 重复操作
	 */
	public static final String ENABLE_USER_OPERATION_REPEATED = "10021701";
	/**
	 * 解禁账号, 没有操作权限
	 */
	public static final String ENABLE_USER_NOT_ALLOWED = "10021799";
	
	
	/**
	 * 绑定手机, 手机号已被绑定
	 */
	public static final String BINDING_MOBILE_NUMBER_OCCUPIED = "10021102";
	/**
	 * 绑定手机, 重复操作
	 */
	public static final String BINDING_MOBILE_OPERATION_REPEATED = "10021103";
	
	
	/**
	 * 手机解绑, 重复操作
	 */
	public static final String UNBINDING_MOBILE_OPERATION_REPEATED = "10021100";
	
	
	/**
	 * 绑定子账号, 临时id不合法
	 */
	public static final String BINDING_SUBACCOUNT_TEMPID_INVALID = "10021301";
	/**
	 * 绑定子账号, 重复操作
	 */
	public static final String BINDING_SUBACCOUNT_OPERATION_REPEATED = "10021302";
	/**
	 * 绑定子账号， 子账号appId不合法
	 */
	public static final String BINDING_SUBACCOUNT_SUB_APPID_INVALID = "10021303";
	
	
	/**
	 * 子账号解除绑定, 子账号id不合法
	 */
	public static final String UNBINDING_SUBACCOUNT_SUB_APPID_INVALID = "10021501";
	/**
	 * 子账号解除绑定, 子账号不存在
	 */
	public static final String UNBINGDING_SUBACCOUNT_NOT_FOUND = "10021502";
	
	
	/**
	 * 发送验证码, 验证方式不合法
	 */
	public static final String SEND_CAPTCHA_CHECKTYPE_INVALID = "10020201";
	
	
	/**
	 * 校验验证码, 验证码不合法
	 */
	public static final String VALIDATION_CAPTCHA_CAPTCHA_INVALID = "10020301";
	/**
	 * 校验验证码, 验证码过期
	 */
	public static final String VALIDATION_CAPTCHA_CAPTCHA_EXPIRY = "10020302";
	/**
	 * 校验验证码, 验证方式不合法
	 */
	public static final String VALIDATION_CAPTCHA_CHECKTYPE_INVALID = "10020303";
	/**
	 * url不合法
	 */
	public static final String URL_INVALID="10030101";
	/**
	 * 观察者已存在
	 */
	public static final String PUSH_OBSERVER_EXISTS="10030102";
}
