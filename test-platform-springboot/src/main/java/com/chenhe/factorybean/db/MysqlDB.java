package com.chenhe.factorybean.db;

import com.chenhe.factorybean.DBOperation;
import com.chenhe.factorybean.dto.MysqlDBEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenhe
 * @Date 2018-04-17 15:46
 * @desc
 **/
public class MysqlDB implements DBOperation<MysqlDBEntity> {
    Logger logger = LoggerFactory.getLogger(MysqlDB.class);


    @Override
    public int save(MysqlDBEntity mysqlDBEntity) {
        logger.info("保存至 Mysql id={}",mysqlDBEntity.getId());
        return 0;
    }

    @Override
    public int update(MysqlDBEntity mysqlDBEntity) {
        return 0;
    }

    @Override
    public int delete(MysqlDBEntity mysqlDBEntity) {
        return 0;
    }

    @Override
    public MysqlDBEntity select(Integer id) {
        return null;
    }
}
