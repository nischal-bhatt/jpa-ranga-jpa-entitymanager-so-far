package com.in28minutes.jpa.hibernate.repository;

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
public class ReviewRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public Review findById(Long id) {
		return em.find(Review.class, id);
	}

	public Review save(Review review) {
		if (review.getId() == null) {
			em.persist(review);
		} else {
			em.merge(review);
		}

		return review;

	}

	
}
