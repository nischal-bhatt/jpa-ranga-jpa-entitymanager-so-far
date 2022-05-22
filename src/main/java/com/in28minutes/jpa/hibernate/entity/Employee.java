package com.in28minutes.jpa.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
//@Entity
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
//@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="employeeType")
//this is default
//there will just be 1 employee table with the above anno
//above performance good but data integrity bad
public abstract class Employee {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "FullnAme", nullable = true)
	private String name;

	

	public Employee() {
		// need to have a no args constructor for jpa
	}

	public Employee(String name) {

		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + "]";
	}

	



}
