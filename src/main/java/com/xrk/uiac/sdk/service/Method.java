package com.xrk.uiac.sdk.service;

/**
 * 
 * Method: Method.java.
 *
 * <br>==========================
 * <br> 公司：广州向日葵信息科技有限公司
 * <br> 开发：yexx<yexiaoxiao@xiangrikui.com>
 * <br> 版本：1.0
 * <br> 创建时间：2015年9月25日 下午12:32:27
 * <br> JDK版本：1.8
 * <br>==========================
 */
public class Method
{
	private static final String METHOD_GET_NAME = "GET";
	private static final String METHOD_POST_NAME = "POST";
	private static final String METHOD_DELETE_NAME = "DELETE";
	private static final String METHOD_PUT_NAME = "PUT";
	
	private String name;
	
	private Method()
	{
		
	}
	
	private Method(String name)
	{
		this.name = name;
	}
	
	public String name()
	{
		return name;
	}
	
	public static Method GET()
	{
		return new Method(METHOD_GET_NAME);
	}
	
	public static Method POST()
	{
		return new Method(METHOD_POST_NAME);
	}
	
	public static Method DELETE()
	{
		return new Method(METHOD_DELETE_NAME);
	}
	
	public static Method PUT()
	{
		return new Method(METHOD_PUT_NAME);
	}
}
