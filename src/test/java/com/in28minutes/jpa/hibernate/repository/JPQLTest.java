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
	
	

}
