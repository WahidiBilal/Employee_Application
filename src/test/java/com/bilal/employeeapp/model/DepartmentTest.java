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
    public  void setUp() {
        department = new Department();
    }

    @Test
    public void testDefaultConstructor() {
        // Verify that the default constructor sets did and dname to null
        assertEquals(null, department.getDid());
        assertEquals(null, department.getDname());
    }

    @Test
    public void testConstructorWithId() {
        Integer expectedDid = 1;
        Department departmentWithId = new Department(expectedDid);

        // Verify that the constructor with an Integer parameter sets did correctly
        assertEquals(expectedDid, departmentWithId.getDid());
        assertEquals(null, departmentWithId.getDname()); // dname should be null
    }

    @Test
    public void testConstructorWithName() {
        String expectedDname = "HR";
        Department departmentWithName = new Department(expectedDname);

        // Verify that the constructor with a String parameter sets dname correctly
        assertEquals(null, departmentWithName.getDid()); // did should be null
        assertEquals(expectedDname, departmentWithName.getDname());
    }

    @Test
    public void testConstructorWithIdAndName() {
        Integer expectedDid = 2;
        String expectedDname = "IT";
        Department departmentWithIdAndName = new Department(expectedDid, expectedDname);

        // Verify that the constructor with both Integer and String parameters sets did and dname correctly
        assertEquals(expectedDid, departmentWithIdAndName.getDid());
        assertEquals(expectedDname, departmentWithIdAndName.getDname());
    }

    @Test
    public void testSetDid() {
        Integer expectedDid = 1;
        department.setDid(expectedDid);

        // Verify that the setDid method sets did correctly
        assertEquals(expectedDid, department.getDid());
    }

    @Test
    public void testSetDname() {
        String expectedDname = "HR";
        department.setDname(expectedDname);

        // Verify that the setDname method sets dname correctly
        assertEquals(expectedDname, department.getDname());
    }
    
    
    @Test
    public void testToString() {
        Integer expectedDid = 3;
        String expectedDname = "Finance";
        Department department = new Department(expectedDid, expectedDname);

        // Verify that the toString method returns the expected string representation
        String expectedToString = "Department [id=" + expectedDid + ", dname=" + expectedDname + "]";
        assertEquals(expectedToString, department.toString());
    }
}
