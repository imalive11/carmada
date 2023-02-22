package com.carmada.payment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
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
	public String listAll(Model model){
		List<Payment> payments = paymentService.findAll();
		
		model.addAttribute("payments", payments);
		
		return "payments/list-payments";
	}
	
	@GetMapping("/search")
	public String findByDriver(@RequestParam("name") String name, Model model){

		List<Payment> payments = paymentService.findByDriver(name);
		
		if (payments.isEmpty() == true) {
			model.addAttribute("errorMessage", "Driver not found!");
			return "payments/list-payments";
		}

		model.addAttribute("payments", payments);
		
		return "payments/list-payments";
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
		payment.set();
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
