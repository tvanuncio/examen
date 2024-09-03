package com.trupper.examen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserServiceConfig {

  @Bean
  public UserDetailsService
    userDetailsService() {
      var userDetailsManager = new InMemoryUserDetailsManager();
      userDetailsManager.createUser(User.withUsername("admin")
                                        .password("{noop}pwdadmin")
                                        .roles("ADMIN")
                                        .build());
      return userDetailsManager;
  }
  
  @Bean
  public JwtAuthenticationFilter createFilter(UserDetailsService userDetailsService) {
	  return new JwtAuthenticationFilter(userDetailsService);
  }
}
