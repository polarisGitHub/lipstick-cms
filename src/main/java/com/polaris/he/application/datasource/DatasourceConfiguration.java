package com.polaris.he.application.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * User: hexie
 * Date: 2018-12-08 16:02
 * Description:
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class DatasourceConfiguration {

    @Autowired
    private Environment environment;

    @Bean("dataSource")
    public DataSource getDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(environment.getProperty("c3p0.driverClass"));
        dataSource.setJdbcUrl(environment.getProperty("c3p0.url"));
        dataSource.setUser(environment.getProperty("c3p0.username"));
        dataSource.setPassword(environment.getProperty("c3p0.password"));
        dataSource.setMinPoolSize(environment.getProperty("c3p0.minPoolSize", Integer.class));
        dataSource.setMaxPoolSize(environment.getProperty("c3p0.maxPoolSize", Integer.class));
        dataSource.setInitialPoolSize(environment.getProperty("c3p0.initialPoolSize", Integer.class));
        dataSource.setMaxIdleTime(environment.getProperty("c3p0.maxIdleTime", Integer.class));
        dataSource.setCheckoutTimeout(environment.getProperty("c3p0.checkoutTimeout", Integer.class));
        dataSource.setMaxStatements(environment.getProperty("c3p0.maxStatements", Integer.class));
        return dataSource;
    }
}