package com.carmada.drivers.controller;

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
import com.carmada.drivers.entity.DriverPersonalInfo;
import com.carmada.drivers.service.DriverService;
import com.carmada.incident.entity.Incident;
import com.carmada.incident.service.IncidentService;
import com.carmada.payment.entity.Payment;
import com.carmada.payment.service.PaymentService;

@Controller
@RequestMapping("drivers")
public class DriverController {
	
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
	
//	@ExceptionHandler(Exception.class)
//    public String handleError(Model model, Exception e) {
//        model.addAttribute("errorMessage", e.getMessage());
//        return "redirect:error";
//    }
//	
	@GetMapping
	public String listAllActive(Model model){
		List<Driver> drivers;
		try {
			drivers = driverService.findAllActiveDrivers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "drivers/home-page";
		}
		
		model.addAttribute("drivers", drivers);

		return "drivers/driver-list";
	}
	
	@GetMapping("/all")
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
		return "drivers/driver-list-all";
	}
	
	@GetMapping("/search")
	public String findByNameByStatus(@RequestParam("name") String name, Model model){

		List<Driver> drivers = driverService.findByNameByStatus(name);
		
		if (drivers.isEmpty() == true) {
			model.addAttribute("errorMessage", "Driver not found!");
			return "drivers/driver-list";
		}
		
		model.addAttribute("drivers", drivers);
		
		return "drivers/driver-list";
	}
	
	@GetMapping("/searchall")
	public String findByName(@RequestParam("name") String name, Model model){

		List<Driver> drivers = driverService.findByName(name);
		
		if (drivers.isEmpty() == true) {
			model.addAttribute("errorMessage", "Driver not found!");
			return "drivers/driver-list-all";
		}
		
		model.addAttribute("drivers", drivers);
		
		return "drivers/driver-list-all";
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
	
	@GetMapping("/deactivate")
	public String deactivate(@RequestParam("driverId") int theId) {
		
		
		driverService.deactivate(theId);
		
		return "redirect:/drivers/";
		
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("driverId") int theId,
									Model model) {
		
		
		Driver driver = driverService.findById(theId);
		
		model.addAttribute("driver", driver);
		
		return "drivers/driver-form";			
	}
	
	@GetMapping("/profile")
	public String viewProfile(@RequestParam("driverId") int driverId,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10000") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(required = false, value = "startDate") String startDate,
            @RequestParam(required = false, value = "endDate") String endDate,
									Model model) {
		
		Driver driver = driverService.findById(driverId);
		
		model.addAttribute("driver", driver);
		
		Date firstDate = paymentService.findFirstTravelDate();
		Date latestDate = paymentService.findLatestTravelDate();
		
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
            
            page = paymentService.findAllByIdAndTravelDateBetween(driverId, parsedStartDate, parsedEndDate, pageable);
            incidents = incidentService.findAllByDriverAndDate(driverId, parsedStartDate, parsedEndDate);
            
        } else {
        	
        	page = paymentService.findByDriverId(pageable, driverId);
        	incidents = incidentService.findByDriverId(driverId);
        	
        }
		
        
        model.addAttribute("page", page);
        
        model.addAttribute("incidents", incidents);
        

        model.addAttribute("firstDate", firstDate);
        model.addAttribute("latestDate", latestDate);
		
		// send over to our form
		return "drivers/driver-profile";			
	}
	

	

}
