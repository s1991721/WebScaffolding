package com.ljf.web_scaffolding.aop;

import com.google.gson.Gson;
import com.ljf.web_scaffolding.entity.UserInfo;
import com.ljf.web_scaffolding.enums.ResultEnum;
import com.ljf.web_scaffolding.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mr.lin on 2020/5/20
 * 切面拦截敏感词
 */
@Slf4j
//@Aspect
//@Configuration
public class WordAspect {

    //execution切点函数
    //* 返回类型
    //包名
    //..当前包及其子包
    //* 类名
    //* 方法名
    //(..) 参数
    private final String cutPoint = "execution(* com.ljf.web_scaffolding.controllers..*.*(..))";

    @Autowired
    StringRedisTemplate redisTemplate;

    //执行方法前的拦截方法
//    @Before(cutPoint)
    public void doBeforeMethod(JoinPoint joinPoint) {
        log.info("我是前置通知，我将要执行一个方法了");
        //获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();
        for (Object argItem : obj) {
            log.info("参数修改前:" + argItem);
            if (argItem instanceof UserInfo) {
                UserInfo user = (UserInfo) argItem;
                user.setName("xiaolong英文版");
            }
            log.info("参数修改后:" + argItem);
        }
    }

    /**
     * 后置返回通知
     * 这里需要注意的是:
     * 如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     * 如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning 限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     */
//    @AfterReturning(value = cutPoint, returning = "keys")
    public void doAfterReturningAdvice1(JoinPoint joinPoint, Object keys) {
        log.info("进入方法获取返回结果为：" + keys);
        if (keys instanceof UserInfo) {
            UserInfo resultVO = (UserInfo) keys;
            resultVO.setName("小龙人");
        }
        log.info("可在方法内修改返回结果：" + keys);
    }

    /**
     * 环绕通知：
     * 环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，
     * 执行完毕是否需要替换返回值。
     * 环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
    @Around(cutPoint)
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        prepareWord();
        log.info("环绕通知的目标方法名：" + proceedingJoinPoint.getSignature().getName());
        if (!containKeyWord(proceedingJoinPoint.getArgs())) {
            try {//obj之前可以写目标方法执行前的逻辑
                Object obj = proceedingJoinPoint.proceed();//调用执行目标方法
                return obj;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return ResultUtil.error(999, "敏感词");
    }

    private boolean containKeyWord(Object[] args) {
        String json = new Gson().toJson(args);
        for (String key : keyList) {
            if (json.contains(key)) {
                return true;
            }
        }
        return false;
    }

    private List<String> keyList = new ArrayList();

    public void prepareWord() {

        if (keyList != null && keyList.size() != 0) {
            return;
        }

        keyList = redisTemplate.opsForList().range("keyWord", 0, -1);

        if (keyList != null && keyList.size() != 0) {
            return;
        }

        File file = new File("C:\\Users\\jduser\\Downloads\\words.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                if (!StringUtils.isEmpty(tempString)) {
                    keyList.add(tempString);
                }
            }
            redisTemplate.opsForList().rightPushAll("keyWord", keyList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
