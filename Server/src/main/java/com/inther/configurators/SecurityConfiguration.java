package com.inther.configurators;

import com.inther.assets.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.SessionManagementFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    private final DataSource dataSource;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CorsFilter corsFilter;

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
        http.addFilterBefore(corsFilter, SessionManagementFilter.class);
        http.authorizeRequests()
                .antMatchers("/authentication").hasAuthority("ROLE_ANONYMOUS")
                .antMatchers("/authentication/invalidAuthenticationData").hasAuthority("ROLE_ANONYMOUS")
                .antMatchers("/api/presentation").permitAll()
                .antMatchers("/api/presentation/**").permitAll()
                .anyRequest().authenticated();
        http.cors();
        http.csrf()
                .disable();
        http.formLogin()
                .loginPage("/authentication")
                .loginProcessingUrl("/authentication")
                .usernameParameter("email")
                .defaultSuccessUrl("/authentication/success", true)
                .failureUrl("/authentication/invalidAuthenticationData")
                .permitAll();
        http.logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/authentication");
    }

    @Autowired
    public SecurityConfiguration(DataSource dataSource, BCryptPasswordEncoder bCryptPasswordEncoder, CorsFilter corsFilter)
    {
        this.dataSource = dataSource;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.corsFilter = corsFilter;
    }
}