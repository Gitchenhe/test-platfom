package com.chenhe.proxytest;

/**
 * @author chenhe
 * @Date 2018-08-10 9:51
 * @desc
 **/
public class ProxyTestService {


    private TestDao testDao;

    public String say(){
      return testDao.say("时间 - "+System.currentTimeMillis());
    }
}
