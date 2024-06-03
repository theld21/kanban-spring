package com.ldt.api.util;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;

import com.ldt.api.entity.User;

public class SecurityUtil {

  public static User getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated()) {
      Object principal = authentication.getPrincipal();
      if (principal instanceof User) {
        return (User) principal;
      }
    }
    return null;
  }

  public static void checkAuthor(Number authId) {
    User user = SecurityUtil.getCurrentUser();
    if (!authId.equals(user.getId())) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied");
    }
  }
}