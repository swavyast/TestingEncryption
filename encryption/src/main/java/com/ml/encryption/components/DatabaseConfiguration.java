package com.ml.encryption.components;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ml.encryption.services.SecretService;

@Component
public class DatabaseConfiguration {

    @Autowired
    private SecretService service;

    public DatabaseConfiguration(SecretService secretService) {

        this.service = secretService;
    }

    public SessionFactory configureMySQLDatabase() {

        String username = service.getDatabaseSecrets().getUsername();
        String password = service.getDatabaseSecrets().getPassword();
        Properties properties = new Properties();
        properties.put("hibernate.connection.username", username);
        properties.put("hibernate.connection.password", password);
        properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/database0?createDatabaseIfNotExists");
        properties.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().applySettings(properties).build();
        MetadataSources sources = new MetadataSources();
        Metadata metadata = sources.addPackage("com.ml.encryption").getMetadataBuilder(ssr).build();

        return metadata.buildSessionFactory();
    }

}
