package com.chenhe.service.transaction;

/**
 * @author chenhe
 * @Date 2018-04-17 18:11
 * @desc 测试事务的传播行为
 **/
public interface TansactionTestService {
    /**
     * PROPAGATION_REQUIRE : 事务不存在,则创建事务
     * @param exist 事务是否存在
     */
    void testRequire(boolean exist);

    /**
     * PROPAGATION_REQUIRE_NEW: 创建一个新的事务,如果当前存在事务,则把当前事务挂起
     * @param exist
     */
    void testRequireNew(boolean exist);

    void testSupports(boolean exist);
}
