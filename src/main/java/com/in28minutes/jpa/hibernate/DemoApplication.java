package com.in28minutes.jpa.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.jpa.hibernate.entity.Course;
import com.in28minutes.jpa.hibernate.repository.CourseRepository;

@SpringBootApplication

public class DemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		repository.playWithEntityManager();
		
		// repository.save(new Course("jpa"));
		// repository.save(new Course("rrr"));
		// Course course = repository.findById(2L);
		// logger.info("10001 course -> {}", course);

		// repository.save(new Course("Microservices kin 100 stes"));

	}

}
