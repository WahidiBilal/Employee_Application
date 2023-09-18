package com.bilal.employeeapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bilal.employeeapp.dao.IEmployeeDao;
import com.bilal.employeeapp.exceptions.NotFoundException;
import com.bilal.employeeapp.model.Employee;
import com.bilal.employeeapp.model.EmployeeDTO;

@Service
public class EmployeeService {

	@Autowired
	public IEmployeeDao employeeDao;

	public ResponseEntity<List<Employee>> getAllEmployee() {

		List<Employee> employeeList;

		employeeList = employeeDao.findAll();

		if (employeeList != null) {
			return new ResponseEntity<>(employeeList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
		}

	}

	private Employee convertToEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		employee.setEname(employeeDTO.getEname());
		employee.setEdob(employeeDTO.getEdob());
		employee.setEage(employeeDTO.getEage());
		employee.setEmail(employeeDTO.getEmail());
		employee.setEsalary(employeeDTO.getEsalary());

		return employee;
	}

	public ResponseEntity<String> addEmployee(EmployeeDTO employeeDTO) {

		Employee employee = convertToEmployee(employeeDTO);

		Employee savedEmployee = employeeDao.save(employee);

		if (savedEmployee == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Employee added fail");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("Employee added successfully");

		}
	}

	public Employee updateEmployee(Integer id, EmployeeDTO employeeDTO) {

		Employee existingEmployee = employeeDao.findById(id).orElse(null);

		if (existingEmployee == null) {
			throw new NotFoundException("Employee not found for ID::" + id);
		}

		existingEmployee.setEname(employeeDTO.getEname());
		existingEmployee.setEage(employeeDTO.getEage());
		existingEmployee.setEdob(employeeDTO.getEdob());
		existingEmployee.setEmail(employeeDTO.getEmail());
		existingEmployee.setEsalary(employeeDTO.getEsalary());
		existingEmployee.setEdepartment(employeeDTO.getEdepartment());

		return employeeDao.save(existingEmployee);

	}

	public ResponseEntity<String> deleteEmployee(Integer id) {
		try {
			employeeDao.deleteById(id);
			return ResponseEntity.ok("Employee deleted successfully");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found or unable to delete");
		}
	}

	public ResponseEntity<Employee> getEmployeeById(Integer id) {

		Employee employee = employeeDao.findById(id).orElse(null);

		if (employee == null) {
			return new ResponseEntity<Employee>(employee, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}

	}

	public ResponseEntity<List<Employee>> findByName(String name) {

		List<Employee> employeeList = employeeDao.findByName(name);

		if (employeeList != null) {
			return new ResponseEntity<>(employeeList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
		}
	}

}
