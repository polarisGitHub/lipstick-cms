package com.polaris.he.application;

import com.polaris.he.framework.dao.Dao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * User: hexie
 * Date: 2018-12-08 16:17
 * Description:
 */
@Configuration
@EnableConfigurationProperties
@EnableCaching(proxyTargetClass = true)
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@MapperScan(basePackages = {"com.polaris.he.framework.dao", "com.polaris.he.lipstick.dao"}, markerInterface = Dao.class)
public class ContextConfiguration {
}