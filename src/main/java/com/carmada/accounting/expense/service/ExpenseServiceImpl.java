package com.carmada.accounting.expense.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carmada.accounting.expense.dao.ExpenseRepository;
import com.carmada.accounting.expense.entity.Expense;

@Service
public class ExpenseServiceImpl implements ExpenseService {
	
	@Autowired
	private ExpenseRepository expenseRepository;

	@Override
	public List<Expense> findAll() {
		return expenseRepository.findAllByOrderByIdAsc();
	}

	@Override
	public Expense findById(int id) {
		
		Optional<Expense> result = this.expenseRepository.findById(id);
		
		Expense expense = null;
		
		if(result.isPresent() == true) {
			expense = result.get();
		} else {
			throw new RuntimeException("Did not find Vehicle by id: " + expense);
		}
		return expense;
	}
	
	@Override
	public Date findLatestOrderedDate() {
		// TODO Auto-generated method stub
		return expenseRepository.findLatestOrderDate();
	}
	
	@Override
	@Transactional
	public List<Expense> findLatestDayOrderDate() throws ParseException {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now().minusDays(1);
		
		Date dateToday = new SimpleDateFormat("yyyy-MM-dd").parse(dtf.format(now));
		
		
//		Date latestTravelDate = this.findLatestOrderedDate();
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(latestTravelDate);
//
//		// Subtract one day from the Calendar object
//		cal.add(Calendar.DAY_OF_MONTH, -1);
//
//		// Get the new date from the Calendar object
//		Date yesterday = cal.getTime();

		return expenseRepository.findLatestExpense(dateToday);
	}

	@Override
	public void save(Expense expense) {
		this.expenseRepository.save(expense);
		
	}

	@Override
	public void delete(int id) {
		this.expenseRepository.deleteById(id);
		
	}



}
