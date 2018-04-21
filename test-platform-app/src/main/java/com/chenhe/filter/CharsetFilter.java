package com.chenhe.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author chenhe
 * @Date 2018-04-18 10:29
 * @desc
 **/
//@WebFilter(filterName = "charsetFilter",urlPatterns = "/*")
public class CharsetFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(CharsetFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("charsetFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("charsetFilter doFilter");
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("charsetFilter destroy");
    }
}
