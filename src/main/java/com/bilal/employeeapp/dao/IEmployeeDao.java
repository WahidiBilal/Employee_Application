package com.bilal.employeeapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bilal.employeeapp.model.Employee;

public interface IEmployeeDao extends JpaRepository<Employee,Integer> {

	@Query("SELECT e FROM Employee e WHERE e.ename LIKE %?1%")
	List<Employee> findByName(String name);



}
