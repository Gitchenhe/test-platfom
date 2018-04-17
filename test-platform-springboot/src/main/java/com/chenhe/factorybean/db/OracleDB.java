package com.chenhe.factorybean.db;

import com.chenhe.factorybean.DBOperation;
import com.chenhe.factorybean.dto.MysqlDBEntity;
import com.chenhe.factorybean.dto.OracleDBEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenhe
 * @Date 2018-04-17 15:48
 * @desc
 **/
public class OracleDB implements DBOperation<OracleDBEntity> {
    Logger logger = LoggerFactory.getLogger(OracleDB.class);
    @Override
    public int save(OracleDBEntity oracleDBEntity) {
        logger.info("保存至 Oracle id={}",oracleDBEntity.getId());
        return 0;
    }

    @Override
    public int update(OracleDBEntity oracleDBEntity) {
        return 0;
    }

    @Override
    public int delete(OracleDBEntity oracleDBEntity) {
        return 0;
    }

    @Override
    public OracleDBEntity select(Integer id) {
        return null;
    }
}
