package ru.itis.trofimoff.todoapp.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import ru.itis.trofimoff.todoapp.repositories.GroupRepository;
import ru.itis.trofimoff.todoapp.repositories.TodoRepository;
import ru.itis.trofimoff.todoapp.repositories.UserRepository;
import ru.itis.trofimoff.todoapp.services.admin.AdminService;
import ru.itis.trofimoff.todoapp.services.admin.AdminServiceImpl;
import ru.itis.trofimoff.todoapp.services.group.GroupServiceImpl;
import ru.itis.trofimoff.todoapp.services.todo.TodoServiceImpl;
import ru.itis.trofimoff.todoapp.services.user.UserServiceImpl;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@ComponentScan("ru.itis.trofimoff.todoapp.controllers")
@ComponentScan("ru.itis.trofimoff.todoapp.repositories")
@EnableWebMvc
@PropertySource("classpath:db.properties")
public class AppConfig implements WebMvcConfigurer {

  @Autowired
  private Environment environment;

//  @Bean
//  public ViewResolver viewResolver() {
//    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//    resolver.setPrefix("/WEB-INF/views/");
//    resolver.setSuffix(".jsp");
//    resolver.setViewClass(JstlView.class);
//    resolver.setRedirectContextRelative(false);
//    return resolver;
//  }

  @Bean
  public FreeMarkerViewResolver freeMarkerViewResolver(){
    FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
    resolver.setSuffix(".ftl");
    return resolver;
  }

  @Bean
  public FreeMarkerConfigurer freeMarkerConfig(){
    FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
    configurer.setTemplateLoaderPath("/WEB-INF/freemarker/views");
    return configurer;
  }

//  @Bean
//  public UserValidator validator(){
//    return new UserValidator();
//  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AdminServiceImpl adminService() {
    return new AdminServiceImpl(userService(), todoService());
  }

  @Bean
  public GroupServiceImpl groupService() {
    return new GroupServiceImpl(groupRepository());
  }

  @Bean
  public GroupRepository groupRepository() {
    return new GroupRepository(dataSource());
  }

  @Bean
  public TodoServiceImpl todoService() {
    return new TodoServiceImpl(todoRepository());
  }

  @Bean
  public TodoRepository todoRepository() {
    return new TodoRepository(dataSource());
  }

  @Bean
  public UserServiceImpl userService() {
    return new UserServiceImpl(userRepository(), passwordEncoder());
  }

  @Bean
  public UserRepository userRepository() {
    return new UserRepository(dataSource());
  }

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