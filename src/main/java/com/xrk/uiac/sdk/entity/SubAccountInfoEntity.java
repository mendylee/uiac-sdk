package com.xrk.uiac.sdk.entity;

/**
 * 
 * SubAccountInfoEntity: SubAccountInfoEntity.java.
 *
 * <br>==========================
 * <br> 公司：广州向日葵信息科技有限公司
 * <br> 开发：shunchiguo<shunchiguo@xiangrikui.com>
 * <br> 版本：1.0
 * <br> 创建时间：2015年7月15日
 * <br> JDK版本：1.7
 * <br>==========================
 */
public class SubAccountInfoEntity
{
	private String account;
	private String appId;
	private String appName;
	private boolean thirdParty;
	
	public String getAccount()
	{
		return account;
	}
	public void setAccount(String account)
	{
		this.account = account;
	}
	public String getAppId()
	{
		return appId;
	}
	public void setAppId(String appId)
	{
		this.appId = appId;
	}
	public String getAppName()
	{
		return appName;
	}
	public void setAppName(String appName)
	{
		this.appName = appName;
	}
	public boolean getThirdParty()
	{
		return thirdParty;
	}
	public void setThirdParty(boolean thirdParty)
	{
		this.thirdParty = thirdParty;
	}
}
