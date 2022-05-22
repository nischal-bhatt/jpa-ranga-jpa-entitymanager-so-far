package com.in28minutes.jpa.hibernate.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa.hibernate.DemoApplication;
import com.in28minutes.jpa.hibernate.entity.Student;

@SpringBootTest(classes=DemoApplication.class)
class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	EntityManager em;
	
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
