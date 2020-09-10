package edu.books.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;

import edu.books.auth.service.UserDetailsService;
import edu.books.filter.JwtRequestFilter;
import edu.books.security.AjaxAuthenticationFailureHandler;
import edu.books.security.AjaxAuthenticationSuccessHandler;
import edu.books.security.AjaxLogoutSuccessHandler;
import edu.books.security.CustomTokenBasedRememberMeHander;
import edu.books.security.Http401UnauthorizedEntryPoint;
import lombok.extern.java.Log;

@Log
@EnableWebSecurity
@Configuration
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

    private String tokenKey = "token_key";

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("unauthorizedEntryPoint")
    private Http401UnauthorizedEntryPoint unauthorizedEntryPoint;

    @Autowired
    @Qualifier("accessDeniedHandler")
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    @Qualifier("authenticationSuccessHandler")
    private AjaxAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    @Qualifier("ajaxAuthenticationFailureHandler")
    private AjaxAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    @Qualifier("ajaxLogoutSuccessHandler")
    private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private DataSource dataSource;


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**",
                "/fonts/**",
                "/img/**",
                "/js/**",
                "/plugins/**",
                "/sample/**",
                "/admins/**",
                "/dist/**",
                "/front/**",
                "/files/**",
                "/uploads/**"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        // dont authenticate this particular request
        .authorizeRequests().antMatchers("/api/admin/login", "/auth/login",  "/api/login/", "/api/logout", "/img/**").permitAll()
        // all other requests need to be authenticated
        .antMatchers( "/api/**")
				.permitAll().and()
        // make sure we use stateless session; session won't be used to
        // user's state.
        .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().logout().deleteCookies();
        // Add a filter to validate the tokens with every request
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }

    /**
     * Remember me config
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
        auth.authenticationProvider(rememberMeAuthenticationProvider());
    }


    @Bean public RememberMeAuthenticationFilter rememberMeAuthenticationFilter() throws Exception{
        return new RememberMeAuthenticationFilter(authenticationManager(), tokenBasedRememberMeService());
    }
    @Bean public CustomTokenBasedRememberMeHander tokenBasedRememberMeService(){
        CustomTokenBasedRememberMeHander service = new CustomTokenBasedRememberMeHander(tokenKey, userDetailsService);
        service.setAlwaysRemember(true);
        service.setCookieName("at");
        return service;
    }
    @Bean
    RememberMeAuthenticationProvider rememberMeAuthenticationProvider(){
        return new RememberMeAuthenticationProvider(tokenKey);
    }
}
