package com.chenhe.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * @author chenhe
 * @Date 2018-04-28 16:01
 * @desc
 **/
public class WebSiteMeshFilter extends ConfigurableSiteMeshFilter {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*","/WEB-INF/decorator/default.jsp")
                .addExcludedPath("/login")
                .addExcludedPath("/error");
    }
}
