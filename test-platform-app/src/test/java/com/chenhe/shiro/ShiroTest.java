package com.chenhe.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.apache.shiro.mgt.SecurityManager;


public class ShiroTest {
    /**
     * 配置文件读取用户认证信息,用户权限信息
     */
    @Test
    public void test() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "admin");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            System.err.println("用户身份认证失败");
            e.printStackTrace();
        }
        Session session = subject.getSession();
        System.out.println("session.host:"+session.getHost());
        System.out.println("session id:"+session.getId());
        System.out.println("session timeout:"+session.getTimeout());
        if (subject.isAuthenticated()) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
            return;
        }

        System.out.println(token.getUsername() + " 是否拥有administrator 角色:" + subject.hasRole("administrator"));
        System.out.println(token.getUsername() + " 是否拥有 test 角色:" + subject.hasRole("test"));
        System.out.println();
        System.out.println(token.getUsername() + " 是否拥有 user:add 权限:" + subject.isPermitted("user:add"));
        System.out.println(token.getUsername() + " 是否拥有 test:add 权限:" + subject.isPermitted("test:add"));

        subject.logout();
        System.out.println("admin 注销后,是否有 user;add 权限" + subject.isPermitted("user:add"));
    }

    /**
     * 从域(realm)中读取用户认证信息,授权信息
     */
    @Test
    public void testRealm() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "zhangsan");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            System.err.println("用户身份认证失败");
            e.printStackTrace();
        }

        if (subject.isAuthenticated()) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
            return;
        }

        System.out.println(token.getUsername() + " 是否拥有administrator 角色:" + subject.hasRole("administrator"));
        System.out.println(token.getUsername() + " 是否拥有 test 角色:" + subject.hasRole("test"));
        System.out.println();
        System.out.println(token.getUsername() + " 是否拥有 user:add 权限:" + subject.isPermitted("user:add"));
        System.out.println(token.getUsername() + " 是否拥有 test:add 权限:" + subject.isPermitted("test:add"));

        subject.logout();
        System.out.println("admin 注销后,是否有 user;add 权限" + subject.isPermitted("user:add"));
    }

}
