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
import org.springframework.web.bind.annotation.RequestParam;

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
									Model theModel) {
		
		Vehicle vehicle = vehicleService.findById(id);
		
		theModel.addAttribute("vehicle", vehicle);
		
		return "vehicles/vehicle-form";			
	}


	@GetMapping("/delete")
	public String delete(@RequestParam("vehicleId") int id) {
		
		vehicleService.delete(id);
		
		return "redirect:/vehicles/";
		
	}
	

}
