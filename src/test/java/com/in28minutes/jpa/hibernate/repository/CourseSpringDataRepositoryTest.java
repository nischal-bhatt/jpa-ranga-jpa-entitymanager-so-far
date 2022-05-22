package com.in28minutes.jpa.hibernate.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import com.in28minutes.jpa.hibernate.DemoApplication;
import com.in28minutes.jpa.hibernate.entity.Course;

@SpringBootTest(classes=DemoApplication.class)
class CourseSpringDataRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseSpringDataRepository courseSpringDataRepository;
	
	@Test
	public void findById_course_Present()
	{
		Optional<Course> course=courseSpringDataRepository.findById(1L);
	    logger.info("here here here {}", course.isPresent());
	    assertTrue(course.isPresent());
	}
	
	@Test
	public void playingAround()
	{
		Course course = new Course("juuuiii");
		courseSpringDataRepository.save(course);
		course.setName("hahahahaha");
		courseSpringDataRepository.save(course);
		
	}
	
	@Test
	public void sort()
	{
		
		Sort sort = Sort.by(Sort.Direction.DESC,"name");
		
		logger.info("sorted courzes -> {}", this.courseSpringDataRepository.findAll(sort));
		logger.info("count {}", this.courseSpringDataRepository.count());
		
	}
	

}
