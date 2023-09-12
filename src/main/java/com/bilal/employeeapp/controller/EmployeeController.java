package com.bilal.employeeapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bilal.employeeapp.model.Employee;
import com.bilal.employeeapp.service.EmployeeService;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployee(){
		return employeeService.getAllEmployee();
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeByID (@PathVariable Integer id) {
		return employeeService.getEmployeeById(id);
	}
	
	@GetMapping("/employees/search")
    public ResponseEntity<List<Employee>> searchEmployeesByName(@RequestParam String name) {
        return employeeService.findByName(name);
    }
	
	@PostMapping("/employee/add")
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	
	}
	
	@PutMapping("/employee/update/{id}")
	public void updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
		 employeeService.updateEmployee(id, employee);
		
		
	}
	
	@DeleteMapping("/employee/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
		return employeeService.deleteEmployee(id);
		
	}
	
	
}
