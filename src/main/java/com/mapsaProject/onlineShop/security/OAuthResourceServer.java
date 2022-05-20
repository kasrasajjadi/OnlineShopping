package com.mapsaProject.onlineShop.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Profile("security-dev")
@Configuration
public class OAuthResourceServer extends ResourceServerConfigurerAdapter {

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      .antMatchers("/api/**").authenticated()
      .antMatchers("/").permitAll()
      .antMatchers("/swagger-ui/**").permitAll();
  }
}