package com.bilal.employeeapp.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.bilal.employeeapp.model.Department;
import com.bilal.employeeapp.model.Employee;
import com.bilal.employeeapp.model.EmployeeDTO;
import com.bilal.employeeapp.service.EmployeeService;

public class EmployeeControllerTest {

	@InjectMocks
	private EmployeeController employeeController;

	@Mock
	private EmployeeService employeeService;

	@SuppressWarnings("deprecation")
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllEmployee() {

		// given
		List<Employee> employees = new ArrayList<>();
		employees.add(
				new Employee(1, "Bilal", Date.valueOf("1995-01-01"), 30, "bilal@gmail.com", 5000, new Department(2)));
		employees.add(new Employee(2, "Ali", Date.valueOf("1992-05-15"), 36, "ali@gmail.com", 6000, new Department(3)));

		when(employeeService.getAllEmployee()).thenReturn(ResponseEntity.ok(employees));

		ResponseEntity<List<Employee>> response = employeeController.getAllEmployee();

		assertNotNull(response);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(employees, response.getBody());
	}

	@Test
	public void testGetEmployeeById() {
		Employee employee = new Employee(1, "Bilal", Date.valueOf("1995-01-01"), 30, "bilal@gmail.com", 5000,
				new Department(2));

		when(employeeService.getEmployeeById(1)).thenReturn(ResponseEntity.ok(employee));

		ResponseEntity<Employee> result = employeeController.getEmployeeByID(1);

		assertEquals("Bilal", result.getBody().getEname());
		assertEquals(30, result.getBody().getEage());

	}

	@Test
	    public void testEmployeeNotExistById() {
	    	
	    	when(employeeService.getEmployeeById(1)).thenReturn(null);
	    	
	    	ResponseEntity<Employee> employee = employeeController.getEmployeeByID(1);
	    	
	    	assertEquals(null,employee);
	    	
	   
	   }

	@Test
	public void testEmployeeSearchByNameExist() {

		Employee employee = new Employee(1, "Bilal", Date.valueOf("1995-01-01"), 30, "bilal@gmail.com", 5000,
				new Department(2));

		List<Employee> employees = new ArrayList<>();
		employees.add(employee);

		when(employeeService.findByName("obaid")).thenReturn(ResponseEntity.ok(employees));

		ResponseEntity<List<Employee>> response = employeeController.searchEmployeesByName("obaid");

		assertNotNull(response);
		assertEquals(200, response.getStatusCodeValue());
		assertTrue(response.getBody().contains(employee));
	}

	@Test
	public void testAddEmployee() {

		EmployeeDTO employee = new EmployeeDTO(2, "Obaid", Date.valueOf("1995-01-01"), 30, "Obaid@gmail.com", 5000,
				new Department(1));

		when(employeeService.addEmployee(employee)).thenReturn(ResponseEntity.ok("success"));

		ResponseEntity<String> result = employeeController.addEmployee(employee);

		assertEquals(200, result.getStatusCodeValue());
	}

	@Test
	public void testUpdateEmployee() {

		EmployeeDTO updatedEmployeeDTO = new EmployeeDTO();
		updatedEmployeeDTO.setEname("Bilal");

		Employee updatedEmployee = new Employee();
		when(employeeService.updateEmployee(1, updatedEmployeeDTO)).thenReturn(updatedEmployee);

		Employee result = employeeController.updateEmployee(1, updatedEmployeeDTO);

		verify(employeeService, times(1)).updateEmployee(1, updatedEmployeeDTO);

		assertEquals(updatedEmployee, result);
	}

	@Test
	public void testDeleteEmployee() {

		Integer employeeIdToDelete = 1;

		when(employeeService.deleteEmployee(employeeIdToDelete)).thenReturn(ResponseEntity.ok("success"));

		ResponseEntity<String> result = employeeController.deleteEmployee(employeeIdToDelete);

		assertEquals(200, result.getStatusCodeValue());

		verify(employeeService).deleteEmployee(employeeIdToDelete);

	}
}
