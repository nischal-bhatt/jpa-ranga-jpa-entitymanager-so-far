package com.in28minutes.jpa.hibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa.hibernate.DemoApplication;
import com.in28minutes.jpa.hibernate.entity.Course;
import com.in28minutes.jpa.hibernate.entity.Student;

@SpringBootTest(classes=DemoApplication.class)
class JPQLTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	public void findById_basic() {
		
		List resultList=em.createQuery("Select c from Course c").getResultList();
	    logger.info("Select c from Course c -> {}",resultList);
	}
	
	@Test
	public void findById_typed() {
		
		TypedQuery<Course> query=em.createQuery("Select c from Course c",Course.class);
	    List<Course> resultList = query.getResultList();
		logger.info("Select c from Course c -> {}",resultList);
	}
	
	
	@Test
	public void jpql_where() {
		
		TypedQuery<Course> query1 = em.createNamedQuery("get_all_courses",Course.class);
		List<Course> resultList1 = query1.getResultList();
		logger.info("we're here now {}",resultList1);
		TypedQuery<Course> query=em.createQuery("Select c from Course c where name like '%t%'",Course.class);
	    List<Course> resultList = query.getResultList();
		logger.info("Select c from Course c where clause-> {}",resultList);
	}
	
	
	@Test
	public void jpql_courses_without_students() {
		
		TypedQuery<Course> query = em.createQuery("Select c from Course c where c.students is empty", Course.class);
	    List<Course> resultList  = query.getResultList();
	    
	    logger.info("results -> {}", resultList);
	}
	
	@Test
	public void jpql_courses_with_at_least_2_students() {
		
		TypedQuery<Course> query = em.createQuery("Select c from Course c where size(c.students) > 0", Course.class);
	    List<Course> resultList  = query.getResultList();
	    
	    logger.info("results at least 2 -> {}", resultList);
	}
	
	@Test
	public void jpql_courses_ordered_by_students() {
		
		TypedQuery<Course> query = em.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
	    List<Course> resultList  = query.getResultList();
	    
	    logger.info("results at least 2 -> {}", resultList);
	}
	
	@Test
	public void jpql_students_with_passports_in_a_certain_pattern() {
		
		TypedQuery<Student> query = em.createQuery("Select s from Student s where s.passport.number like '%Z%'", Student.class);
	    List<Student> resultList  = query.getResultList();
	    
	    logger.info("students with passport nums in a certain pattern -> {}", resultList);
	}
	@Test
	public void join()
	{
		Query query = em.createQuery("Select c,s from Course c JOIN c.students s");
		List<Object []> resultList = query.getResultList();
		logger.info("result sizey -> {}",resultList.size());
		
		for (Object[] result:resultList)
		{
			logger.info("course{} student{}",result[0],result[1]);
			
		}
	}
	
	@Test
	public void lef_join()
	{
		Query query = em.createQuery("Select c,s from Course c LEFT JOIN c.students s");
		List<Object []> resultList = query.getResultList();
		logger.info("result sizey -> {}",resultList.size());
		
		for (Object[] result:resultList)
		{
			logger.info("course{} student{}",result[0],result[1]);
			
		}
	}
	
	@Test
	public void cross_join()
	{
		Query query = em.createQuery("Select c,s from Course c ,Student s");
		List<Object []> resultList = query.getResultList();
		logger.info("result sizey -> {}",resultList.size());
		
		for (Object[] result:resultList)
		{
			logger.info("course{} student{}",result[0],result[1]);
			
		}
	}
	
	

}
