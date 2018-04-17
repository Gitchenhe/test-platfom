package com.chenhe.factorybean;

import com.chenhe.factorybean.dto.DBEntity;

/**
 * @author chenhe
 * @Date 2018-04-17 15:45
 * @desc 数据库操作类
 **/
public interface DBOperation<T extends DBEntity> {
    int save(T t);

    int update(T t);

    int delete(T t);

    T select(Integer id);
}
