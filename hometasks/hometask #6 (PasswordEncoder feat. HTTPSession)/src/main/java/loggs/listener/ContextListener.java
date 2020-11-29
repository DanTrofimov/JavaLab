package loggs.listener;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import loggs.repository.CookieRepositoryImpl;
import loggs.services.CookieServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Properties properties = new Properties();
        try {
            properties.load(sce.getServletContext().getResourceAsStream("/WEB-INF/properties/db.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(properties.getProperty("db.jdbc.url"));
        hikariConfig.setDriverClassName(properties.getProperty("db.jdbc.driver-class-name"));
        hikariConfig.setUsername(properties.getProperty("db.jdbc.username"));
        hikariConfig.setPassword(properties.getProperty("db.jdbc.password"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.jdbc.hikari.max-pool-size")));

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        CookieRepositoryImpl authRepository = new CookieRepositoryImpl(dataSource);
        CookieServiceImpl authService = new CookieServiceImpl(authRepository);

        sce.getServletContext().setAttribute("authService", authService);
    }
}