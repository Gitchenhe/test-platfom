package com.chenhe.service.mapper;

import com.chenhe.service.dto.ExceptionEntity;

/**
 * @author chenhe
 * @Date 2018-04-18 9:14
 * @desc Spring 事务管理(传播)测试mapper
 **/
public interface ExceptionMapper {

    void insert(ExceptionEntity exceptionEntity);
}
