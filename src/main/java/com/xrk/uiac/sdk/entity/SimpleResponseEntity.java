package com.xrk.uiac.sdk.entity;

/**
 * 
 * SimpleResponseEntity: SimpleResponseEntity.java.
 *
 * <br>==========================
 * <br> 公司：广州向日葵信息科技有限公司
 * <br> 开发：shunchiguo<shunchiguo@xiangrikui.com>
 * <br> 版本：1.0
 * <br> 创建时间：2015年7月15日
 * <br> JDK版本：1.7
 * <br>==========================
 */
public class SimpleResponseEntity<T>
{
	private T result;
	public SimpleResponseEntity(T t)
	{
		setResult(t);
	}
	
	public T getResult()
    {
	    return result;
    }
	public void setResult(T result)
    {
	    this.result = result;
    }
}