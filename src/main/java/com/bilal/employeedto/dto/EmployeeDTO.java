package com.bilal.employeedto.dto;

import java.sql.Date;

import com.bilal.employeeapp.model.Department;

public class EmployeeDTO {

    private Integer eid;
    private String ename;
    private Date edob;
    private Integer eage;
    private String email;
    private Integer esalary;
   
    public EmployeeDTO() {
    }
    
    
 // Employee class constructor
 	public EmployeeDTO(EmployeeDTO employeeDTO) {
 	 this.eid = employeeDTO.getEid();	
 	 this.ename = employeeDTO.getEname();
 	 this.edob = employeeDTO.getEdob();
 	 this.eage = employeeDTO.getEage();
 	 this.email = employeeDTO.getEmail();
 	 this.esalary = employeeDTO.getEsalary();
 	    
 	    
 	}
 	
 	


    public EmployeeDTO(Integer eid, String ename, Date edob, Integer eage, String email, Integer esalary, Department edepartment) {
        this.eid = eid;
        this.ename = ename;
        this.edob = edob;
        this.eage = eage;
        this.email = email;
        this.esalary = esalary;
    }
    
    public EmployeeDTO(String ename, Date edob, Integer eage, String email, Integer esalary, Department edepartment) {
		super();
		this.ename = ename;
		this.edob = edob;
		this.eage = eage;
		this.email = email;
		this.esalary = esalary;
	}


    // Getters and setters for the fields

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
		// TODO Auto-generated method stub
		return null;
	}


	
}
