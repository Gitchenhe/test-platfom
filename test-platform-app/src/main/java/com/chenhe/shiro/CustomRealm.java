package com.chenhe.shiro;

import com.chenhe.service.account.AccountService;
import com.chenhe.service.account.param.UserAuthParam;
import com.chenhe.service.dto.UserAccountEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 域,验证从数据库读取用户认证信息,用户权限信息
 */
public class CustomRealm extends AuthorizingRealm {
    Logger logger = LoggerFactory.getLogger(CustomRealm.class);

    @Autowired
    AccountService accountService;

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取身份信息
        String userName = (String) principals.getPrimaryPrincipal();
        logger.info("用户授权,userName = {}", userName);
        //根据身份信息从数据库中查询权限数据
        //这里使用静态数据
        List<String> permissions = new ArrayList<>();
        permissions.add("user:*");
        permissions.add("department:*");

        //将权限信息封装为AuthorizationInfo
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole("manager");

        for (String permission : permissions) {
            simpleAuthorizationInfo.addStringPermission(permission);
        }

        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        //从token中获取用户身份信息
        String account = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());
        logger.info("用户认证,userName = {}", account);
        //通过userName从数据库中查询
        UserAuthParam param = new UserAuthParam();
        param.setAccount(account);
        param.setPassword(password);
        UserAccountEntity entity = accountService.queryUser(param);
        //如果查询不到则返回null
        if (null == entity)
            return null;


        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(account, password, getName());
        return simpleAuthenticationInfo;
    }
}
