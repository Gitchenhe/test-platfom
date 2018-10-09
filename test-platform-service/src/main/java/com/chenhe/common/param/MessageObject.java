package com.chenhe.common.param;

import java.util.Date;

/**
 * @author chenhe
 * @Date 2018-09-05 10:30
 * @desc
 **/
public class MessageObject {

    private String messageId;
    private Object data;
    private Date createTime;
    private MessageStatusEnum status;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public MessageStatusEnum getStatus() {
        return status;
    }

    public void setStatus(MessageStatusEnum status) {
        this.status = status;
    }
}
