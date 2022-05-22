package com.in28minutes.jpa.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Passport {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String number;

	@OneToOne(fetch = FetchType.LAZY, mappedBy="passport")
	//this means that student is the owning side of the relationship
	//add mapped by on the non owning side of the relationship
	private Student student;

	public Passport() {
		// need to have a no args constructor for jpa
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Passport(String number) {

		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setName(String number) {
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "passport [number=" + number + "]";
	}

}
