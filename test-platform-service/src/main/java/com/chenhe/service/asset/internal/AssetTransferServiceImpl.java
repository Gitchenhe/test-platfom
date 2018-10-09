package com.chenhe.service.asset.internal;

import com.chenhe.service.asset.AssetService;
import com.chenhe.service.asset.AssetTransferService;
import com.chenhe.service.asset.param.AccountTransferParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenhe
 * @Date 2018-09-05 11:33
 * @desc
 **/
@Service
public class AssetTransferServiceImpl implements AssetTransferService {
    private static Logger logger = LoggerFactory.getLogger(AssetTransferServiceImpl.class);
    @Autowired
    private AssetService assetService;
    @Override
    public void accountTransfer(AccountTransferParam param) {
        String uuid;
        try{
            uuid = assetService.preIncrease(param.getSourceAccountId(),param.getDestAccountId(),param.getAmount());
        }catch (Exception e){
            logger.error("",e);
        }
    }
}
