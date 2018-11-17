package com.xrk.uiac.sdk.common;

/**
 * 
 * CONST: CONST.java.
 *
 * <br>==========================
 * <br> 公司：广州向日葵信息科技有限公司
 * <br> 开发：shunchiguo<shunchiguo@xiangrikui.com>
 * <br> 版本：1.0
 * <br> 创建时间：2015年7月15日
 * <br> JDK版本：1.7
 * <br>==========================
 */
public class CONST
{
	public static final String CLIENT_VERSION = "XRK-CLIENT-VERSION";
	public static final String APPID = "XRK-APPID";
	public static final String ACCESS_TOKEN = "XRK-ACCESS-TOKEN";

	/**
	 * 验证码类型, 注册
	 */
	public static final int CAPTCHA_CHECKTYPE_REGISTER = 1;
	
	/**
	 * 验证码类型, 重置密码
	 */
	public static final int CAPTCHA_CHECKTYPE_RESET_PASSWORD = 2;
	
	/**
	 * 验证码类型, 绑定
	 */
	public static final int CAPTCHA_CHECKTYPE_BINDING = 3;
	
	/**
	 * 验证码类型，登录
	 */
	public static final int CAPTCHA_CHECKTYPE_LOGIN = 4;
}
