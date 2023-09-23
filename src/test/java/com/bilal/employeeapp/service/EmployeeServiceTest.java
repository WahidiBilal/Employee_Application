package com.bilal.employeeapp.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
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
import com.bilal.employeeapp.dto.DepartmentDTO;
import com.bilal.employeeapp.dto.EmployeeDTO;
import com.bilal.employeeapp.exceptions.NotFoundException;
import com.bilal.employeeapp.model.Department;
import com.bilal.employeeapp.model.Employee;

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

		ResponseEntity<List<EmployeeDTO>> response = employeeService.getAllEmployee();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Zakir", response.getBody().get(0).getEname());
		assertEquals(Date.valueOf("1984-01-01"), response.getBody().get(0).getEdob());
		assertEquals(32, response.getBody().get(0).getEage());
		assertEquals("Zakir@example.com", response.getBody().get(0).getEmail());
		assertEquals(6000, response.getBody().get(0).getEsalary());
		assertEquals(2, response.getBody().get(0).getEdepartment().getDid());

		verify(iemployeeDao, times(1)).findAll();
	}

	@Test
	public void testGetAllEmployeeEmptyList() {
		List<Employee> employees = new ArrayList<>();

		when(iemployeeDao.findAll()).thenReturn(employees);

		ResponseEntity<List<EmployeeDTO>> response = employeeService.getAllEmployee();

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertTrue(response.getBody().isEmpty());

		verify(iemployeeDao, times(1)).findAll();
	}

	@Test
	public void testGetAllEmployeeNotFound() {
	    when(iemployeeDao.findAll()).thenReturn(null);

	    ResponseEntity<List<EmployeeDTO>> response = employeeService.getAllEmployee();

	    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	    assertNotNull(response.getBody());
	    assertEquals(0, response.getBody().size());
	}

	@Test
	public void testGetAllEmployeeInternalServerError() {
	    when(iemployeeDao.findAll()).thenThrow(new RuntimeException("Database error"));

	    ResponseEntity<List<EmployeeDTO>> response = employeeService.getAllEmployee();

	    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
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
		assertEquals("Bilal", result.getBody().getEname());
		assertEquals(Date.valueOf("1995-01-01"), result.getBody().getEdob());
		assertEquals(30, result.getBody().getEage());
		assertEquals("bilal@gmail.com", result.getBody().getEmail());
		assertEquals(5000, result.getBody().getEsalary());
		assertEquals(2, result.getBody().getEdepartment().getDid());

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

		ResponseEntity<List<EmployeeDTO>> response = employeeService.findByName("Zakir");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Zakir", response.getBody().get(0).getEname());

		verify(iemployeeDao, times(1)).findByName("Zakir");
	}

	@Test
	public void testFindByNameNotFound() {
		String nameToSearch = "NonExistentName";

		when(iemployeeDao.findByName(nameToSearch)).thenReturn(null);

		ResponseEntity<List<EmployeeDTO>> response = employeeService.findByName(nameToSearch);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(0, response.getBody().size());
	}

	@Test
	public void testAddEmployeeSuccessfully() {
		EmployeeDTO employeeDTO = new EmployeeDTO(1, "Bilal", Date.valueOf("1995-01-01"), 30, "bilal@gmail.com", 5000,
				new DepartmentDTO(2));

		Employee employee = new Employee(1, "Bilal", Date.valueOf("1995-01-01"), 30, "bilal@gmail.com", 5000,
				new Department(2));

		when(iemployeeDao.save(any(Employee.class))).thenReturn(employee);

		ResponseEntity<String> response = employeeService.addEmployee(employeeDTO);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Employee added successfully", response.getBody());
	}

	@Test
	public void testAddEmployeeInvalidEid() {
		EmployeeDTO employeeDTO = new EmployeeDTO(1, "Bilal", Date.valueOf("1995-01-01"), 30, "bilal@gmail.com", 5000,
				new DepartmentDTO(2));

		when(iemployeeDao.save(any(Employee.class))).thenReturn(
				new Employee(-1, "Bilal", Date.valueOf("1995-01-01"), 30, "bilal@gmail.com", 5000, new Department(2)));

		ResponseEntity<String> response = employeeService.addEmployee(employeeDTO);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Employee added fail", response.getBody());

		verify(iemployeeDao, times(1)).save(any(Employee.class));
	}

	@Test
	public void testAddEmployeeInternalServerError() {

		EmployeeDTO employeeDTO = new EmployeeDTO(1, "Bilal", Date.valueOf("1995-01-01"), 30, "bilal@gmail.com", 5000,
				new DepartmentDTO(2));

		Employee employee = new Employee(1, "Bilal", Date.valueOf("1995-01-01"), 30, "bilal@gmail.com", 5000,
				new Department(2));

		when(iemployeeDao.save(employee)).thenThrow(new RuntimeException("Database error"));

		ResponseEntity<String> response = employeeService.addEmployee(employeeDTO);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Employee added fail", response.getBody());
	}

	@Test
	public void testUpdateEmployeeSuccess() {

		Employee existingEmployee = new Employee(1, "Zakir", Date.valueOf("1984-01-01"), 32, "Zakir@example.com", 6000,
				new Department(2));

		Employee updatedEmployee = new Employee(1, "Bilal", Date.valueOf("1990-01-01"), 35, "bilal@example.com", 7000,
				new Department(3));

		when(iemployeeDao.findById(1)).thenReturn(Optional.of(existingEmployee));

		when(iemployeeDao.save(existingEmployee)).thenReturn(updatedEmployee);

		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO = convertToEmployeeDTO(updatedEmployee);

		Employee result = employeeService.updateEmployee(1, employeeDTO);

		assertEquals(updatedEmployee, result);
		assertEquals("Bilal", existingEmployee.getEname());
		assertEquals(Date.valueOf("1990-01-01"), existingEmployee.getEdob());
		assertEquals(35, existingEmployee.getEage());
		assertEquals("bilal@example.com", existingEmployee.getEmail());
		assertEquals(7000, existingEmployee.getEsalary());
		assertEquals(3, existingEmployee.getEdepartment().getDid());

		verify(iemployeeDao, times(1)).findById(1);
		verify(iemployeeDao, times(1)).save(existingEmployee);
	}

	@Test
	public void testUpdateEmployee_NotFound() {
		Integer idToUpdate = 2;
		EmployeeDTO updatedEmployee = new EmployeeDTO(1, "Zakir", Date.valueOf("1984-01-01"), 32, "Zakir@example.com",
				6000, new DepartmentDTO(2));

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

	@Test
	public void testAddEmployeeFail() {

		EmployeeDTO employeeDTO = new EmployeeDTO(1, "Zakir", Date.valueOf("1984-01-01"), 32, "Zakir@example.com", 6000,
				new DepartmentDTO(2));

		Employee employee = new Employee(1, "Zakir", Date.valueOf("1984-01-01"), 32, "Zakir@example.com", 6000,
				new Department(2));

		when(iemployeeDao.save(employee)).thenReturn(null);

		ResponseEntity<String> response = employeeService.addEmployee(employeeDTO);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Employee added fail", response.getBody());
	}

	@Test
	public void testAddEmployeeException() {
		EmployeeDTO employeeDTO = new EmployeeDTO();

		when(iemployeeDao.save(any(Employee.class))).thenReturn(null);

		ResponseEntity<String> response = employeeService.addEmployee(employeeDTO);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	public void testFindByNameInternalServerError() {
		String nameToSearch = "Ali";

		when(iemployeeDao.findByName(nameToSearch)).thenThrow(new RuntimeException("Database error"));

		ResponseEntity<List<EmployeeDTO>> response = employeeService.findByName(nameToSearch);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(0, response.getBody().size());
	}

	private EmployeeDTO convertToEmployeeDTO(Employee employee) {

		DepartmentDTO departmentDTO = new DepartmentDTO();

		departmentDTO.setDid(employee.getEdepartment().getDid());
		departmentDTO.setDname(employee.getEdepartment().getDname());

		EmployeeDTO employeeDTO = new EmployeeDTO();

		employeeDTO.setEid(employee.getEid());
		employeeDTO.setEname(employee.getEname());
		employeeDTO.setEage(employee.getEage());
		employeeDTO.setEdob(employee.getEdob());
		employeeDTO.setEmail(employee.getEmail());
		employeeDTO.setEsalary(employee.getEsalary());
		employeeDTO.setEdepartment(departmentDTO);

		return employeeDTO;
	}

}
