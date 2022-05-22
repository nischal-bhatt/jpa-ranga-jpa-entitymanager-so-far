package com.in28minutes.jpa.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private Long id;

	private String rating;

	private String description;

	@ManyToOne
	private Course course;

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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Review [description=" + description + "" + rating + "]";
	}

}
