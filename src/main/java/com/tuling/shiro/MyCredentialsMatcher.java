package com.tuling.shiro;

import com.tuling.common.utils.MD5Util;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class MyCredentialsMatcher extends SimpleCredentialsMatcher {
	@Override
	public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		Object tokenCredentials = encrypt(String.valueOf(token.getPassword()));//令牌的凭证
		Object accountCredentials = getCredentials(info);//帐户凭据(数据库查询出来的密码)
		// 将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false
		return equals(tokenCredentials, accountCredentials);
	}

	/**
	 * 将传进来密码加密方法
	 * @param data
	 * @return
	 */
	private String encrypt(String data) {
		String md5Password= MD5Util.getMD5(data);
		return md5Password;
	}
}
