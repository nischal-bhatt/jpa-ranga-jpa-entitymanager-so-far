package com.in28minutes.jpa.hibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.entity.Course;
import com.in28minutes.jpa.hibernate.entity.Review;

@Repository
@Transactional
public class CourseRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	public Course save(Course course) {
		if (course.getId() == null) {
			em.persist(course);
		} else {
			em.merge(course);
		}

		return course;

	}

	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}

	public void playWithEntityManager() {
		logger.info("playwithEntityMgr - start");

		Course course1 = new Course("web services in 1-- ste[s");
		em.persist(course1);

		Course course2 = new Course("angular js services in 1-- ste[s");
		em.persist(course2);

		em.flush();// changes that are done till then will be saved out to the db
		// em.clear() --> same as entitymanager.detach
		// em.detach(course2);
		course1.setName("web se updated");

		course2.setName("angular updated");
		
		em.refresh(course1); //all the changes done to course 1 are lost
		//course1 will be refreshed with the content that comes back from the db
		em.flush();

		Course course3 = new Course("aloha");
		course3.setName(null); //this is failing
		em.persist(course3);
		em.flush();
		
		// no need to say merge!
		
		Course course4 = findById(3L);
		course4.setName("updated by dane");
		
	}

	public void addReviewsForCourse() {
		Course course = this.findById(2L);
		logger.info("retrieving nish....{}",course.getReviews());
		Review review = new Review("77","Great hands on stuff");
		Review review2 = new Review("79","ranga is the best");
		
		course.addReview(review);
		review.setCourse(course);
		
		course.addReview(review2);
		review2.setCourse(course);
		em.persist(review);
		em.persist(review2);
		
	}
	//get it working, and then make it good
	public void addReviewsForCourse(Long courseId, List<Review> reviews) {
		Course course = this.findById(courseId);
		
		for (Review review: reviews)
		{
			
			course.addReview(review);
			review.setCourse(course);
			em.persist(review);
		}
		
	}

}
