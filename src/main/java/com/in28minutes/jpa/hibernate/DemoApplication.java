package com.in28minutes.jpa.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.jpa.hibernate.entity.Course;
import com.in28minutes.jpa.hibernate.entity.FullTimeEmployee;
import com.in28minutes.jpa.hibernate.entity.PartTimeEmployee;
import com.in28minutes.jpa.hibernate.entity.Passport;
import com.in28minutes.jpa.hibernate.entity.Review;
import com.in28minutes.jpa.hibernate.entity.Student;
import com.in28minutes.jpa.hibernate.repository.CourseRepository;
import com.in28minutes.jpa.hibernate.repository.EmployeeRepository;
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
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
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
		Review rev1 = new Review("1","hello");
		Review rev2 = new Review("2","hellol");
		Course c1 = new Course("course 1 for review 1");
		Course c2 = new Course ("course 2 for review 2");
		repository.save(c1);
		repository.save(c2);
		rev1.setCourse(c1);
		rev2.setCourse(c2);
		
		System.out.println("printing reviews for course 2l");
		repository.addReviewsForCourse();
		
		rrepository.save(rev1);
		rrepository.save(rev2);
		
		srepository.saveStudentWithPassport();
		
		Review a = new Review("67","two thumbs up");
		Review b = new Review("34","excellent");
		List<Review> reviewz = new ArrayList<Review>();
		reviewz.add(a);
		reviewz.add(b);
		Course c100 = new Course("rangahetimamangoya");
		repository.save(c100);
		repository.addReviewsForCourse(c100.getId(),reviewz);
		
		// repository.save(new Course("jpa"));
		// repository.save(new Course("rrr"));
		// Course course = repository.findById(2L);
		// logger.info("10001 course -> {}", course);

		// repository.save(new Course("Microservices kin 100 stes"));

		srepository.insertStudentAndCourse();
		srepository.insertStudentAndCourse(new Student("ahmad"),new Course("ole"));
	
	    //now retrive back the data above
		
		employeeRepository.insert(new PartTimeEmployee("jill",new BigDecimal("60")));
		employeeRepository.insert(new FullTimeEmployee("jack",new BigDecimal("10000")));
		
	
	    //	logger.info("employees -> {}",employeeRepository.retrieveAllEmployees());
	
		logger.info("employees part time -> {}", employeeRepository.retrieveAllPartTimeEmployees());
		logger.info("employees full time -> {}", employeeRepository.retrieveAllFullTimeEmployees());
	}

}
