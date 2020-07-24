package com.ljf.web_scaffolding.aop;

import com.google.gson.Gson;
import com.ljf.web_scaffolding.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mr.lin on 2020/5/21
 * 前缀树审查敏感词
 */
@Slf4j
@Aspect
@Configuration
public class PreTreeSensitiveWordAOP {

    private final String cutPoint = "execution(* com.ljf.web_scaffolding.controllers..*.*(..))";

    @Around(cutPoint)
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) {
        initData();
        Object[] args = proceedingJoinPoint.getArgs();
        String content = new Gson().toJson(args);
        Long start = System.currentTimeMillis();
        log.info("数据加载完成，开始审查内容");
        if (analysisContentSuccess(content)) {
            log.info("审查通过 cost:{}", System.currentTimeMillis() - start);
            try {//obj之前可以写目标方法执行前的逻辑
                Object obj = proceedingJoinPoint.proceed();//调用执行目标方法
                return obj;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return null;//如果不返回，则会999错误
            }
        }
        log.info("审查不通过 cost:{}", System.currentTimeMillis() - start);
        return ResultUtil.error(999, "敏感词");
    }

    private void initData() {

        if (sensitiveWordMap == null || sensitiveWordMap.size() < 1) {
            List list = loadWord();
            fillMap(list);
        }

    }

    private HashMap sensitiveWordMap;

    //加载词组
    private List loadWord() {
        List<String> keyList = new ArrayList();

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
            return keyList;
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
        return null;
    }

    //构建树
    private void fillMap(List<String> keyList) {
        sensitiveWordMap = new HashMap(keyList.size());
        Map currentMap = null;

        for (String key : keyList) {
            currentMap = sensitiveWordMap;
            for (int i = 0; i < key.length(); i++) {
                char keyChar = key.charAt(i);
                Object temp = currentMap.get(keyChar);

                if (temp != null) {
                    currentMap = (Map) temp;
                } else {
                    Map newWordMap = new HashMap<>();
                    newWordMap.put("isEnd", "0");
                    currentMap.put(keyChar, newWordMap);
                    currentMap = newWordMap;
                }

                if (i == key.length() - 1) {
                    currentMap.put("isEnd", "1");
                }
            }
        }

    }

    //分析文本
    private boolean analysisContentSuccess(String content) {
        for (int i = 0; i < content.length(); i++) {
            if (containWord(content, i)) {//含有关键词
                return false;
            }
        }
        return true;
    }

    //过滤
    private boolean containWord(String content, int startIndex) {
        Map currentMap = sensitiveWordMap;

        for (int i = startIndex; i < content.length(); i++) {
            char word = content.charAt(i);
            currentMap = (Map) currentMap.get(word);
            if (currentMap != null) {
                if ("1".equals(currentMap.get("isEnd"))) {
                    return true;
                }
            } else {
                break;
            }
        }
        return false;
    }
}
