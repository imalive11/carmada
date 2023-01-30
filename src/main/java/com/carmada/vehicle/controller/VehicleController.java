package com.carmada.vehicle.controller;

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

import com.carmada.vehicle.entity.Vehicle;
import com.carmada.vehicle.service.VehicleService;

@Controller
@RequestMapping("vehicles")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@InitBinder
	public void initBinder(WebDataBinder databinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		databinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/")
	public String listAll(Model model){
		List<Vehicle> vehicles = vehicleService.findAll();
	
		
		model.addAttribute("vehicles", vehicles);
		
		return "vehicles/list-vehicles";
	}
	
	@GetMapping("/addVehicle")
	public String addVehicle(Model model) {

		Vehicle vehicle = new Vehicle();
		
		model.addAttribute("vehicle", vehicle);
		
		return "vehicles/vehicle-form";
	}
	
	@PostMapping("/saveVehicle")
	public String saveVehicle(@Valid @ModelAttribute("vehicle") Vehicle vehicle, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "vehicles/vehicle-form";
		}
		vehicleService.save(vehicle);
		
		return "redirect:/vehicles/";
	}
	

	

}
