package com.bilal.employeeapp.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table
public class Employee {
    
	@Id

	@GeneratedValue(
			strategy = GenerationType.IDENTITY
				
			)
	
	private Integer eid;
	private String ename;
	private Date edob;
	private Integer eage; 
	private String email;
	private Integer esalary;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "did")
	private Department edepartment;
	
	public Employee () {
		
		
	}
	
	
	
	// Employee class constructor
	public Employee(EmployeeDTO employeeDTO) {
	 this.eid = employeeDTO.getEid();	
	 this.ename = employeeDTO.getEname();
	 this.edob = employeeDTO.getEdob();
	 this.eage = employeeDTO.getEage();
	 this.email = employeeDTO.getEmail();
	 this.esalary = employeeDTO.getEsalary();
	    
	    
	}
   

	

	public Employee(Integer eid, String ename, Date edob, Integer eage, String email, Integer esalary,
			Department edepartment) {
		this.eid = eid;
		this.ename = ename;
		this.edob = edob;
		this.eage = eage;
		this.email = email;
		this.esalary = esalary;
		this.edepartment = edepartment;
	}



	public Employee(String ename, Date edob, Integer eage, String email, Integer esalary, Department edepartment) {
		super();
		this.ename = ename;
		this.edob = edob;
		this.eage = eage;
		this.email = email;
		this.esalary = esalary;
		this.edepartment = edepartment;
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

	public Department getEdepartment() {
		return edepartment;
	}

	public void setEdepartment(Department edepartment) {
		this.edepartment = edepartment;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", edob=" + edob + ", eage=" + eage + ", email=" + email
				+ ", esalary=" + esalary + ", edepartment=" + edepartment + "]";
	}

	
	
}