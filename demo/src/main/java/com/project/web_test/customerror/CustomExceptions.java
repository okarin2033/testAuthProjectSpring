package com.project.web_test.customerror;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccessError.class)
    protected ResponseEntity<ExceptionClass> handleAccessError() {
        return new ResponseEntity<>(new ExceptionClass("You should be authorised to do this"), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthError.class)
    protected ResponseEntity<ExceptionClass> handleAuthError() {
        return new ResponseEntity<>(new ExceptionClass("Wrong name or password"), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BannedError.class)
    protected ResponseEntity<ExceptionClass> handleBannedError() {
        return new ResponseEntity<>(new ExceptionClass("More than 10 attempts of login"), HttpStatus.TOO_MANY_REQUESTS);
    }

    @ExceptionHandler(NotUniqueNameError.class)
    protected ResponseEntity<ExceptionClass> handleNotUniqueNameError() {
        return new ResponseEntity<>(new ExceptionClass("The name is not unique"), HttpStatus.FORBIDDEN);
    }

    @Data
    @AllArgsConstructor
    private static class ExceptionClass{
        private String message;
    }
}
