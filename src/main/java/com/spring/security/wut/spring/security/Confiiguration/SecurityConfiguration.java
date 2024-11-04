package com.spring.security.wut.spring.security.Confiiguration;

import com.spring.security.wut.spring.security.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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

//      httpSecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated());
//      httpSecurity.formLogin(Customizer.withDefaults());
       httpSecurity.httpBasic(Customizer.withDefaults());
       httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

       return httpSecurity.build();
   }

   @Bean
    public AuthenticationProvider authenticationProvider()
   {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
       authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        return authenticationProvider;
   }



   @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
   {
       return new BCryptPasswordEncoder(12);
   }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
      return  authenticationConfiguration.getAuthenticationManager();
   }


}
