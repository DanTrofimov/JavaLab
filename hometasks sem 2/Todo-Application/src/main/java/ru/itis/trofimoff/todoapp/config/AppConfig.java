package ru.itis.trofimoff.todoapp.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.freemarker.SpringTemplateLoader;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import ru.itis.trofimoff.todoapp.interceptors.AuthInterceptor;
import ru.itis.trofimoff.todoapp.utils.mail.sender.EmailUtil;
import ru.itis.trofimoff.todoapp.utils.mail.sender.EmailUtilImpl;
import ru.itis.trofimoff.todoapp.utils.mail.generator.FreemarkerMailsGenerator;
import ru.itis.trofimoff.todoapp.utils.mail.generator.MailsGenerator;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Configuration
@ComponentScan("ru.itis.trofimoff.todoapp")
@EnableWebMvc
@PropertySource("classpath:application.properties")
public class AppConfig implements WebMvcConfigurer {

  @Autowired
  private Environment environment;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/styles/**").addResourceLocations("/styles/");
    registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
    registry.addResourceHandler("/scripts/**").addResourceLocations("/scripts/");
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/*");
  }

  @Bean
  public MailsGenerator mailsGenerator() {
    return new FreemarkerMailsGenerator();
  }

  @Bean
  public JavaMailSender javaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    mailSender.setHost(environment.getProperty("spring.mail.host"));
    mailSender.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("spring.mail.port"))));

    mailSender.setUsername(environment.getProperty("spring.mail.username"));
    mailSender.setPassword(environment.getProperty("spring.mail.password"));
    mailSender.setDefaultEncoding("UTF-8");

    Properties props = mailSender.getJavaMailProperties();
    props.put("mail.smtp.starttls.enable", environment.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
    props.put("mail.smtp.allow8bitmime", environment.getProperty("spring.mail.properties.mail.smtp.allow8bitmime"));
    props.put("mail.smtp.ssl.trust", environment.getProperty("spring.mail.properties.mail.smtp.ssl.trust"));
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.debug", environment.getProperty("spring.mail.properties.mail.debug"));

    return mailSender;
  }

  @Bean
  public ExecutorService executorService() {
    return Executors.newCachedThreadPool();
  }

  @Bean
  public EmailUtil emailUtil() {
    return new EmailUtilImpl();
  }
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}