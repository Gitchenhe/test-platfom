package com.chenhe.service.transaction;

/**
 * @author chenhe
 * @Date 2018-04-18 9:27
 * @desc
 **/
public class TransactionParam {
    /**
     * 是否存在事务
     */
    private boolean existTransaction;
    /**
     * 是否抛出异常
     */
    private boolean throwException;

    public boolean isExistTransaction() {
        return existTransaction;
    }

    public void setExistTransaction(boolean existTransaction) {
        this.existTransaction = existTransaction;
    }

    public boolean isThrowException() {
        return throwException;
    }

    public void setThrowException(boolean throwException) {
        this.throwException = throwException;
    }
}
