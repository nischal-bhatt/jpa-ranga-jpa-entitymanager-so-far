package com.in28minutes.jpa.hibernate.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "CourseDetails") // this is a hibernate annotation
@NamedQueries(value = { @NamedQuery(name = "get_all_courses", query = "Select c From Course c") })
public class Course {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "FullnAme", nullable = true)
	private String name;

	@OneToMany(mappedBy="course" /*fetch=FetchType.EAGER*/)
	private List<Review> reviews = new ArrayList<>();

	@ManyToMany(mappedBy="courses")
	//student is the owning side
	private List<Student> students = new ArrayList<>();
	
	
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	@CreationTimestamp
	private LocalDateTime createdDate;

	public Course() {
		// need to have a no args constructor for jpa
	}

	public Course(String name) {

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

	public List<Review> getReviews() {
		return reviews;
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}
	
	public void removeReview(Review review) {
		this.reviews.remove(review);
	}

	
	
	public List<Student> getStudents() {
		return students;
	}

	public void addStudents(Student student) {
		this.students.add(student);
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}

}
