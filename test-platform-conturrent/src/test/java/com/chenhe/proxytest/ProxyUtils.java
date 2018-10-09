package com.chenhe.proxytest;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenhe
 * @Date 2018-08-10 11:32
 * @desc
 **/
public class ProxyUtils {

    private static Map<String,Object> beanComponent = new HashMap<>();

    public static void init(){
        TestDao testDao = null;
        try {
            testDao = TestDaoImpl.class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        ProxyTestService proxyTestService = null;
        try {
            proxyTestService = ProxyTestService.class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Field field = null;
        try {
            field = ProxyTestService.class.getDeclaredField("testDao");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        field.setAccessible(true);
        try {
            field.set(proxyTestService,testDao);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        beanComponent.put("proxyTestService",proxyTestService);
    }

    public static Object getBean(String beanName) {
        return beanComponent.get(beanName);
    }
}
