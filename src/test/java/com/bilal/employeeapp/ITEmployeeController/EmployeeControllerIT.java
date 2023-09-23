package com.bilal.employeeapp.ITEmployeeController;

import com.bilal.employeeapp.EmployeeManagementApplication;
import com.bilal.employeeapp.dto.DepartmentDTO;
import com.bilal.employeeapp.dto.EmployeeDTO;
import com.bilal.employeeapp.model.Employee;
import com.bilal.employeeapp.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
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
    public void testUpdateEmployee() {
        // Create and add an employee to the database
        EmployeeDTO employee = new EmployeeDTO();
        employee.setEname("Hassan");
		employee.setEage(25);
		employee.setEdob(Date.valueOf("1990-01-01"));
		employee.setEmail("hassan@gmail.com");
		employee.setEsalary(40000);
		employee.setEdepartment(new DepartmentDTO(2));
       
        ResponseEntity<String> status = employeeService.addEmployee(employee);
        
        assertEquals(HttpStatus.CREATED, status.getStatusCode());

        ResponseEntity<List<EmployeeDTO>> savedEmployee = employeeService.findByName("Hassan");
        
        EmployeeDTO updatingEmployee = savedEmployee.getBody().get(0);
        updatingEmployee.setEname("Ali");

        restTemplate.exchange("/api/v1/employee/employee/update/{id}", 
        		HttpMethod.PUT, new HttpEntity<>(updatingEmployee), Employee.class, updatingEmployee.getEid());

        ResponseEntity<Employee> updatedEmployee = employeeService.getEmployeeById(updatingEmployee.getEid());

        assertNotNull(updatedEmployee);
        assertEquals("Ali", updatedEmployee.getBody().getEname());
    }
    

	@Test
	public void testDeleteEmployee() {
		EmployeeDTO employee = new EmployeeDTO();
		employee.setEname("Hassan");
		employee.setEage(25);
		employee.setEdob(Date.valueOf("1990-01-01"));
		employee.setEmail("hassan@gmail.com");
		employee.setEsalary(40000);
		employee.setEdepartment(new DepartmentDTO(2));

		ResponseEntity<String> status = employeeService.addEmployee(employee);

		assertEquals(HttpStatus.CREATED, status.getStatusCode());

		ResponseEntity<List<EmployeeDTO>> savedEmployee = employeeService.findByName("Hassan");

		EmployeeDTO existingEmployee = savedEmployee.getBody().get(0);

		restTemplate.delete("/api/v1/employee/employee/delete/{id}", existingEmployee.getEid());

		ResponseEntity<Employee> deletedEmployee = employeeService.getEmployeeById(existingEmployee.getEid());

		assertEquals(HttpStatus.NOT_FOUND, deletedEmployee.getStatusCode());
		assertNull(deletedEmployee.getBody());
	}

	@Test
	public void testGetAllEmployee() {

		EmployeeDTO employee = new EmployeeDTO();
		employee.setEname("Hassan");
		employee.setEage(25);
		employee.setEdob(Date.valueOf("1990-01-01"));
		employee.setEmail("hassan@gmail.com");
		employee.setEsalary(40000);
		employee.setEdepartment(new DepartmentDTO(2));

		employeeService.addEmployee(employee);

		ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/employee/employees", String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		assertNotNull(response.getBody());
	}
    
    
    
    
}
    