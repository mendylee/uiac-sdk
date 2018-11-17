package com.xrk.uiac.sdk.exception;

/**
 * 
 * UiacException: UiacException.java.
 *
 * <br>==========================
 * <br> 公司：广州向日葵信息科技有限公司
 * <br> 开发：shunchiguo<shunchiguo@xiangrikui.com>
 * <br> 版本：1.0
 * <br> 创建时间：2015年7月15日
 * <br> JDK版本：1.7
 * <br>==========================
 */
public class UiacException extends Exception
{
    private static final long serialVersionUID = 1L;
    private String errCode;
    private int httpCode;
    
	public UiacException()
	{
		
	}
	
	public UiacException(int httpCode, String errCode, String errMsg)
	{
		super(errMsg);
		setErrCode(errCode);
		setHttpCode(httpCode);
	}

	public String getErrCode()
    {
	    return errCode;
    }

	public void setErrCode(String errCode)
    {
	    this.errCode = errCode;
    }

	public int getHttpCode()
    {
	    return httpCode;
    }

	public void setHttpCode(int httpCode)
    {
	    this.httpCode = httpCode;
    }
}
