package com.carmada.accounting.expense.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.carmada.accounting.expense.entity.Expense;

public interface ExpenseService {

	public List<Expense> findAll();
	
	public Expense findById(int id);
	
	public Date findLatestOrderedDate();
	
	public List<Expense> findLatestDayOrderDate() throws ParseException;
	
	public void save(Expense expense);
	
	public void delete(int id);



}
