package com.mastercard.evaluation.bin.range.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler {

  @ExceptionHandler(BinRangeInfoNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String handleBinRangeInfoNotFoundException(BinRangeInfoNotFoundException ex) {
    return ex.getMessage();
  }

}
