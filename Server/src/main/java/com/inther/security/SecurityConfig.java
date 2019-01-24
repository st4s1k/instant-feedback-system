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
    private final DataSource dataSource;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/api/userRegister").permitAll()
                .antMatchers("/api/user/editSelfUser").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/api/user/addPresentation").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/api/user/getPresentations").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/api/user/editSelfPresentation").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/api/user/deleteSelfPresentation").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/api/user/addMessage").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/api/user/editSelfMessage").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/api/user/deleteSelfMessage").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/api/user/addMark").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/api/admin/addNewUser").hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/admin/editAnyUser").hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/admin/deleteAnyUser").hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/admin/editAnyPresentation").hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/admin/deleteAnyPresentation").hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/admin/editAnyMessage").hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/admin/deleteAnyMessage").hasAuthority("ROLE_ADMIN");
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