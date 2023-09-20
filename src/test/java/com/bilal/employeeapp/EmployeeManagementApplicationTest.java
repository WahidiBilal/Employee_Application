package com.bilal.employeeapp;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class EmployeeManagementApplicationTest {

    @Test
    public void contextLoads() {
    	 Assertions.assertTrue(true, "The context should load successfully.");
    }
}
