package com.chenhe.filter;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author chenhe
 * @Date 2018-04-20 17:30
 * @desc
 **/
public class RememberMeFilter extends FormAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request,response);
        Session session = subject.getSession();
        return false;

    }
}
