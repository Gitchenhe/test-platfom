package com.chenhe.factorybean;

import com.chenhe.factorybean.db.MysqlDB;
import com.chenhe.factorybean.db.OracleDB;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author chenhe
 * @Date 2018-04-17 15:50
 * @desc 代理对象
 **/
public class ProxyDBObject implements FactoryBean<Object> {
    private String currentDB;

    @Override
    public Object getObject() throws Exception {
        if("mysql".equals(currentDB)){
            return new MysqlDB();
        }
        return new OracleDB();
    }

    @Override
    public Class<?> getObjectType() {
        if("mysql".equals(currentDB)){
            return MysqlDB.class;
        }
        return OracleDB.class;
    }

    public String getCurrentDB() {
        return currentDB;
    }

    public void setCurrentDB(String currentDB) {
        this.currentDB = currentDB;
    }
}
