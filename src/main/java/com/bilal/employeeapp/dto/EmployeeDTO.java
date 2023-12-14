package com.bilal.employeeapp.dto;

import java.sql.Date;

public class EmployeeDTO {
    

	
	private Integer eid;
	private String ename;
	private Date edob;
	private Integer eage; 
	private String email;
	private Integer esalary;
	private DepartmentDTO edepartment;
	private String message;
	
	public EmployeeDTO () {
		
	}
	
	

	public EmployeeDTO(Integer eid, String ename, Date edob, Integer eage, String email, Integer esalary,
			DepartmentDTO edepartment) {
		this.eid = eid;
		this.ename = ename;
		this.edob = edob;
		this.eage = eage;
		this.email = email;
		this.esalary = esalary;
		this.edepartment = edepartment;
	}



	public EmployeeDTO(String ename, Date edob, Integer eage, String email, Integer esalary, DepartmentDTO edepartment) {
		super();
		this.ename = ename;
		this.edob = edob;
		this.eage = eage;
		this.email = email;
		this.esalary = esalary;
		this.edepartment = edepartment;
	}
	
	

	public EmployeeDTO(String message, EmployeeDTO employeeDTO) {
		this.message = message;
	}



	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Date getEdob() {
		return edob;
	}

	public void setEdob(Date edob) {
		this.edob = edob;
	}

	public Integer getEage() {
		return eage;
	}

	public void setEage(Integer eage) {
		this.eage = eage;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEsalary() {
		return esalary;
	}

	public void setEsalary(Integer esalary) {
		this.esalary = esalary;
	}

	public DepartmentDTO getEdepartment() {
		return edepartment;
	}

	public void setEdepartment(DepartmentDTO edepartment) {
		this.edepartment = edepartment;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", edob=" + edob + ", eage=" + eage + ", email=" + email
				+ ", esalary=" + esalary + ", edepartment=" + edepartment + "]";
	}
	
	
}
