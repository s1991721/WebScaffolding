package com.ljf.web_scaffolding.services;

import com.ljf.web_scaffolding.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljf.web_scaffolding.request.LoginRequest;
import com.ljf.web_scaffolding.request.SignUpRequest;
import com.ljf.web_scaffolding.vo.ResultVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jef
 * @since 2020-05-15
 */
public interface UserService extends IService<User> {

    ResultVo login(LoginRequest request);

    ResultVo logout(String token);

    ResultVo signUp(SignUpRequest request);

    ResultVo getUserInfo(String token);

}
