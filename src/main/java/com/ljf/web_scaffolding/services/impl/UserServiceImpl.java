package com.ljf.web_scaffolding.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.ljf.web_scaffolding.entity.User;
import com.ljf.web_scaffolding.enums.ResultEnum;
import com.ljf.web_scaffolding.mapper.UserMapper;
import com.ljf.web_scaffolding.request.LoginRequest;
import com.ljf.web_scaffolding.request.SignUpRequest;
import com.ljf.web_scaffolding.services.UserService;
import com.ljf.web_scaffolding.utils.ResultUtil;
import com.ljf.web_scaffolding.vo.LoginVo;
import com.ljf.web_scaffolding.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jef
 * @since 2020-05-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    UserMapper userMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public ResultVo login(LoginRequest request) {

        User user = userMapper.findByAccount(request.getAccount());

        if (null != user) {//登录验证成功
            String token = redisTemplate.opsForValue().get(request.getAccount());
            if (!StringUtils.isEmpty(token)) {//登录中，踢出另一个
                redisTemplate.delete(request.getAccount());
                redisTemplate.delete(token);
            }
            token = "token";//生成token

            redisTemplate.opsForValue().set(request.getAccount(), token, 24 * 60 * 60 * 1000, TimeUnit.MILLISECONDS);

            // 根据account获取用户信息，由token当key，实体做value
            LoginVo loginVo = new LoginVo();
            BeanUtils.copyProperties(user, loginVo);
            loginVo.setToken(token);

            String value = new Gson().toJson(loginVo);
            redisTemplate.opsForValue().set(token, value, 24 * 60 * 60 * 1000, TimeUnit.MILLISECONDS);

            return ResultUtil.success(loginVo);
        } else {
            return ResultUtil.resultVoFromEnum(ResultEnum.LOGIN_FAIL);
        }

    }

    @Override
    public ResultVo logout(String token) {
        redisTemplate.delete(token);
        return ResultUtil.success();
    }

    @Override
    public ResultVo signUp(SignUpRequest request) {
        User user = userMapper.findByAccount(request.getAccount());
        if (null != user) {
            return ResultUtil.resultVoFromEnum(ResultEnum.ACCOUNT_EXIST);
        }

        User temp = new User();
        BeanUtils.copyProperties(request, temp);
        save(temp);
        return ResultUtil.success();
    }

    @Override
    public ResultVo getUserInfo(String token) {
        String json = redisTemplate.opsForValue().get(token);

        LoginVo loginVo = new Gson().fromJson(json, LoginVo.class);

        return ResultUtil.success(loginVo);
    }
}
