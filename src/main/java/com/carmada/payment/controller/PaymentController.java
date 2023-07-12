package com.carmada.payment.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.carmada.drivers.entity.Driver;
import com.carmada.drivers.service.DriverService;
import com.carmada.payment.entity.Payment;
import com.carmada.payment.service.PaymentService;
import com.carmada.vehicle.entity.Vehicle;
import com.carmada.vehicle.service.VehicleService;

@Controller
@RequestMapping("payments")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private DriverService driverService;
	
	@InitBinder
	public void initBinder(WebDataBinder databinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		databinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
//	@ExceptionHandler(Exception.class)
//    public String handleError(Model model, Exception e) {
//        model.addAttribute("errorMessage", e.getMessage());
//        return "error";
//    }
	
	@GetMapping
	public String listAll(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "60") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(required = false) String name,
            @RequestParam(required = false, value = "inputDate") String date,
            Model model){
		
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy));
		Page<Payment> page;
        page = paymentService.findLatestDayPayment(pageable);
        model.addAttribute("page", page);
		
		return "payments/payment-list";
	}
	
	@GetMapping("/search")
	public String searchPayment( @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "100") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(required = false) String name,
            @RequestParam(required = false, value = "inputDate") String date,
            Model model){
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy));

        Page<Payment> page;
        
        if (name != null && !name.isEmpty() && date != null ) {
        	
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = null;
            
            try {
            	parsedDate = formatter.parse(date);
	        } catch (ParseException e) {
	                e.printStackTrace();
	        }
            page = paymentService.findByDriverAndTravelDate(pageable, name, parsedDate);
            
            if (page.hasContent() == false) {
    			model.addAttribute("errorMessage", "No Driver found!");
    		}
            
        } else if (name != null && !name.isEmpty()) {
        	
            page = paymentService.findByDriver(pageable, name);
            
            if (page.hasContent() == false) {
    			model.addAttribute("errorMessage", "No Driver found!");
    		}
            
        } else if (date != null ) {
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = null;
            
            try {
            	parsedDate = formatter.parse(date);
	        } catch (ParseException e) {
	                e.printStackTrace();
	        }
            
            page = paymentService.findAllByDate(pageable, parsedDate, parsedDate);
            
        } else {
        	
            page = paymentService.findLatestDayPayment(pageable);
        }
		
        model.addAttribute("page", page);
		return "payments/payment-list";
	}
	
	@GetMapping("/late")
	public String listLatePayment( @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "100") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            Model model){
		
		String remarks = "late";
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy));
		
		Page<Payment> page = paymentService.searchByRemarksLike(pageable, remarks);
		
		model.addAttribute("page", page);
		
		return "payments/late-payment-list";
	}
	

	@GetMapping("/add")
	public String addPayment(Model model) {
		
		listDriverAndVehicleForDropdown(model);
		
		Payment payment = new Payment();
		
		model.addAttribute("payment", payment);
		
		
		return "payments/payment-form";
	}
	
	@PostMapping("/save")
	public String savePayment(@Valid @ModelAttribute("payment") Payment payment, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			
			listDriverAndVehicleForDropdown(model);
			
			return "payments/payment-form";
		}
		payment.setFullName();
		paymentService.save(payment);
		
		return "redirect:/payments/";
	}
	
	@GetMapping("/update")
	public String updatePayment(@RequestParam("paymentId") int id,
									Model model) {
		
		listDriverAndVehicleForDropdown(model);
		
		Payment payment = paymentService.findById(id);
		
		model.addAttribute("payment", payment);
		
		return "payments/payment-form";			
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("paymentId") int id) {
		
		paymentService.delete(id);
		
		return "redirect:/payments/";
		
	}
	
	
	private void listDriverAndVehicleForDropdown(Model model) {

		List<Driver> drivers = driverService.findAll();
		
		model.addAttribute("drivers", drivers);
		
		List<Vehicle> vehicles = vehicleService.findAll();
		
		model.addAttribute("vehicles", vehicles);
	}
	

}
