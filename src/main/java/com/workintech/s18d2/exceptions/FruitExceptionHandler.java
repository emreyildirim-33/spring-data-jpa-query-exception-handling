package com.workintech.s18d2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FruitExceptionHandler {

    // FruitException için hata yakalayıcı
    @ExceptionHandler
    public ResponseEntity<FruitErrorResponse> handleException(FruitException exception) {
        // Record'u parantez içinde tek seferde oluşturuyoruz
        FruitErrorResponse response = new FruitErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // VegetableException için hata yakalayıcı
    @ExceptionHandler
    public ResponseEntity<FruitErrorResponse> handleException(VegetableException exception) {
        FruitErrorResponse response = new FruitErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Geri kalan tüm genel hatalar için (Bad Request)
    @ExceptionHandler
    public ResponseEntity<FruitErrorResponse> handleException(Exception exception) {
        FruitErrorResponse response = new FruitErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}