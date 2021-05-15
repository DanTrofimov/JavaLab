package ru.itis.trofimoff.task.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import ru.itis.trofimoff.task.security.token.TokenAuthenticationFilter;
import ru.itis.trofimoff.task.security.token.TokenAuthenticationProvider;
import ru.itis.trofimoff.task.security.token.TokenLogoutFilter;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenAuthenticationFilter tokenAuthenticationFilter;

    @Autowired
    private TokenLogoutFilter tokenLogoutFilter;

    @Autowired
    private TokenAuthenticationProvider tokenAuthenticationProvider;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(tokenLogoutFilter, LogoutFilter.class)
                .authorizeRequests()
                .antMatchers("/todos").hasAuthority("ADMIN")
                .antMatchers("/logout").hasAnyAuthority()
                .antMatchers("/login").permitAll()
                .and()
                .sessionManagement().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(tokenAuthenticationProvider);
    }
}
