package com.bilal.employeeapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bilal.employeeapp.dao.IEmployeeDao;
import com.bilal.employeeapp.dto.DepartmentDTO;
import com.bilal.employeeapp.dto.EmployeeDTO;
import com.bilal.employeeapp.exceptions.NotFoundException;
import com.bilal.employeeapp.model.Department;
import com.bilal.employeeapp.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	public IEmployeeDao employeeDao;

	public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {

		List<EmployeeDTO> employeeDTOList = new ArrayList<>();

		try {

			List<Employee> employeeList = employeeDao.findAll();

			if (employeeList != null && employeeList.size() > 0) {

				for (Employee e : employeeList) {
					employeeDTOList.add(convertToEmployeeDTO(e));
				}

				return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<String> addEmployee(EmployeeDTO employeeDTO) {
		try {
			Employee saveEmployee = employeeDao.save(convertToEmployee(employeeDTO));

			if (saveEmployee != null && saveEmployee.getEid() > 0) {
				return ResponseEntity.status(HttpStatus.CREATED).body("Employee added successfully");
			} else {

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Employee added fail");
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	public Employee updateEmployee(Integer id, EmployeeDTO employeeDTO) {

		Employee existingEmployee = employeeDao.findById(id).orElse(null);

		if (existingEmployee == null) {
			throw new NotFoundException("Employee not found for ID::" + id);
		}

		existingEmployee.setEname(employeeDTO.getEname());
		existingEmployee.setEage(employeeDTO.getEage());
		existingEmployee.setEdob(employeeDTO.getEdob());
		existingEmployee.setEmail(employeeDTO.getEmail());
		existingEmployee.setEsalary(employeeDTO.getEsalary());

		Department department = new Department();
		department.setDid(employeeDTO.getEdepartment().getDid());
		department.setDname(employeeDTO.getEdepartment().getDname());

		existingEmployee.setEdepartment(department);

		return employeeDao.save(existingEmployee);

	}

	public ResponseEntity<String> deleteEmployee(Integer id) {
		try {
			employeeDao.deleteById(id);
			return ResponseEntity.ok("Employee deleted successfully");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found or unable to delete");
		}
	}

	public ResponseEntity<Employee> getEmployeeById(Integer id) {

		Employee employee = employeeDao.findById(id).orElse(null);

		if (employee == null) {
			return new ResponseEntity<>(employee, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(employee, HttpStatus.OK);
		}

	}

	public ResponseEntity<List<EmployeeDTO>> findByName(String name) {

		try {

			List<Employee> employeeList = employeeDao.findByName(name);

			List<EmployeeDTO> employeeDtoList = new ArrayList<>();

			if (employeeList != null) {

				for (Employee e : employeeList) {
					employeeDtoList.add(convertToEmployeeDTO(e));
				}

				return new ResponseEntity<>(employeeDtoList, HttpStatus.OK);
			} else {

				return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	private EmployeeDTO convertToEmployeeDTO(Employee employee) {

		DepartmentDTO departmentDTO = new DepartmentDTO();

		departmentDTO.setDid(employee.getEdepartment().getDid());
		departmentDTO.setDname(employee.getEdepartment().getDname());

		EmployeeDTO employeeDTO = new EmployeeDTO();

		employeeDTO.setEid(employee.getEid());
		employeeDTO.setEname(employee.getEname());
		employeeDTO.setEage(employee.getEage());
		employeeDTO.setEdob(employee.getEdob());
		employeeDTO.setEmail(employee.getEmail());
		employeeDTO.setEsalary(employee.getEsalary());
		employeeDTO.setEdepartment(departmentDTO);

		return employeeDTO;
	}

	private Employee convertToEmployee(EmployeeDTO employeeDTO) {

		Department department = new Department();

		department.setDid(employeeDTO.getEdepartment().getDid());
		department.setDname(employeeDTO.getEdepartment().getDname());

		Employee employee = new Employee();

		employee.setEid(employeeDTO.getEid());
		employee.setEage(employeeDTO.getEage());
		employee.setEname(employeeDTO.getEname());
		employee.setEmail(employeeDTO.getEmail());
		employee.setEdob(employeeDTO.getEdob());
		employee.setEsalary(employeeDTO.getEsalary());

		employee.setEdepartment(department);

		return employee;
	}

}