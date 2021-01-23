package ru.itis.trofimoff.crf.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
//@ComponentScan(basePackages = "ru.itdrive.web")
@PropertySource(value = "classpath:application.properties")
public class ApplicationConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(environment.getProperty("db.url"));
        hikariConfig.setDriverClassName(environment.getProperty("db.driver.name"));
        hikariConfig.setUsername(environment.getProperty("db.user"));
        hikariConfig.setPassword(environment.getProperty("db.password"));
        return hikariConfig;
    }
}
