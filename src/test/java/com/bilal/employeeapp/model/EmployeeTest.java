package com.bilal.employeeapp.model;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)

public class EmployeeTest {

	@Test
	public void testDefaultConstructor() {
		Employee employee = new Employee();
		assertNotNull(employee);
	}

	@Test
	public void testSetters() {
		Employee employee = new Employee();

		Integer eid = 1;
		employee.setEid(eid);
		assertEquals(eid, employee.getEid());

		String ename = "John Doe";
		employee.setEname(ename);
		assertEquals(ename, employee.getEname());

		Date edob = new Date(1994 - 8 - 24);
		employee.setEdob(edob);
		assertEquals(edob, employee.getEdob());

		Integer eage = 30;
		employee.setEage(eage);
		assertEquals(eage, employee.getEage());

		String email = "johndoe@example.com";
		employee.setEmail(email);
		assertEquals(email, employee.getEmail());

		Integer esalary = 50000;
		employee.setEsalary(esalary);
		assertEquals(esalary, employee.getEsalary());

		Department edepartment = new Department("IT");
		employee.setEdepartment(edepartment);
		assertEquals(edepartment, employee.getEdepartment());
	}

	@Test
	public void testParameterizedConstructorWithAllFields() {
		Integer eid = 1;
		String ename = "John Doe";
		Date edob = new Date(1994 - 8 - 24);
		Integer eage = 30;
		String email = "johndoe@example.com";
		Integer esalary = 50000;
		Department edepartment = new Department("IT");

		Employee employee = new Employee(eid, ename, edob, eage, email, esalary, edepartment);

		assertNotNull(employee);
		assertEquals(eid, employee.getEid());
		assertEquals(ename, employee.getEname());
		assertEquals(edob, employee.getEdob());
		assertEquals(eage, employee.getEage());
		assertEquals(email, employee.getEmail());
		assertEquals(esalary, employee.getEsalary());
		assertEquals(edepartment, employee.getEdepartment());
	}

	@Test
	public void testParameterizedConstructorWithoutId() {
		String ename = "Jane Doe";
		Date edob = new Date(1994 - 8 - 24);
		Integer eage = 25;
		String email = "janedoe@example.com";
		Integer esalary = 45000;
		Department edepartment = new Department("HR");

		Employee employee = new Employee(ename, edob, eage, email, esalary, edepartment);

		assertNotNull(employee);
		assertNull(employee.getEid()); // Id should be null for a new employee
		assertEquals(ename, employee.getEname());
		assertEquals(edob, employee.getEdob());
		assertEquals(eage, employee.getEage());
		assertEquals(email, employee.getEmail());
		assertEquals(esalary, employee.getEsalary());
		assertEquals(edepartment, employee.getEdepartment());
	}

	@Test
	public void testToStringMethod() {
		Integer eid = 1;
		String ename = "John Doe";
		Date edob = new Date(1994 - 8 - 24);
		Integer eage = 30;
		String email = "johndoe@example.com";
		Integer esalary = 50000;
		Department edepartment = new Department("IT");

		Employee employee = new Employee(eid, ename, edob, eage, email, esalary, edepartment);

		String expectedString = "Employee [eid=" + eid + ", ename=" + ename + ", edob=" + edob + ", eage=" + eage
				+ ", email=" + email + ", esalary=" + esalary + ", edepartment=" + edepartment + "]";

		assertEquals(expectedString, employee.toString());
	}
}