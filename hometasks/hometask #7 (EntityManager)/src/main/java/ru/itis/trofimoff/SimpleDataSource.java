package ru.itis.trofimoff;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class SimpleDataSource {

    public static DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Properties properties = new Properties();

        try {
            properties.load(ClassLoader.getSystemResourceAsStream("db.properties"));
        } catch (IOException e) {
            throw new RuntimeException("db.properties file not found\n" + e);
        }

        dataSource.setUrl(properties.getProperty("db.url"));
        dataSource.setUsername(properties.getProperty("db.username"));
        dataSource.setPassword(properties.getProperty("db.password"));
        dataSource.setDriverClassName(properties.getProperty("db.driver"));

        return dataSource;
    }
}