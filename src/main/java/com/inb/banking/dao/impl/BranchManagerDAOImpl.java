package com.inb.banking.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inb.banking.dao.BranchManagerDAO;
import com.inb.banking.entity.BranchManager;

@Repository("branchManagerDAO")
public class BranchManagerDAOImpl implements BranchManagerDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	public void createBranchManager(BranchManager branchmanager) {
		sessionFactory.getCurrentSession().save(branchmanager);
	}

}
