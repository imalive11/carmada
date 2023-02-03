package com.carmada.drivers.controller;

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
import com.carmada.drivers.entity.DriverPersonalInfo;
import com.carmada.drivers.service.DriverService;

@Controller
@RequestMapping("drivers")
public class DriverController {
	
	private DriverService driverService;
	
	@Autowired
	public DriverController(DriverService driverService) {
		this.driverService = driverService;
	}

	@InitBinder
	public void initBinder(WebDataBinder databinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		databinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
//	@ExceptionHandler(Exception.class)
//    public String handleError(Model model, Exception e) {
//        model.addAttribute("errorMessage", e.getMessage());
//        return "redirect:error";
//    }
//	
	@GetMapping
	public String listAll(Model model){
		List<Driver> drivers;
		try {
			drivers = driverService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "drivers/home-page";
		}
		
		model.addAttribute("drivers", drivers);
		return "drivers/list-drivers";
	}
	
	@GetMapping("/search")
	public String findByName(@RequestParam("name") String name, Model model){

		List<Driver> drivers = driverService.findByName(name);
		
		if (drivers.isEmpty() == true) {
			model.addAttribute("errorMessage", "Driver not found!");
			return "drivers/list-drivers";
		}
		
		model.addAttribute("drivers", drivers);
		
		return "drivers/list-drivers";
	}
	
	@GetMapping("/add")
	public String addDriver(Model model) {
		
		Driver driver = new Driver();
		DriverPersonalInfo driverPersonalInfo = new DriverPersonalInfo();
		
		driver.setDriverPersonalInfo(driverPersonalInfo);
		
		
		model.addAttribute("driver", driver);
		
		return "drivers/driver-form";
	}
	
	@PostMapping("/save")
	public String saveDriver(@Valid @ModelAttribute("driver") Driver driver, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "drivers/driver-form";
		}
		driverService.save(driver);
		
		return "redirect:/drivers/";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("driverId") int theId) {
		
		driverService.delete(theId);
		
		return "redirect:/drivers/";
		
	}
	
	@GetMapping("/update")
	public String showFormForUpdate(@RequestParam("driverId") int theId,
									Model model) {
		
		Driver driver = driverService.findById(theId);
		
		model.addAttribute("driver", driver);
		
		// send over to our form
		return "drivers/driver-form";			
	}
	

	

}
