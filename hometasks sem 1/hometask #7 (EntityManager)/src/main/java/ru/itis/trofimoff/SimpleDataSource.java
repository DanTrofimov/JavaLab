package ru.itis.trofimoff;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SimpleDataSource {

    public static Properties properties = new Properties();

    public static void initProps(String propFileName) {
        try (InputStream stream = SimpleDataSource.class.getClassLoader().getResourceAsStream(propFileName)) {
            assert stream != null;
            properties.load(stream);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        initProps("db.properties");

        String dbUrl = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        String dbDriver = properties.getProperty("db.driver");

        dataSource.setUrl(dbUrl);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(dbDriver);

        return dataSource;
    }
}