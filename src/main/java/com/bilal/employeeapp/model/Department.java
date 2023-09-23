package com.bilal.employeeapp.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table
public class Department {
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.IDENTITY
			)
	
	private Integer did;
	private String dname;
	
//	@OneToMany(mappedBy = "edepartment", cascade = CascadeType.ALL)
//	@JsonIgnore
//	private List<Employee> employees = new ArrayList<>();
	
	public Department() {
		
	}
	
	public Department(Integer did) {
		this.did = did;
	}
	
	

	public Department(String dname) {
		super();
		this.dname = dname;
	}

	public Department(Integer did, String dname) {
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