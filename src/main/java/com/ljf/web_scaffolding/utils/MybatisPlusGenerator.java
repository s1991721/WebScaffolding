package com.ljf.web_scaffolding.utils;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mr.lin on 2020/5/14
 * 生成代码工具
 */
public class MybatisPlusGenerator {

    public static void main(String[] args) {

        System.out.println("MybatisPlusGenerator 开始");

        String projectPath = "D:/generator";
        String packagePath = "com.ljf";
        String moduleName = "web_scaffolding";

        //生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor("Jef");
        globalConfig.setOpen(true);
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);

        //数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/scaffolding?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimeZone=GMT%2B8");
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");

        //包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(packagePath);
        packageConfig.setModuleName(moduleName);

        //自定义配置
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };

        String templatePath="/templates/mapper.xml.ftl";

        List<FileOutConfig> fileOutConfigList = new ArrayList<>();
        fileOutConfigList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/resources/mapper/" + packageConfig.getModuleName() + "/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        injectionConfig.setFileOutConfigList(fileOutConfigList);

        //模板配置
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
//        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setControllerMappingHyphenStyle(true);
        strategyConfig.setTablePrefix("t_scaffolding_");

        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setDataSource(dataSourceConfig);
        autoGenerator.setPackageInfo(packageConfig);
        autoGenerator.setCfg(injectionConfig);
        autoGenerator.setTemplate(templateConfig);
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());

        autoGenerator.execute();

        System.out.println("MybatisPlusGenerator 结束");
    }

}
