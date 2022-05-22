package com.in28minutes.jpa.hibernate.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa.hibernate.DemoApplication;
import com.in28minutes.jpa.hibernate.entity.Passport;
import com.in28minutes.jpa.hibernate.entity.Student;

@SpringBootTest(classes=DemoApplication.class)
class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	EntityManager em;
	
	@Test
	@Transactional //the moment u create a transaction, you are creating
	               // a persistence context
	public void someTest()
	{
		//whenever we call a method on the entitymanager, what we are playing with
		//is the persistencecontext
		//if u dont put @Transactional at the start of a method,
		// each call is like its own transaction
		//remove the above transactional from the method and see wehat happen
		//lazy initialization exception
		Student s = em.find(Student.class, 12L);
		//persistence context (student)
		Passport p =s.getPassport();
		//persistence context (student,passport)
		p.setName("G50");
		//persistence context (student,passport++)
		s.setName("payalrohatgi");
		//persistence context (student++,passport++)
		//queries fired at end of transaction, end of method
	}
	
	@Test
	@Transactional
	public void retrieveStudentAndPassportDetails() {
		//any 1 to 1 relationship is always eager fetch
		//but we can change to lazy 
		//we got the passport details even though we did not request for it
		Student student=em.find(Student.class, 12L);
		logger.info("student->{}",student);
		logger.info("passport -> {}",student.getPassport());
	}
	
	
	
	

}
