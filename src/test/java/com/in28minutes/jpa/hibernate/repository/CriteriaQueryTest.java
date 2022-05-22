package com.in28minutes.jpa.hibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa.hibernate.DemoApplication;
import com.in28minutes.jpa.hibernate.entity.Course;

@SpringBootTest(classes=DemoApplication.class)
class CriteriaQueryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	public void findById_basic() {
		
		CriteriaBuilder criteriaBuilder=em.getCriteriaBuilder();
		CriteriaQuery<Course> cq=criteriaBuilder.createQuery(Course.class);
		Root<Course> courseRoot =cq.from(Course.class);
		TypedQuery<Course> query = 
				em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("typed  -> {}",resultList);
	}
	
	@Test
	public void all_courses_having_Z() {
		
		CriteriaBuilder criteriaBuilder=em.getCriteriaBuilder();
		CriteriaQuery<Course> cq=criteriaBuilder.createQuery(Course.class);
		Root<Course> courseRoot =cq.from(Course.class);
		Predicate like = criteriaBuilder.like(courseRoot.get("name"), "%ang%");
		cq.where(like);
		TypedQuery<Course> query = 
				em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("typed  -> {}",resultList);
	}

	@Test
	public void all_courses_without_students() {
		
		CriteriaBuilder criteriaBuilder=em.getCriteriaBuilder();
		CriteriaQuery<Course> cq=criteriaBuilder.createQuery(Course.class);
		Root<Course> courseRoot =cq.from(Course.class);
		Predicate like = criteriaBuilder.isEmpty(courseRoot.get("students"));
		cq.where(like);
		TypedQuery<Course> query = 
				em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("typed  -> {}",resultList);
	}
	
	
	
	@Test
	public void join() {
		
		CriteriaBuilder criteriaBuilder=em.getCriteriaBuilder();
		CriteriaQuery<Course> cq=criteriaBuilder.createQuery(Course.class);
		Root<Course> courseRoot =cq.from(Course.class);
		//Predicate like = criteriaBuilder.isEmpty(courseRoot.get("students"));
		Join<Object,Object> join=courseRoot.join("students");
		//cq.where(like);
		TypedQuery<Course> query = 
				em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("typed  -> {}",resultList);
	}
	
	@Test
	public void leftjoin() {
		
		CriteriaBuilder criteriaBuilder=em.getCriteriaBuilder();
		CriteriaQuery<Course> cq=criteriaBuilder.createQuery(Course.class);
		Root<Course> courseRoot =cq.from(Course.class);
		//Predicate like = criteriaBuilder.isEmpty(courseRoot.get("students"));
		Join<Object,Object> join=courseRoot.join("students",JoinType.LEFT);
		//cq.where(like);
		TypedQuery<Course> query = 
				em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("typed  -> {}",resultList);
	}
	
}
