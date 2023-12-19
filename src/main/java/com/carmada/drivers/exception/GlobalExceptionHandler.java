package com.carmada.drivers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
//	@ExceptionHandler(NullPointerException.class)
//    public String handleError(Model model, NullPointerException e) {
//        model.addAttribute("errorCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
//        model.addAttribute("errorMessage", e.getMessage());
//        return "error";
//    }
}
