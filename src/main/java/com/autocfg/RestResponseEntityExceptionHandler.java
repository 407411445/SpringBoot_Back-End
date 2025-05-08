package com.autocfg;


import com.example.blog.BlogServiceException;
import com.example.customer.CustomerServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

//    private static final List<Charset> ACCEPT_CHARSET = Arrays.asList(Charset.forName("UTF-8"));


    @ExceptionHandler(value = { CustomerServiceException.class })
    protected ResponseEntity<Object> handleServiceException(CustomerServiceException ex) {

        Map<String, Object> error = Map.of("error", ex.getMessage(), "type", ex.getExceptionType().name());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);

    }

    @ExceptionHandler(value = { BlogServiceException.class })
    protected ResponseEntity<Object> handleServiceException(BlogServiceException ex) {

        Map<String, Object> error = Map.of("error", ex.getMessage(), "type", ex.getExceptionType().name());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);


    }



}