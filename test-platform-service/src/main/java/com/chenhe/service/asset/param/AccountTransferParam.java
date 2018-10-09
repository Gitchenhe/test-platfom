package com.chenhe.service.asset.param;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author chenhe
 * @Date 2018-09-05 11:29
 * @desc
 **/
public class AccountTransferParam {
    private String sourceAccountId;
    private String destAccountId;
    private BigDecimal amount;
    private Date time;

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(String sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public String getDestAccountId() {
        return destAccountId;
    }

    public void setDestAccountId(String destAccountId) {
        this.destAccountId = destAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
