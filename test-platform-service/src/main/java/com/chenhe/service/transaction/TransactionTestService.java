package com.chenhe.service.transaction;

/**
 * @author chenhe
 * @Date 2018-04-17 18:11
 * @desc 测试事务的传播行为
 **/
public interface TransactionTestService {
    /**
     * PROPAGATION_REQUIRE : 事务不存在,则创建事务
     * @param param 事务,异常要求
     */
    void testRequire(TransactionParam param);

    /**
     * PROPAGATION_REQUIRE_NEW: 创建一个新的事务,如果当前存在事务,则把当前事务挂起
     * @param param 事务,异常要求
     */
    void testRequireNew(TransactionParam param);

    /**
     *
     * @param param 事务,异常要求
     */
    void testSupports(TransactionParam param);
}
