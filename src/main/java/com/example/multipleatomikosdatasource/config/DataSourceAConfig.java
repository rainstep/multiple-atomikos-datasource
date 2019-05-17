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
@MapperScan(value = "com.example.multipleatomikosdatasource.mapper.a"
        , sqlSessionFactoryRef = "sqlSessionFactoryA"
//        , sqlSessionTemplateRef = "sqlSessionTemplateA"
)
public class DataSourceAConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource-a")
    public DataSource dataSourceA() {
        return new AtomikosDataSourceBean();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryA() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceA());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("mapper/a/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

//    @Bean
//    public SqlSessionTemplate sqlSessionTemplateA() throws Exception {
//        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactoryA());
//        return sqlSessionTemplate;
//    }

}
