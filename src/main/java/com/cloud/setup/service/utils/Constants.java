package com.cloud.setup.service.utils;

import java.util.Properties;

public class Constants {
    public final static Properties POSTGRES_JPA_PROPERTIES = new Properties() {{
        put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        put("hibernate.hbm2ddl.auto", "update");
        put("hibernate.ddl-auto", "update");
        put("show-sql", "true");
    }};

    public final static Properties SQLLITE_JPA_PROPERTIES = new Properties() {
        {
            put("hibernate.dialect", "com.cloud.setup.service.database.SQLiteDialect");
            put("hibernate.hbm2ddl.auto", "create-drop");
            put("hibernate.ddl-auto", "create-drop");
            put("show-sql", "true");
        }};
}
