package com.carmada.accounting.expense.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carmada.accounting.expense.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
	
	public List<Expense> findAllByOrderByIdAsc();
	
}
