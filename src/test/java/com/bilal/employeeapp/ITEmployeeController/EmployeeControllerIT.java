package com.bilal.employeeapp.ITEmployeeController;

import com.bilal.employeeapp.EmployeeManagementApplication;
import com.bilal.employeeapp.model.Employee;
import com.bilal.employeeapp.service.EmployeeService;
import com.bilal.employeedto.dto.EmployeeDTO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeManagementApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class EmployeeControllerIT {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private EmployeeService employeeService;

	@Test
	public void testAddAndGetEmployeeDTO() {
		// Create a new EmployeeDTO object
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEname("Hassan");
		employeeDTO.setEage(25);
		employeeDTO.setEdob(Date.valueOf("1990-01-01"));
		employeeDTO.setEmail("hassan@gmail.com");
		employeeDTO.setEsalary(40000);

		// Add the employee using the API endpoint
		ResponseEntity<String> addResponse = restTemplate
				.postForEntity("http://localhost:" + port + "/api/v1/employee/employee/add", employeeDTO, String.class);

		assertEquals(HttpStatus.CREATED, addResponse.getStatusCode());
		assertNotNull(addResponse.getBody());

		// Get the added employee using the API endpoint
		ResponseEntity<EmployeeDTO> getResponse = restTemplate
				.getForEntity("http://localhost:" + port + "/api/v1/employee/employee/{id}", EmployeeDTO.class, 1);

		assertEquals(HttpStatus.OK, getResponse.getStatusCode());
		assertNotNull(getResponse.getBody());
		assertEquals("Hassan", getResponse.getBody().getEname());
	}

	@Test
	public void testUpdateEmployeeDTO() {

		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEname("Hassan");

		ResponseEntity<String> status = employeeService.addEmployee(employeeDTO);

		assertEquals(HttpStatus.CREATED, status.getStatusCode());

		ResponseEntity<List<Employee>> savedEmployee = employeeService.findByName("Hassan");

		Employee updatingEmployeeDTO = savedEmployee.getBody().get(0);
		updatingEmployeeDTO.setEname("Ali");

		restTemplate.exchange("/api/v1/employee/employee/update/{id}", HttpMethod.PUT,
				new HttpEntity<>(updatingEmployeeDTO), EmployeeDTO.class, updatingEmployeeDTO.getEid());

		ResponseEntity<Employee> updatedEmployeeDTO = employeeService.getEmployeeById(updatingEmployeeDTO.getEid());

		assertNotNull(updatedEmployeeDTO);
		assertEquals("Ali", updatedEmployeeDTO.getBody().getEname());
	}

	@Test
	public void testDeleteEmployeeDTO() {

		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEname("Hassan");
		employeeDTO.setEage(25);
		employeeDTO.setEdob(Date.valueOf("1990-01-01"));
		employeeDTO.setEmail("hassan@gmail.com");
		employeeDTO.setEsalary(40000);

		ResponseEntity<String> status = employeeService.addEmployee(employeeDTO);

		assertEquals(HttpStatus.CREATED, status.getStatusCode());

		ResponseEntity<List<Employee>> savedEmployee = employeeService.findByName("Hassan");

		Employee existingEmployee = savedEmployee.getBody().get(0);

		restTemplate.delete("/api/v1/employee/employee/delete/{id}", existingEmployee.getEid());

		ResponseEntity<Employee> deletedEmployee = employeeService.getEmployeeById(existingEmployee.getEid());

		assertEquals(HttpStatus.NOT_FOUND, deletedEmployee.getStatusCode());
		assertNull(deletedEmployee.getBody());
	}

	@Test
	public void testGetAllEmployeeDTO() {

		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEname("Hassan");
		employeeDTO.setEage(25);
		employeeDTO.setEdob(Date.valueOf("1990-01-01"));
		employeeDTO.setEmail("hassan@gmail.com");
		employeeDTO.setEsalary(40000);

		ResponseEntity<String> status = employeeService.addEmployee(employeeDTO);

		assertEquals(HttpStatus.CREATED, status.getStatusCode());

		ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/employee/employees", String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	public void testSearchByEmployeeNameDTO() {

		EmployeeDTO employeeDTO1 = new EmployeeDTO();
		employeeDTO1.setEname("Hassan");
		EmployeeDTO employeeDTO2 = new EmployeeDTO();
		employeeDTO2.setEname("Bilal");

		ResponseEntity<String> status1 = employeeService.addEmployee(employeeDTO1);
		ResponseEntity<String> status2 = employeeService.addEmployee(employeeDTO2);

		assertEquals(HttpStatus.CREATED, status1.getStatusCode());
		assertEquals(HttpStatus.CREATED, status2.getStatusCode());

		String nameToSearch = "Hassan";

		ParameterizedTypeReference<List<EmployeeDTO>> responseType = new ParameterizedTypeReference<List<EmployeeDTO>>() {
		};

		ResponseEntity<List<EmployeeDTO>> responseEntity = restTemplate
				.exchange("/api/v1/employee/employees/search?name=" + nameToSearch, HttpMethod.GET, null, responseType);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		List<EmployeeDTO> employees = responseEntity.getBody();

		assertNotNull(employees);
		assertEquals(1, employees.size());
	}

}
