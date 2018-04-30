package com.chenhe.service.member;

import com.chenhe.service.dto.UserAccountEntity;
import com.chenhe.service.member.param.QueryUserParam;

import java.util.List;

/**
 * @author Administrator
 * @Date 2018-04-30 13:51
 * @desc
 **/
public interface MemberService {
    List<UserAccountEntity> queryMember(QueryUserParam param);
}
