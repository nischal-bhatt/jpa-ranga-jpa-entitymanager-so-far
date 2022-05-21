package com.in28minutes.jpa.hibernate.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Passport {

	@Id
	@GeneratedValue
	private Long id;

	@Column( nullable=false)
	private String number;

	
	
	public Passport() {
      // need to have a no args constructor for jpa
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
