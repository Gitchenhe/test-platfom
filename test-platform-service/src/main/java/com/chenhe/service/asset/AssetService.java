package com.chenhe.service.asset;

import java.math.BigDecimal;

/**
 * @author chenhe
 * @Date 2018-09-05 11:38
 * @desc
 **/
public interface AssetService {

    String preIncrease(String accountId,String destAccountId, BigDecimal account);

    void confirmIncrease(String uuid);
}
