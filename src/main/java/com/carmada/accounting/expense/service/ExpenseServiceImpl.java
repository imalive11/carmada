package com.carmada.accounting.expense.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public void save(Expense expense) {
		this.expenseRepository.save(expense);
		
	}

	@Override
	public void delete(int id) {
		this.expenseRepository.deleteById(id);
		
	}



}
