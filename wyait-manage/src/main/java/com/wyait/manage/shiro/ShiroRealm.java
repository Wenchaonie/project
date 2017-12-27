package com.wyait.manage.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @项目名称：wyait-manage
 * @包名：com.wyait.manage.shiro
 * @类描述：
 * @创建人：wyait
 * @创建时间：2017-12-13 13:53
 * @version：V1.0
 */
public class ShiroRealm extends AuthorizingRealm {
	@Override protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principalCollection) {
		//TODO
		return null;
	}

	@Override protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken)
			throws AuthenticationException {
		//TODO
		return null;
	}
}
