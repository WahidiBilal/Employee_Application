package com.bilal.employeeapp;
//
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.junit.jupiter.api.Assertions;
//
//
//@SpringBootTest
//public class EmployeeManagementApplicationTest {
//
//	@Test
//	public void contextLoads() {
//		
//		 Assertions.assertTrue(true, "The context should load successfully.");
//		
//	}
//
//}
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeManagementApplicationTest {

    @Test
    public void mainMethodRunsSuccessfully() {
        // You can call the main method directly and check for any exceptions
        try {
            EmployeeManagementApplication.main(new String[]{});
        } catch (Exception e) {
            fail("The main method should run without exceptions.");
        }
    }
}