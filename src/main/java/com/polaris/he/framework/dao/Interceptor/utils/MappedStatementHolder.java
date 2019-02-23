package com.polaris.he.framework.dao.Interceptor.utils;

import org.apache.ibatis.mapping.MappedStatement;

/**
 * User: hexie
 * Date: 2019-01-31 21:59
 * Description:
 */
public class MappedStatementHolder {

    private static final ThreadLocal<MappedStatement> MAPPED_STATEMENT_THREAD_LOCAL = ThreadLocal.withInitial(() -> null);

    public static void set(MappedStatement mappedStatement) {
        MAPPED_STATEMENT_THREAD_LOCAL.set(mappedStatement);
    }

    public static MappedStatement get() {
        return MAPPED_STATEMENT_THREAD_LOCAL.get();
    }

    public static void clear(){
        MAPPED_STATEMENT_THREAD_LOCAL.remove();
    }
}