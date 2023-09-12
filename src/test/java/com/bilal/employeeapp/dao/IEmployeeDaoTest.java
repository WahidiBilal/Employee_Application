package com.bilal.employeeapp.dao;


import com.bilal.employeeapp.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class IEmployeeDaoTest {

	@Autowired
	private IEmployeeDao underTest;
	
	@Test
	public void saveEmployeeTest() {
		
		//given
		
		
		Employee employee1 = new Employee(
				"bilal",
				new Date(1999-05-12),
				25,
				"bilal@gmail.com",
				200000,
				new Department(
						2
						)
				
				);
		
		Employee employee2 = new Employee(
						"ali",
						new Date(1999-05-12),
						25,
						"ali@gmail.com",
						200000,
						new Department(
								2
								)
						
						);
		
		//When
		underTest.save(employee1);
		underTest.save(employee2);
		
		//then
		assertTrue(employee1.getEid() > 0);
		assertTrue(employee2.getEid() > 1);
		
	}
	
	
	@Test
	public void testSearchByNameExists() {
		
		//given
		
		
		Employee employee1 = new Employee(
				"bilal",
				new Date(1999-05-12),
				25,
				"bilal@gmail.com",
				200000,
				new Department(
						2
						)
				
				);
		
		Employee employee2 = new Employee(
						"ali",
						new Date(1999-05-12),
						25,
						"ali@gmail.com",
						200000,
						new Department(
								2
								)
						
						);
		
		underTest.save(employee1);
		underTest.save(employee2);
		
		// when
		
		List<Employee> result = underTest.findByName("bilal");
		
		// then
		assertEquals(1,result.size());
		
	}

}
