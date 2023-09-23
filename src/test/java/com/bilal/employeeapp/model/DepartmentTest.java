package com.bilal.employeeapp.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DepartmentTest {
	private Department department;

	@Before
	public void setUp() {
		department = new Department();
	}

	@Test
	public void testDefaultConstructor() {

		assertEquals(null, department.getDid());
		assertEquals(null, department.getDname());
	}

	@Test
	public void testConstructorWithId() {
		Integer expectedDid = 1;
		Department departmentWithId = new Department(expectedDid);

		assertEquals(expectedDid, departmentWithId.getDid());
		assertEquals(null, departmentWithId.getDname());
	}

	@Test
	public void testConstructorWithName() {
		String expectedDname = "HR";
		Department departmentWithName = new Department(expectedDname);

		assertEquals(null, departmentWithName.getDid());
		assertEquals(expectedDname, departmentWithName.getDname());
	}

	@Test
	public void testConstructorWithIdAndName() {
		Integer expectedDid = 2;
		String expectedDname = "IT";
		Department departmentWithIdAndName = new Department(expectedDid, expectedDname);

		assertEquals(expectedDid, departmentWithIdAndName.getDid());
		assertEquals(expectedDname, departmentWithIdAndName.getDname());
	}

	@Test
	public void testSetDid() {
		Integer expectedDid = 1;
		department.setDid(expectedDid);

		assertEquals(expectedDid, department.getDid());
	}

	@Test
	public void testSetDname() {
		String expectedDname = "HR";
		department.setDname(expectedDname);

		assertEquals(expectedDname, department.getDname());
	}

	@Test
	public void testToString() {
		Integer expectedDid = 3;
		String expectedDname = "Finance";
		Department department = new Department(expectedDid, expectedDname);

		String expectedToString = "Department [id=" + expectedDid + ", dname=" + expectedDname + "]";
		assertEquals(expectedToString, department.toString());
	}
}
