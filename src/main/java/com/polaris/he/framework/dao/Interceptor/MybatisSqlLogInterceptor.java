package com.polaris.he.framework.dao.Interceptor;

import com.polaris.he.framework.dao.Interceptor.utils.MappedStatementHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
 * User: hexie
 * Date: 2019-01-17 00:31
 * Description:
 */
@Slf4j
@Intercepts(value = {
        @Signature(type = StatementHandler.class, args = {Statement.class, ResultHandler.class}, method = "query"),
        @Signature(type = StatementHandler.class, args = {Statement.class}, method = "update"),
        @Signature(type = StatementHandler.class, args = {Statement.class}, method = "batch")
})
public class MybatisSqlLogInterceptor implements Interceptor {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = MappedStatementHolder.get();
        String id = Optional.ofNullable(mappedStatement).map(MappedStatement::getId).orElse("");
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        long start = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            long end = System.currentTimeMillis();
            log.info("execution time:{}ms,{} #{}", end - start, id, processSqlString(statementHandler.getBoundSql()));
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    private String processSqlString(BoundSql boundSql) {
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        String unwrappedSql = sql;
        try {
            Object parameterObject = boundSql.getParameterObject();
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            if (parameterObject == null || CollectionUtils.isEmpty(parameterMappings)) {
                return sql;
            }
            for (ParameterMapping mapping : parameterMappings) {
                String property = mapping.getProperty();
                Object param = boundSql.getAdditionalParameter(property);
                if (param == null) {
                    if (parameterObject instanceof MapperMethod.ParamMap) {
                        param = ((MapperMethod.ParamMap) parameterObject).getOrDefault(property, null);
                    } else {
                        param = parameterObject;
                    }
                }
                sql = sql.replaceFirst("\\?", getParameterValue(property, param));
            }
        } catch (Exception e) {
            return unwrappedSql;
        }
        return sql;
    }

    private String getParameterValue(String property, Object param) {
        String value = "null";
        if (param == null) {
            return value;
        } else if (param instanceof String) {
            return "'" + param.toString() + "'";
        } else if (param instanceof Date) {
            return "'" + DATE_TIME_FORMATTER.format(((Date) param).toInstant().atZone(ZoneOffset.systemDefault()).toLocalDateTime()) + "'";
        } else if (param instanceof LocalDate) {
            return "'" + DATE_FORMATTER.format((LocalDate) param) + "'";
        } else if (param instanceof LocalDateTime) {
            return "'" + DATE_TIME_FORMATTER.format((LocalDateTime) param) + "'";
        } else {
            Field f = FieldUtils.getField(param.getClass(), property, true);
            if (f != null) {
                try {
                    Object object = FieldUtils.readField(f, param);
                    if (object instanceof String) {
                        return String.format("'%s'", String.valueOf(object));
                    }
                    return String.valueOf(object);
                } catch (IllegalAccessException e) {
                }
            }
            return param.toString();
        }
    }
}