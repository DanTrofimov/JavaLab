package ru.itis.trofimoff.todoapp.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
//import ru.itis.trofimoff.form.repository.user.UserRepositoryImpl;
//import ru.itis.trofimoff.form.services.user.UserServiceImpl;
//import ru.itis.trofimoff.form.services.validators.UserValidator;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@ComponentScan("ru.itis.trofimoff.todoapp.controllers")
@ComponentScan("ru.itis.trofimoff.todoapp.repositories")
@EnableWebMvc
@PropertySource("classpath:db.properties")
public class Config implements WebMvcConfigurer {

  @Autowired
  private Environment environment;

  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".jsp");
    resolver.setViewClass(JstlView.class);
    resolver.setRedirectContextRelative(false);
    return resolver;
  }

//  @Bean
//  public UserValidator validator(){
//    return new UserValidator();
//  }
//
//  @Bean
//  public UserServiceImpl userService() {
//    return new UserServiceImpl(userRepository());
//  }
//
//  @Bean
//  public UserRepositoryImpl userRepository() {
//    return new UserRepositoryImpl(dataSource());
//  }

  @Bean
  public DataSource dataSource() {
    return new HikariDataSource(hikariConfig());
  }

  @Bean
  public HikariConfig hikariConfig() {
    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setJdbcUrl(environment.getProperty("db.url"));
    hikariConfig.setMaximumPoolSize(Integer.parseInt(Objects.requireNonNull(environment.getProperty("db.hikari.max-pool-size"))));
    hikariConfig.setUsername(environment.getProperty("db.username"));
    hikariConfig.setPassword(environment.getProperty("db.password"));
    hikariConfig.setDriverClassName(environment.getProperty("db.driver.classname"));
    return hikariConfig;
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("registration");
  }
}