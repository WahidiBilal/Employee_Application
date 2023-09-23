package com.bilal.employeeapp.dto;

public class DepartmentDTO {
	
	
	private Integer did;
	private String dname;
	
	
	public DepartmentDTO() {
		
	}
	
	public DepartmentDTO(Integer did) {
		this.did = did;
	}
	
	

	public DepartmentDTO(String dname) {
		super();
		this.dname = dname;
	}

	public DepartmentDTO(Integer did, String dname) {
		super();
		this.did = did;
		this.dname = dname;
	}

	public Integer getDid() {
		return did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}


	@Override
	public String toString() {
		return "Department [id=" + did + ", dname=" + dname + "]";
	}
	
	
	

}

