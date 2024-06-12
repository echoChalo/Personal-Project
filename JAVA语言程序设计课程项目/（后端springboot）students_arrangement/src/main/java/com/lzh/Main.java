package com.lzh;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class Main {
    public static void main(String[] args) {
        //创建对象
        AutoGenerator autoGenerator = new AutoGenerator();
        //数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/student_arrangement");  //后面接着数据库表格地址
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("13944tty");
        autoGenerator.setDataSource(dataSourceConfig);

        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir("D:\\Java_Development\\Source Files\\大二Java大作业\\students_arrangement\\src\\main\\java");
        globalConfig.setAuthor("LZH");
        globalConfig.setOpen(false);
        //去掉Service的前缀名字
        globalConfig.setServiceName("%sService");
        autoGenerator.setGlobalConfig(globalConfig);

        //包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.lzh");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setController("controller");
        autoGenerator.setPackageInfo(packageConfig);

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude("student","teacher","system_admin");  //这里要和表名一致
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);  //下划线转驼峰（大写）
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
        autoGenerator.setStrategy(strategyConfig);

        //启动
        autoGenerator.execute();
    }
}
