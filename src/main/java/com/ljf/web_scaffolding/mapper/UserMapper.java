package com.ljf.web_scaffolding.mapper;

import com.ljf.web_scaffolding.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jef
 * @since 2020-05-15
 */
public interface UserMapper extends BaseMapper<User> {

    User findByAccount(String account);

}
