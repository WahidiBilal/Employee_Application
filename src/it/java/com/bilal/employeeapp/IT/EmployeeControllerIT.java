package com.bilal.employeeapp.IT;


import com.bilal.employeeapp.dto.DepartmentDTO;
import com.bilal.employeeapp.dto.EmployeeDTO;
import com.bilal.employeeapp.service.EmployeeService;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class EmployeeControllerIT {

	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	private EmployeeService employeeService;
	


	@ClassRule
	public static MySQLContainer<?> container = new MySQLContainer<>(DockerImageName.parse("mysql:8.1.0")).withDatabaseName("users")
			.withUsername("root").withPassword("root123");
	
	@Before
	public void setUpContainner() {
		container.start();
		
		try (Connection connection = DriverManager.getConnection(container.getJdbcUrl(), "root", "root123");
	             Statement statement = connection.createStatement()) {
	            // Read and execute the SQL script
	            ClassPathResource resource = new ClassPathResource("import.sql");
	            try (InputStream inputStream = resource.getInputStream();
	                 InputStreamReader reader = new InputStreamReader(inputStream);
	                 BufferedReader bufferedReader = new BufferedReader(reader)) {
	                StringBuilder sqlScript = new StringBuilder();
	                String line;
	                while ((line = bufferedReader.readLine()) != null) {
	                    sqlScript.append(line);
	                    if (line.endsWith(";")) {
	                        statement.execute(sqlScript.toString());
	                        sqlScript.setLength(0);
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
	@DynamicPropertySource
	public static void overrideProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		//registry.add("spring.jpa.properties.hibernate.dialect", org.hibernate.dialect.MySQL5Dialect);
	//	registry.add("spring.jpa.properties.hibernate.format_sql", "true");
		
	}
	

	@AfterClass
	public static void tearDown() {
		container.stop();
	}
	
			
	  @Test
	  @Transactional
      public void testGetAllEmployees() throws Exception {

		
		EmployeeDTO employee1 = new EmployeeDTO("Bilal", Date.valueOf("1995-01-01"), 30, "bilal@gmail.com", 5000,
				new DepartmentDTO(2));
		EmployeeDTO employee2 = new EmployeeDTO("Obaid", Date.valueOf("1995-01-01"), 30, "Obaid@gmail.com", 5000,
				new DepartmentDTO(2));
		
		employeeService.addEmployee(employee1);
		employeeService.addEmployee(employee2);
		
		
		 mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employee/employees")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
	                .andReturn();
	   
	}
	
	
	
	
}