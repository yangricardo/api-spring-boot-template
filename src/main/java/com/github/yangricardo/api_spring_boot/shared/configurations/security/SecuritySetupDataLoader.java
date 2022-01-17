package com.github.yangricardo.api_spring_boot.shared.configurations.security;

import com.github.yangricardo.api_spring_boot.modules.users.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SecuritySetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

  boolean alreadySetup = false;

  @Autowired
  IUserService userService;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    if(alreadySetup) return;

    this.userService.createRoleIfNotFound("ROLE_ADMIN");
    this.userService.createRoleIfNotFound("ROLE_USER");

    alreadySetup = true;
  }

}
