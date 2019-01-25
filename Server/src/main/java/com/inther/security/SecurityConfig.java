package com.inther.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private DataSource dataSource;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.authorities-query}")
    private String authoritiesQuery;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(authoritiesQuery)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/userRegister").hasAuthority("ROLE_ANONYMOUS")
                .anyRequest().authenticated();
        http.csrf()
                .disable();
        http.formLogin()
                .loginPage("/userLogin")
                .loginProcessingUrl("/userLogin")
                .usernameParameter("email")
                .defaultSuccessUrl("/", true)
                .failureUrl("/userLoginError")
                .permitAll();
        http.logout()
                .logoutUrl("/userLogout")
                .logoutSuccessUrl("/userLogin");
        http.exceptionHandling()
                .accessDeniedPage("/accessDenied");
    }

    @Autowired
    public SecurityConfig(DataSource dataSource, BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.dataSource = dataSource;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
}