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

import com.bilal.employeeapp.dto.EmployeeDTO;
import com.bilal.employeeapp.model.Employee;
import com.bilal.employeeapp.service.EmployeeService;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployee(){
		return employeeService.getAllEmployee();
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeByID (@PathVariable Integer id) {
		return employeeService.getEmployeeById(id);
	}
	
	@GetMapping("/employees/search")
    public ResponseEntity<List<EmployeeDTO>> searchEmployeesByName(@RequestParam String name) {
        return employeeService.findByName(name);
    }
	
	@PostMapping("/employee/add")
	public ResponseEntity<String> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
		return employeeService.addEmployee(employeeDTO);
	
	}
	
	@PutMapping("/employee/update/{id}")
	public void updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDTO employeeDTO) {
		 employeeService.updateEmployee(id, employeeDTO);
		
		
	}
	
	@DeleteMapping("/employee/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
		return employeeService.deleteEmployee(id);
		
	}
	
	
	
	
	
}