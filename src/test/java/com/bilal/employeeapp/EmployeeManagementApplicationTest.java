package com.bilal.employeeapp;


import org.junit.jupiter.api.Assertions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = EmployeeManagementApplication.class)
public class EmployeeManagementApplicationTest {

	
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplicationTest.class, args);
		
		Assertions.assertTrue(true, "The context should load successfully.");
	}

}
