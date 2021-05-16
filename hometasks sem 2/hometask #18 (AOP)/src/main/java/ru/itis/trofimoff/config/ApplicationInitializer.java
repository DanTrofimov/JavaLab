package ru.itis.trofimoff.config;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.io.IOException;

public class ApplicationInitializer implements WebApplicationInitializer {

    @Autowired
    private Logger logger;

    @Override
    public void onStartup(ServletContext servletContext) {
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
            logger.error("Exception {}. Info: {}, Message {}. Stacktrace {}", ex, "from ApplicationInitializer", ex.getMessage(), ex.getStackTrace());
        }

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
    }
}
