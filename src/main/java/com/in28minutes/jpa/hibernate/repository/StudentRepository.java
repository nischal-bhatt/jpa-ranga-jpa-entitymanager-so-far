package com.in28minutes.jpa.hibernate.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.entity.Course;
import com.in28minutes.jpa.hibernate.entity.Passport;
import com.in28minutes.jpa.hibernate.entity.Student;

@Repository
@Transactional
public class StudentRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public Student findById(Long id) {
		return em.find(Student.class, id);
	}

	public Student save(Student student) {
		if (student.getId() == null) {
			em.persist(student);
		} else {
			em.merge(student);
		}

		return student;

	}
	
	public void saveStudentWithPassport()
	{
		Passport p = new Passport("Z");
		em.persist(p);
		Student s = new Student ("nish");
		s.setPassport(p);
		em.persist(s);
	}//hibernate is lazy

	
	public void someOperationToUnderstandPersistenceContext() {
		//whenever we call a method on the entitymanager, what we are playing with
		//is the persistencecontext
		//if u dont put @Transactional at the start of a method,
		// each call is like its own transaction
		//remove the above transactional from the method and see wehat happen
		//lazy initialization exception
		Student s = em.find(Student.class, 12L);
		//persistence context (student)
		//in hibernate , session = persistence context
		Passport p =s.getPassport();
		//persistence context (student,passport)
		p.setName("G50");
		//persistence context (student,passport++)
		s.setName("payalrohatgi");
		//persistence context (student++,passport++)
		//queries fired at end of transaction, end of method
	}

	public void insertStudentAndCourse()
	{
		Student ss = new Student("jack");
		Course cc = new Course("Micro");
		em.persist(ss);
		em.persist(cc);
		
		ss.addCourse(cc);
		cc.addStudents(ss);
		
		em.persist(ss);
		
	}
	
	public void insertStudentAndCourse(Student student,Course course)
	{
		//Student ss = new Student("jack");
		//Course cc = new Course("Micro");
		
		student.addCourse(course);
		course.addStudents(student);
		
		em.persist(student);
		em.persist(course);
		
		
		
		
		
	}
	

}
