package com.example.deliverywebapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

public class DeliveryNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(DeliveryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> exceptionHandler(DeliveryNotFoundException exception){

        Map<String,String> errorMap=new HashMap<String,String>();
        errorMap.put("errorMessage",exception.getMessage());
        return errorMap;
    }
}
