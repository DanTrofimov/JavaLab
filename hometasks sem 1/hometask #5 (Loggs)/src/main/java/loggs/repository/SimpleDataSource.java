package loggs.repository;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class SimpleDataSource {

    public static DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        String dbUrl = "jdbc:postgresql://localhost:5432/manager";
        String user = "postgres";
        String password = "admin";
        String dbDriver = "org.postgresql.Driver";

        dataSource.setUrl(dbUrl);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(dbDriver);

        return dataSource;
    }
}