package com.carmada.vehicle.controller;

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
import com.carmada.incident.entity.Incident;
import com.carmada.incident.service.IncidentService;
import com.carmada.payment.entity.Payment;
import com.carmada.payment.service.PaymentService;
import com.carmada.vehicle.entity.Vehicle;
import com.carmada.vehicle.service.VehicleService;

@Controller
@RequestMapping("vehicles")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private IncidentService incidentService;
	
	@InitBinder
	public void initBinder(WebDataBinder databinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		databinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping
	public String listAll(Model model){
		List<Vehicle> vehicles = vehicleService.findAll();
	
		
		model.addAttribute("vehicles", vehicles);
		
		return "vehicles/vehicle-list";
	}
	
	@GetMapping("/search")
	public String findByPlate(@RequestParam("name") String name, Model model){

		List<Vehicle> vehicles = vehicleService.findByPlate(name);
		
		if (vehicles.isEmpty() == true) {
			model.addAttribute("errorMessage", "Driver not found!");
			return "vehicles/vehicle-list";
		}

		model.addAttribute("vehicles", vehicles);
		
		return "vehicles/vehicle-list";
	}
	
	@GetMapping("/add")
	public String addVehicle(Model model) {

		Vehicle vehicle = new Vehicle();
		
		model.addAttribute("vehicle", vehicle);
		
		List<Driver> drivers = driverService.findAll();
		
		model.addAttribute("drivers", drivers);
		
		return "vehicles/vehicle-form";
	}
	
	@PostMapping("/save")
	public String saveVehicle(@Valid @ModelAttribute("vehicle") Vehicle vehicle, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "vehicles/vehicle-form";
		}
		vehicleService.save(vehicle);
		
		return "redirect:/vehicles/";
	}
	
	@GetMapping("/update")
	public String showFormForUpdate(@RequestParam("vehicleId") int id,
									Model model) {
		
		Vehicle vehicle = vehicleService.findById(id);
		
		List<Driver> drivers = driverService.findAll();
		
		model.addAttribute("drivers", drivers);
		
		model.addAttribute("vehicle", vehicle);
		
		
		
		return "vehicles/vehicle-form";			
	}


	@GetMapping("/delete")
	public String delete(@RequestParam("vehicleId") int id) {
		
		vehicleService.delete(id);
		
		return "redirect:/vehicles/";
		
	}
	
	@GetMapping("/profile")
	public String viewProfile(@RequestParam("vehicleId") int vehicleId,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "100") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(required = false, value = "startDate") String startDate,
            @RequestParam(required = false, value = "endDate") String endDate,
									Model model) {
		
		Vehicle vehicle = vehicleService.findById(vehicleId);
		
		model.addAttribute("vehicle", vehicle);
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy));
		Page<Payment> page;
		List<Incident> incidents = null;
		
		if (startDate != null && endDate != null) {
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedStartDate = null;
            Date parsedEndDate = null;
            
            try {
            	parsedStartDate = formatter.parse(startDate);
            	
            	parsedEndDate = formatter.parse(endDate);
	        } catch (ParseException e) {
	                e.printStackTrace();
	        }
            
            page = paymentService.findAllByVehicleIdAndDate(pageable, vehicleId, parsedStartDate, parsedEndDate);
            incidents = incidentService.findAllByVehicleAndDate(vehicleId, parsedStartDate, parsedEndDate);
            
        } else {
        	
        	page = paymentService.findByVehicleId(pageable, vehicleId);
        	incidents = incidentService.findByVehicleId(vehicleId);
        	
        }
		
        
        model.addAttribute("page", page);
        
        model.addAttribute("incidents", incidents);
		
		// send over to our form
		return "vehicles/vehicle-profile";			
	}
	

}
