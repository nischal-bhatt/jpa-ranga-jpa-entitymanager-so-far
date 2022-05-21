package com.in28minutes.jpa.hibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.in28minutes.jpa.hibernate.DemoApplication;
import com.in28minutes.jpa.hibernate.entity.Course;

@SpringBootTest(classes=DemoApplication.class)
class NativeQueriesTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	public void native_queires_basic() {
		Query query = em.createNativeQuery("select * from course_details where id = ?",Course.class);
		query.setParameter(1, 2L);
		List resultList=query.getResultList();
	    logger.info("Select * from Course c where id = lol-> {}",resultList);
	}
	
	@Test
	@Transactional
	@DirtiesContext
	public void native_queires_toupdate() {
		//for mass updates use sql
		Query query = em.createNativeQuery("update course_details set fulln_ame = 'sudta' where id = ? or id = ?",Course.class);
		query.setParameter(1, 2L);
		query.setParameter(2, 3L);
		int rows=query.executeUpdate();
	    logger.info("Select * from Course c where id = lol-> {}",rows);
	}

	

}
