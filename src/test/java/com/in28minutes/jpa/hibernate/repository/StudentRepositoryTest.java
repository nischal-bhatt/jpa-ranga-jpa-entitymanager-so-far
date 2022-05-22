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
	//@Transactional //the moment u create a transaction, you are creating
	               // a persistence context
	public void someTest()
	{
		studentRepository.someOperationToUnderstandPersistenceContext();
		//notice now the above no need @Transactional coz student repo alrd has transactional anno
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
	
	@Test
	//@Transactional
	public void retrievePassportAndAssociatedStudent() {
		//any 1 to 1 relationship is always eager fetch
		//but we can change to lazy 
		//we got the passport details even though we did not request for it
		Passport pport=em.find(Passport.class, 11L);
		logger.info("passport->{}",pport);
		logger.info("student hmm-> {}",pport.getStudent());
	}
	
	@Test
	@Transactional
	public void retrieveStudentAndCourse() {
		Student student = em.find(Student.class, 22L);
		logger.info("student->{}",student);
		logger.info("courses->{}",student.getCourses());
		
		
		//any 1 to 1 relationship is always eager fetch
		//but we can change to lazy 
		//we got the passport details even though we did not request for it
		//Passport pport=em.find(Passport.class, 11L);
		
		//logger.info("student hmm-> {}",pport.getStudent());
	}
	
	
	
	

}




  //what happens in a transaction? 
  //unit testing --> repo --> entitymanager
  // uinit testing --> entitiymanager

   /*
    *    @Transactional
    *    void someMethodWithChange( 
    *    {
    *    
    *    //create objects
    *       em.persist(user1); firing insert
    *       // here, the object itself doesnt get persisted
    *       // but rather only a seq is generated for NOW
    *       em.persiste(user2);
    *       
    *       //change user1  firing update
    *       //change user2
    *      }//all changes are saved down to the databse
    * 
    * when do the queries get fired?
    * hibernate waits until the last possible moment before
    * it would persist the changes
    * why does hibernate wait until the last moment to persist?
    * coz lets say some of the updates fail.. 
    */
//if you really wanna push some of tghe chnages to db earilier, just use em.flush
// @Transactional -- whenever u r making a change on the db -- u def need a transaction