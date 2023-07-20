package com.carmada.drivers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.carmada.incident.entity.Incident;
import com.carmada.incident.service.IncidentService;
import com.carmada.payment.entity.Payment;
import com.carmada.payment.service.PaymentService;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private IncidentService incidentService;


	@ExceptionHandler(Exception.class)
    public String handleError(Model model, Exception e) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }
	
	@GetMapping
	public String listAll(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "60") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            Model model){
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy));
		Page<Payment> page;
        page = paymentService.findLatestDayPayment(pageable);
        List<Incident> incidents = incidentService.findIncidentsForCurrentMonth();
        
        model.addAttribute("incidents", incidents);
        model.addAttribute("page", page);
		
		return "login/home-page";
	}
	
	

}
