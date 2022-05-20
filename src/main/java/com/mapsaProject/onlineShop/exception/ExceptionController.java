package com.mapsaProject.onlineShop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionController {
  public ResponseEntity<Object> NotFound(NotFoundException ex){
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }
}
