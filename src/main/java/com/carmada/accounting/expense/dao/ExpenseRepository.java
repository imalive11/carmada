package com.carmada.accounting.expense.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.carmada.accounting.expense.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
	
	public List<Expense> findAllByOrderByIdAsc();
	
	@Query("SELECT MAX(e.orderDate) FROM Expense e")
    public Date findLatestOrderDate();
	
	@Query("SELECT e FROM Expense e WHERE e.orderDate = :orderDate")
    List<Expense> findLatestExpense(Date orderDate);
	
	
	
}
