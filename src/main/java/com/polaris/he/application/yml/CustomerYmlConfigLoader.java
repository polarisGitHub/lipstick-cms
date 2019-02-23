package com.polaris.he.application.yml;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

/**
 * User: hexie
 * Date: 2019-01-12 22:01
 * Description:
 */
@Slf4j
public class CustomerYmlConfigLoader implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
            yaml.setResources(resolver.getResources("classpath*:*.yml"));
            environment.getPropertySources().addLast(new PropertiesPropertySource("customer-config", yaml.getObject()));
        } catch (IOException e) {
            log.error("加载自定义yml错误", e);
        }
    }
}