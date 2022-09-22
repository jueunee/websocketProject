/*
//package com.example.websocketproject.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    @EnableWebSecurity // 스프링 시큐리티를 사용한다는 의미이다.
//    public class SecurityConfig extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http    .authorizeRequests() // 시큐리티 처리에 HttpServletRequest를 이용
//                    .anyRequest().authenticated(); // 모든 요청에 인증 필요
//
//            http    .formLogin(); // 폼 로그인 방식 사용
//        }
//    }
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .authorizeHttpRequests((requests) -> requests
////                        .antMatchers("/", "/home").permitAll()
////                        .anyRequest().authenticated()
////                )
////                .formLogin((form) -> form
////                        .loginPage("/login")
////                        .permitAll()
////                )
////                .logout((logout) -> logout.permitAll());
////
////        return http.build();
////    }
////
////    @Bean
////    public UserDetailsService userDetailsService() {
////        UserDetails user =
////                User.withDefaultPasswordEncoder()
////                        .username("user")
////                        .password("password")
////                        .roles("USER")
////                        .build();
////
////        return new InMemoryUserDetailsManager(user);
////    }
//}
*/
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*

package com.example.websocketproject.config;

import com.example.websocketproject.springbootmongodbsecurity.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

*/
/**
 *
 * @author didin
 *//*

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Bean
    public UserDetailsService mongoUserDetails() {
        return new CustomUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailsService = mongoUserDetails();
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/dashboard/**").hasAuthority("ADMIN").anyRequest()
                .authenticated().and().csrf().disable().formLogin().successHandler(customizeAuthenticationSuccessHandler)
                .loginPage("/login").failureUrl("/login?error=true")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

}*/
