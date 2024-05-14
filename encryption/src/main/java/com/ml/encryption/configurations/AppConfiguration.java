package com.ml.encryption.configurations;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ml.encryption.components.DatabaseConfiguration;
import com.ml.encryption.enums.DbTypes;

@Configuration
public class AppConfiguration {

    @Bean
    public SessionFactory getSessionFactory(DbTypes dbType, DatabaseConfiguration databaseConfiguration) {

        if (dbType == null) {

            throw new IllegalArgumentException(
                    "Database type can not be null or empty here, please specify your database type before requesting for a session factory instance.");
        } else {

            if (dbType.name().equals("MYSQL")) {

                return databaseConfiguration.configureMySQLDatabase();
            } else {

                throw new IllegalArgumentException(
                        String.format("No database has been specified for the name : %s", dbType.name()));
            }
        }
    }
}
