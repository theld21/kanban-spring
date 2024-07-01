package com.ldt.api.security;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHelper {
  public static ResponseEntity<Object> responseMsg(String message, HttpStatus statusCode) {
    Map<String, Object> res = new HashMap<>();
    res.put("status", false);
    res.put("msg", message);

    return new ResponseEntity<>(res, statusCode);
  }
}
