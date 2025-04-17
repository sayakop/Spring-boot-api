package com.think.rest_demo.exception.VendorException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VendorExceptionController {

    @ExceptionHandler(value = {NotFoundVendorException.class})
     public ResponseEntity<Object> handleNotFoundVendorException(NotFoundVendorException notFoundVendorException)
     {
         VendorException vendorException = new VendorException(
                 notFoundVendorException.getMessage(),
                 notFoundVendorException.getCause(),
                 HttpStatus.NOT_FOUND
         );
         return new ResponseEntity<>(vendorException, HttpStatus.NOT_FOUND);
     }
     
}
