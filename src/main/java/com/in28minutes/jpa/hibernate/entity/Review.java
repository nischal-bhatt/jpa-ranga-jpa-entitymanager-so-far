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
public class Review {

	@Id
	@GeneratedValue
	private Long id;

	private String rating;

	private String description;

	public Review() {
		// need to have a no args constructor for jpa
	}

	public Review(String rating, String description) {

		this.rating = rating;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDeescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Review [description=" + description + "" + rating + "]";
	}

}
