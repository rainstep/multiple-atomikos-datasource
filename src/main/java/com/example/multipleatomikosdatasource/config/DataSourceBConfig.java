package com.example.multipleatomikosdatasource.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = "com.example.multipleatomikosdatasource.mapper.b"
        , sqlSessionFactoryRef = "sqlSessionFactoryB"
//        , sqlSessionTemplateRef = "sqlSessionTemplateB"
)
public class DataSourceBConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource-b")
    public DataSource dataSourceB() {
        return new AtomikosDataSourceBean();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryB() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceB());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("mapper/b/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

//    @Bean
//    public SqlSessionTemplate sqlSessionTemplateB() throws Exception {
//        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactoryB());
//        return sqlSessionTemplate;
//    }

}
