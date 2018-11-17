package com.xrk.uiac.sdk.common;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.xrk.uiac.sdk.exception.UiacException;

public class ParameterUtils
{
	public static String encodeAccountPassword(String srcPwd, Logger logger) throws UiacException
	{
		if(StringUtils.isBlank(srcPwd)){
			throw new UiacException(500, "-1", "password is null");
		}
		String password = null;
		try {
	        password = Codec.hexMD5(srcPwd);
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
        	logger.error(String.format("encoder password failed! msg:%s", e.getMessage()), e);
	        throw new UiacException(500, "-1", e.getMessage());
        }
		return password;
	}
}
