package ru.itis.trofimoff.todoapp.config;

import lombok.SneakyThrows;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.io.IOException;

public class ApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext springWebContext = new AnnotationConfigWebApplicationContext();

        try {
            PropertySource propertySource = new ResourcePropertySource("classpath:application.properties");
            springWebContext.getEnvironment().setActiveProfiles((String) propertySource.getProperty("spring.profile"));

            springWebContext.register(AppConfig.class);
            servletContext.addListener(new ContextLoaderListener(springWebContext));

            ServletRegistration.Dynamic dispatcherServlet =
                    servletContext.addServlet("dispatcherServlet", new DispatcherServlet(springWebContext));
            dispatcherServlet.setLoadOnStartup(1);
            dispatcherServlet.addMapping("/");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
    }
}
