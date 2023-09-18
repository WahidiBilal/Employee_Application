package com.bilal.employeeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = EmployeeManagementApplication.class)
public class EmployeeManagementApplicationTest {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplicationTest.class, args);
	}

}
