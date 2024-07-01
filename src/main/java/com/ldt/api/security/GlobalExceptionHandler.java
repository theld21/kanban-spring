package com.ldt.api.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleAllExceptions(ResponseStatusException ex, WebRequest request) {
    Map<String, Object> res = new HashMap<>();
    res.put("status", false);
    res.put("msg", ex.getReason());

    return new ResponseEntity<>(res, ex.getStatusCode());
  }
}
