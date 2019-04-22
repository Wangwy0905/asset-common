package com.weshare.asset.common.advice;

import com.weshare.asset.common.model.Response;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response bindExceptionHandler(MethodArgumentNotValidException ex) {
        return new Response(Response.INNER_ERROR, ex.getBindingResult().getFieldError().getField() + ex.getBindingResult().getFieldError().getDefaultMessage());
    }
}