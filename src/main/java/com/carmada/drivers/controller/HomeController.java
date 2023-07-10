package com.carmada.drivers.controller;

import java.text.ParseException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
	
//	@Autowired
//	private PaymentService paymentService;


	@ExceptionHandler(Exception.class)
    public String handleError(Model model, Exception e) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }
	
	@GetMapping
	public String listAll(Model model) throws ParseException{
		
//		List<Payment> currentDatePayments = paymentService.findByCurrentDate();
//		
//		double totalRevenue = 0;
//		
//		for (Payment payment: currentDatePayments) {
//			totalRevenue += payment.getAmountBoundary();
//		}
//		model.addAttribute("totalRevenue", totalRevenue);
//		
//		model.addAttribute("currentDatePayments", currentDatePayments);
		
		return "login/home-page";
	}

}
