package com.in28minutes.jpa.hibernate.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.in28minutes.jpa.hibernate.DemoApplication;
import com.in28minutes.jpa.hibernate.entity.Course;
import com.in28minutes.jpa.hibernate.entity.Review;

@SpringBootTest(classes=DemoApplication.class)
class CourseRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	EntityManager em;
	
	
	@Test
	public void findById_basic() {
		
		Course course = courseRepository.findById(2L);
		assertEquals("rrr",course.getName());
		
		logger.info("test is running");
	}
	
	@Test
	@DirtiesContext
	public void deleteById_basic() {
		
		 courseRepository.deleteById(2L);
		assertNull(courseRepository.findById(2L));
		
		
	}
	
	@Test
	@DirtiesContext
	//if any unit test is updating data, jus add @DirtiesContext
	public void save_basic() {
		
		Course course = courseRepository.findById(2L);
		assertEquals("rrr",course.getName());
		course.setName("roman reighns");
		
		courseRepository.save(course);
		Course course1 = courseRepository.findById(2L);
		assertEquals("roman reighns",course1.getName());
		
		
		
		
		
	}
	
	@Test
	@DirtiesContext
	public void playWithEntityManager() {
		
		 courseRepository.playWithEntityManager();
		
		
		
		
		
	}
	
	@Test
	@Transactional //persistence context lives thru the entirety of this method
	//with eager fetching no need transactional
	public void retrieveReviewsForCourse() {
		
		 Course course1 = courseRepository.findById(2L);
		logger.info("pringint courses nish->{}", course1.getReviews());
		//it is doing a lazy fetching
		
		
		
		
	}
	
	
	@Test
	//@Transactional //persistence context lives thru the entirety of this method
	//with eager fetching no need transactional
	public void retrieveCourseForReview() {
		// many to 1 by default is eager fetching
		// Course course1 = courseRepository.findById(2L);
		//logger.info("pringint courses nish->{}", course1.getReviews());
		//it is doing a lazy fetching
		
		Review review = em.find(Review.class, 18L);
		logger.info("->->{}",review.getCourse());
		
		
		//***ToOne default is always eager fetching
		//***ToMany default is always lazy fetching
		
	}
	
	

}
