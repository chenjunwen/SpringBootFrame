package com.tuling.shiro;

import com.tuling.modal.User;
import com.tuling.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/8/2.
 */
public class MyShiroRealm extends AuthorizingRealm{

    @Autowired
    UserService userService;

    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //存放登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = userService.login(username);
        if(user!=null){
            //放入shiro.调用CredentialsMatcher检验密码
            return new SimpleAuthenticationInfo(user, user.getPassWord(), getName());
        }
        return null;
    }

    /**
     * 用于获取认证成功后的角色、权限等信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权-------------------------》》》");
        return null;
    }

}
