package org.zerock.p01.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.zerock.p01.security.CustomUserDetailsService;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@Log4j2
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@ComponentScan({"org.springframework.security.config.annotation.web.builders"})
public class CustomSecurityConfig{

    private final DataSource dataSource;
    private final CustomUserDetailsService userDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityConfigurer(){
        log.info("----web Config----");
        return (web)-> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        log.info("-------configure------");
        http.csrf().disable();
        http.formLogin()
                .loginPage("/memberGroup/login")
                .defaultSuccessUrl("/mainy")
                .and()
                .logout()
                .logoutUrl("/memberGroup/logout")
                .logoutSuccessUrl("/memberGroup/login")
                .deleteCookies("JSESSIONID");
        http.authorizeRequests()
                .antMatchers("/memberGroup/login","/memberGroup/m_register").permitAll()
                .anyRequest().authenticated();


        return http.build();
    }
}