package com.in28minutes.jpa.hibernate.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.entity.Passport;
import com.in28minutes.jpa.hibernate.entity.Student;

@Repository
@Transactional
public class PassportRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public Passport findById(Long id) {
		return em.find(Passport.class, id);
	}

	public Passport save(Passport passport) {
		if (passport.getId() == null) {
			em.persist(passport);
		} else {
			em.merge(passport);
		}

		return passport;

	}

	

	

}
