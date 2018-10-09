package com.chenhe.service.transaction.internal;

import com.chenhe.service.dto.ExceptionEntity;
import com.chenhe.service.mapper.ExceptionMapper;
import com.chenhe.service.transaction.TransactionParam;
import com.chenhe.service.transaction.TransactionTestService;
import com.chenhe.util.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @author chenhe
 * @Date 2018-04-18 9:19
 * @desc
 **/
@Service
public class TransactionTestServiceImpl implements TransactionTestService {
    @Autowired
    private ExceptionMapper exceptionMapper;

    @Override
    public void testRequire(TransactionParam param) {
        TransactionTestServiceImpl transactionTestServiceImpl = SpringUtils.getBean(TransactionTestServiceImpl.class);
        if (param.isExistTransaction()){
            transactionTestServiceImpl.operationWithTrans(param.isThrowException());
        }else{
            transactionTestServiceImpl.operationWithoutTrans(param.isThrowException());
        }
    }

    /**
     * PROPAGATION_REQUIRE_NEW: 创建一个新的事务,如果当前存在事务,则把当前事务挂起
     * @param param 事务,异常要求
     */
    @Override
    public void testRequireNew(TransactionParam param) {

    }

    @Override
    public void testSupports(TransactionParam param) {

    }


    @Transactional(rollbackFor = RuntimeException.class)
    public void operationWithTrans(boolean throwExcepation){
        insert("人为异常,包含事务");
        if (throwExcepation){
            throw new RuntimeException("人为异常,包含事务");
        }
    }

    public void operationWithoutTrans(boolean throwExcepation){
        insert("人为异常,不含事务");
        if (throwExcepation){
            throw new RuntimeException("人为异常,不含事务");
        }
    }


    private void insert(String remark){
        ExceptionEntity entity = new ExceptionEntity();
        entity.setCreateTime(new Date());
        entity.setRemark(remark);
        entity.setRequestNo(UUID.randomUUID().toString());
        exceptionMapper.insert(entity);
    }
}
