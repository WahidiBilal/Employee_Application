package com.bilal.employeeapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.bilal.employeeapp.dao.IEmployeeDao;
import com.bilal.employeeapp.exceptions.NotFoundException;
import com.bilal.employeeapp.model.Department;
import com.bilal.employeeapp.model.Employee;
import com.bilal.employeeapp.model.EmployeeDTO;

@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeService employeeService;

	@Mock
	private IEmployeeDao iemployeeDao;

	@SuppressWarnings("deprecation")
	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void testGetAllEmployeeSuccess() {
		List<Employee> employees = new ArrayList<>();
		employees.add(
				new Employee(1, "Zakir", Date.valueOf("1984-01-01"), 32, "Zakir@example.com", 6000, new Department(2)));

		when(iemployeeDao.findAll()).thenReturn(employees);

		ResponseEntity<List<Employee>> response = employeeService.getAllEmployee();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(employees, response.getBody());

		verify(iemployeeDao, times(1)).findAll();
	}

	@Test
	public void testGetAllEmployeeNotFound() {
	    when(iemployeeDao.findAll()).thenReturn(null);

	    ResponseEntity<List<Employee>> response = employeeService.getAllEmployee();

	    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	    assertNotNull(response.getBody());
	    assertEquals(0, response.getBody().size());
	}

	@Test
	public void testGetEmployeeById_Success() {

		Employee employee = new Employee(1, "Bilal", Date.valueOf("1995-01-01"), 30, "bilal@gmail.com", 5000,
				new Department(2));

		when(iemployeeDao.findById(1)).thenReturn(Optional.of(employee));

		ResponseEntity<Employee> result = employeeService.getEmployeeById(1);

		assertEquals(200, result.getStatusCodeValue());
		assertEquals(employee, result.getBody());

		verify(iemployeeDao, times(1)).findById(1);
	}

	@Test
	public void testGetEmployeeById_NotFound() {
		Integer idToSearch = 1;

		when(iemployeeDao.findById(idToSearch)).thenReturn(Optional.empty());

		ResponseEntity<Employee> response = employeeService.getEmployeeById(idToSearch);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(null, response.getBody());

		verify(iemployeeDao, times(1)).findById(idToSearch);
	}

	@Test
	public void testFindByNameFound() {

		Employee employee = new Employee(1, "Zakir", Date.valueOf("1984-01-01"), 32, "Zakir@example.com", 6000,
				new Department(2));

		List<Employee> foundEmployees = new ArrayList<>();
		foundEmployees.add(employee);

		when(iemployeeDao.findByName("Zakir")).thenReturn(foundEmployees);

		ResponseEntity<List<Employee>> response = employeeService.findByName("Zakir");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(foundEmployees, response.getBody());

		verify(iemployeeDao, times(1)).findByName("Zakir");
	}

	@Test
	public void testFindByNameNotFound() {
		String nameToSearch = "NonExistentName";

		when(iemployeeDao.findByName(nameToSearch)).thenReturn(null);

		ResponseEntity<List<Employee>> response = employeeService.findByName(nameToSearch);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(0, response.getBody().size());
	}

	@Test
	public void testAddEmployeeSuccess() {

		IEmployeeDao employeeDao = mock(IEmployeeDao.class);
		EmployeeService employeeService = new EmployeeService();
		employeeService.employeeDao = employeeDao;

		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEname("John");

		Employee savedEmployee = new Employee();
		savedEmployee.setEid(1);

		when(employeeDao.save(any(Employee.class))).thenReturn(savedEmployee);

		ResponseEntity<String> response = employeeService.addEmployee(employeeDTO);

		verify(employeeDao).save(any(Employee.class));

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Employee added successfully", response.getBody());
	}

	@Test
	public void testAddEmployeeFailure() {

		EmployeeDTO employeeDTO = new EmployeeDTO();

		when(employeeService.addEmployee(employeeDTO))
				.thenReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Employee added fail"));

		ResponseEntity<String> response = employeeService.addEmployee(employeeDTO);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Employee added fail", response.getBody());
	}

	@Test
	public void testUpdateEmployeeSuccess() {

		EmployeeDTO existingEmployeeDTO = new EmployeeDTO(1, "Zakir", Date.valueOf("1984-01-01"), 32,
				"Zakir@example.com", 6000, new Department(2));
		EmployeeDTO updatedEmployeeDTO = new EmployeeDTO(1, "Zakir", Date.valueOf("1984-01-01"), 32,
				"Zakir@example.com", 6000, new Department(2));

		Employee existingEmployee = new Employee(existingEmployeeDTO);
		Employee updatedEmployee = new Employee(updatedEmployeeDTO);

		when(iemployeeDao.findById(1)).thenReturn(Optional.of(existingEmployee));
		when(iemployeeDao.save(existingEmployee)).thenReturn(updatedEmployee);

		Employee result = employeeService.updateEmployee(1, updatedEmployeeDTO);

		assertEquals(updatedEmployee, result);

		verify(iemployeeDao, times(1)).findById(1);
		verify(iemployeeDao, times(1)).save(existingEmployee);
	}

	@Test
	public void testUpdateEmployee_NotFound() {
		Integer idToUpdate = 2;
		EmployeeDTO updatedEmployee = new EmployeeDTO(1, "Zakir", Date.valueOf("1984-01-01"), 32, "Zakir@example.com",
				6000, new Department(2));

		when(iemployeeDao.findById(idToUpdate)).thenReturn(Optional.empty());

		assertThrows(NotFoundException.class, () -> {
			employeeService.updateEmployee(idToUpdate, updatedEmployee);
		});
	}

	@Test
	public void testDeleteEmployee_Success() {
		int employeeId = 1;

		doNothing().when(iemployeeDao).deleteById(employeeId);

		ResponseEntity<String> response = employeeService.deleteEmployee(employeeId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Employee deleted successfully", response.getBody());
	}

	@Test
	public void testDeleteEmployee_NotFoundOrUnableToDelete() {
		int employeeId = 2;

		doThrow(new NotFoundException("Employee not found")).when(iemployeeDao).deleteById(employeeId);

		ResponseEntity<String> response = employeeService.deleteEmployee(employeeId);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Employee not found or unable to delete", response.getBody());
	}

	@Test
	public void testFindById_Success() {
		Integer idToSearch = 1;
		Employee employee = new Employee(idToSearch, "Ali", Date.valueOf("1990-01-15"), 30, "ali@example.com", 5500,
				new Department(3));

		when(iemployeeDao.findById(idToSearch)).thenReturn(Optional.of(employee));

		ResponseEntity<Employee> response = employeeService.getEmployeeById(idToSearch);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(employee, response.getBody());

		verify(iemployeeDao, times(1)).findById(idToSearch);
	}

}
