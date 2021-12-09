package com.cloud.setup.service.database;

import com.cloud.setup.service.properties.AppProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.MissingResourceException;
import java.util.Properties;

import static com.cloud.setup.service.utils.Constants.POSTGRES_JPA_PROPERTIES;
import static com.cloud.setup.service.utils.Constants.SQLLITE_JPA_PROPERTIES;

@Configuration()
@EnableTransactionManagement
@Slf4j
public class DataSourceConfig {
    private final static String PERSISTENCE_UNIT_NAME = "CONFIG";
    private final AppProperties appProperties;

    public DataSourceConfig(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Bean()
    public HikariDataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        if (appProperties.getDatabase() != null) {
            log.info("Init PostgreSql db");
            hikariConfig.setDriverClassName("org.postgresql.Driver");
            hikariConfig.setJdbcUrl(
                    String.format("jdbc:postgresql://%s:%s/%s",
                    appProperties.getDatabase().getDbHost(),
                            appProperties.getDatabase().getDbPort(),
                            appProperties.getDatabase().getDbDatabase()
                    )
            );
            hikariConfig.setUsername(appProperties.getDatabase().getDbUsername());
            hikariConfig.setPassword(appProperties.getDatabase().getDbPassword());
        } else if (appProperties.getSqLite() != null) {
            log.info("Init Sqlite db");
            hikariConfig.setDriverClassName("org.sqlite.JDBC");
            hikariConfig.setJdbcUrl(String.format("jdbc:sqlite:%s", appProperties.getSqLite().getDatabase()));
        } else {
            log.error("Missing Postgres or Sqlite properties!");
            throw new MissingResourceException("Missing db connection properties", "AppProperties", "database/Sqlite");
        }
        return new HikariDataSource(hikariConfig);
    }

    @Bean()
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(final HikariDataSource hikariDataSource) {
        Properties jpaProperties = appProperties.getDatabase() != null ? POSTGRES_JPA_PROPERTIES : SQLLITE_JPA_PROPERTIES;
        return new LocalContainerEntityManagerFactoryBean() {{
            setDataSource(hikariDataSource);
            setPersistenceProviderClass(HibernatePersistenceProvider.class);
            setPackagesToScan("com.cloud.setup.service.entities");
            setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
            setJpaProperties(jpaProperties);
        }};
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
