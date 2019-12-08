package com.daydream.demo_admin.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/9/10
 * \* Time: 20:58
 * \* To change this template use File | Settings | File Templates.
 * \* Description: Mybatis配置类
 * \
 */
@Configuration
@MapperScan("com.daydream.demo_admin.**.dao") //扫描DAO
public class MybatisConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.daydream.demo_admin.**.model");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//    扫描sql映射文件
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:**/sqlmap/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
}