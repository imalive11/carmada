package com.carmada.accounting.expense.controller;

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

import com.carmada.accounting.expense.entity.Expense;
import com.carmada.accounting.expense.service.ExpenseService;

@Controller
@RequestMapping("expenses")
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	
	@InitBinder
	public void initBinder(WebDataBinder databinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		databinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping
	public String expenseDashboard(Model model){
		List<Expense> expenses = expenseService.findAll();
		
		model.addAttribute("expenses", expenses);
		return "expenses/expense-list";
	}
	
//	@GetMapping("/search")
//	public String findByPlate(@RequestParam("name") String name, Model model){
//
//		List<Vehicle> vehicles = vehicleService.findByPlate(name);
//		
//		if (vehicles.isEmpty() == true) {
//			model.addAttribute("errorMessage", "Driver not found!");
//			return "vehicles/vehicle-list";
//		}
//
//		model.addAttribute("vehicles", vehicles);
//		
//		return "vehicles/vehicle-list";
//	}
	
	@GetMapping("/add")
	public String addExpense(Model model) {

		Expense expense = new Expense();
		
		model.addAttribute("expense", expense);
		
		return "expenses/expense-form";
	}
	
	@PostMapping("/save")
	public String saveVehicle(@Valid @ModelAttribute("expense") Expense expense, BindingResult bindingResult) {
		
		
		
		if (bindingResult.hasErrors()) {
			return "expenses/expense-form";
		}
		
		expenseService.save(expense);
		
		return "redirect:/expenses/";
	}
	
	@GetMapping("/update")
	public String showFormForUpdate(@RequestParam("vehicleId") int id,
									Model model) {
		
		Expense expense = expenseService.findById(id);
		
		model.addAttribute("expense", expense);
		
		return "expenses/expense-form";			
	}


	@GetMapping("/delete")
	public String delete(@RequestParam("vehicleId") int id) {
		
		expenseService.delete(id);
		
		return "redirect:/expenses/";
		
	}
	

	

}
