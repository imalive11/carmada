//package com.carmada.drivers.exception;
//
//import java.io.PrintWriter;
//import java.io.StringWriter;
//
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class CustomErrorController implements ErrorController {
//
//    @RequestMapping("/error")
//    public String handleError(Exception e, Model model) {
//    	StringWriter sw = new StringWriter();
//        PrintWriter pw = new PrintWriter(sw);
//        e.printStackTrace(pw);
//    	model.addAttribute("errorCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
//        model.addAttribute("errorMessage", e.getMessage());
//        model.addAttribute("stackTrace", sw.toString());
//        return "error";
//    }
//    
//    
//
//    public String getErrorPath() {
//        return "/error";
//    }
//}
//
//
//
//
