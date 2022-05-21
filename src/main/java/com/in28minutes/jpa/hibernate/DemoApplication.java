package com.in28minutes.jpa.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.jpa.hibernate.entity.Passport;
import com.in28minutes.jpa.hibernate.entity.Review;
import com.in28minutes.jpa.hibernate.entity.Student;
import com.in28minutes.jpa.hibernate.repository.CourseRepository;
import com.in28minutes.jpa.hibernate.repository.PassportRepository;
import com.in28minutes.jpa.hibernate.repository.ReviewRepository;
import com.in28minutes.jpa.hibernate.repository.StudentRepository;

@SpringBootApplication

public class DemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository repository;

	@Autowired
	private StudentRepository srepository;
	
	@Autowired
	private PassportRepository prepository;
	
	@Autowired 
	private ReviewRepository rrepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		repository.playWithEntityManager();
		srepository.save(new Student("ranga"));
		srepository.save(new Student("karanam"));
		srepository.save(new Student("nish"));
		prepository.save(new Passport("167"));
		prepository.save(new Passport("222"));
		rrepository.save(new Review("1","hello"));
		rrepository.save(new Review("2","hehe"));
		
		srepository.saveStudentWithPassport();
		// repository.save(new Course("jpa"));
		// repository.save(new Course("rrr"));
		// Course course = repository.findById(2L);
		// logger.info("10001 course -> {}", course);

		// repository.save(new Course("Microservices kin 100 stes"));

	}

}
