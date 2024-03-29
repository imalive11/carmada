package com.carmada.incident.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.carmada.incident.entity.Incident;
import com.carmada.incident.service.IncidentService;
import com.carmada.vehicle.entity.Vehicle;
import com.carmada.vehicle.service.VehicleService;

@Controller
@RequestMapping("incidents")
public class IncidentController {
	
	@Autowired
	private IncidentService incidentService;
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@InitBinder
	public void initBinder(WebDataBinder databinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		databinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping
	public String listAll(Model model){
		List<Incident> incidents = incidentService.findAll();
	
		model.addAttribute("incidents", incidents);
		
		return "incidents/incident-list";
	}
	
	@GetMapping("/search")
	public String searchIncident(
            @RequestParam(required = false) String name,
            @RequestParam(required = false, value = "startDate") String startDate,
            @RequestParam(required = false, value = "endDate") String endDate,
            Model model){
		
		List<Incident> incidents = null;
		Date parsedStartDate = null;
        Date parsedEndDate = null;
		
		if (name != null && !name.isEmpty() && startDate != null && endDate != null) {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            
            try {
            	parsedStartDate = formatter.parse(startDate);
            	parsedEndDate = formatter.parse(endDate);
	        } catch (ParseException e) {
	                e.printStackTrace();
	        }
        	
        	incidents = incidentService.findAllByDriverNameAndDate(name, parsedStartDate, parsedEndDate);
        	
        } else if (name != null && !name.isEmpty()) {
        	
        	incidents = incidentService.findByDriver(name);
        	
        } else if (startDate != null && endDate != null) {
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            
            try {
            	parsedStartDate = formatter.parse(startDate);
            	parsedEndDate = formatter.parse(endDate);
	        } catch (ParseException e) {
	                e.printStackTrace();
	        }
            
            incidents = incidentService.findByIncidentDateBetween(parsedStartDate, parsedEndDate);
            
        } else {
        	
        	incidents = incidentService.findAll();
        	
        }
		
		if (incidents != null) {
			 model.addAttribute("incidents", incidents);
			 model.addAttribute("firstDate", parsedStartDate);
		     model.addAttribute("latestDate", parsedEndDate);
		}
       
		return "incidents/incident-list";
	}
	
	
	@GetMapping("/add")
	public String addVehicle(Model model) {

		listDriverAndVehicleForDropdown(model);
		
		Incident incident = new Incident();
		
		model.addAttribute("incident", incident);
		
		return "incidents/incident-form";
	}
	
	@PostMapping("/save")
	public String saveVehicle(@Valid @ModelAttribute("incident") Incident incident, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "incidents/incident-form";
		}
		incidentService.save(incident);
		
		return "redirect:/incidents/";
	}
	
	@GetMapping("/update")
	public String showFormForUpdate(@RequestParam("incidentId") int id,
									Model model) {
		
		listDriverAndVehicleForDropdown(model);
		
		Incident incident = incidentService.findById(id);
		
		model.addAttribute("incident", incident);
		
		return "incidents/incident-form";			
	}


	@GetMapping("/delete")
	public String delete(@RequestParam("incidentId") int id) {
		
		incidentService.delete(id);
		
		return "redirect:/incidents/";
		
	}
	
	private void listDriverAndVehicleForDropdown(Model model) {

		List<Driver> drivers = driverService.findAllActiveDrivers();
		
		model.addAttribute("drivers", drivers);
		
		List<Vehicle> vehicles = vehicleService.findAll();
		
		model.addAttribute("vehicles", vehicles);
	}
	

}
