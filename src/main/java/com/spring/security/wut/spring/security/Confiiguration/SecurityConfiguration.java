package com.spring.security.wut.spring.security.Confiiguration;

import com.spring.security.wut.spring.security.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    MyUserDetailsService myUserDetailsService;


   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
       httpSecurity.csrf(customizer-> customizer.disable());
       httpSecurity.authorizeHttpRequests(request ->
               request.requestMatchers("/addNewUser", "/home", "/login")
                       .permitAll().anyRequest().authenticated());
       httpSecurity.formLogin(Customizer.withDefaults());
//      httpSecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated());
       httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
       httpSecurity.httpBasic(Customizer.withDefaults());
       return httpSecurity.build();
   }

   @Bean
    public AuthenticationProvider authenticationProvider()
   {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        return authenticationProvider;
   }



   @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
   {
       return new BCryptPasswordEncoder(12);
   }

}
