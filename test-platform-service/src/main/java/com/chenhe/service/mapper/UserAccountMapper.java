package com.chenhe.service.mapper;

import com.chenhe.service.dto.UserAccountEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 账号表操作类
 * @author chenhe
 */
public interface UserAccountMapper {
    /**
     * 查询用户  登录使用
     * @param account
     * @param password
     * @return
     */
    UserAccountEntity selectUser(@Param("account") String account, @Param("password") String password);

}
