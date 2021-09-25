package com.example.nmaroulis_backend.models.ad;

import com.example.nmaroulis_backend.models.ad.AdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class AdNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(AdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String adNotFoundHandler(AdNotFoundException ex) {
        return ex.getMessage();
    }

}
