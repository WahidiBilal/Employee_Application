package com.bilal.employeeapp;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = EmployeeManagementApplication.class)
public class EmployeeManagementApplicationTest {

	@Test
	public void contextLoads() {
		 Assertions.assertTrue(true, "The context should load successfully.");
	}

}