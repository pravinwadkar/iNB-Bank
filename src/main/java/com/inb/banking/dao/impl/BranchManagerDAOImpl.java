package com.inb.banking.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inb.banking.dao.BranchManagerDAO;
import com.inb.banking.entity.BranchManager;
import com.inb.banking.rest.entity.WSBranchManager;


@Repository("branchManagerDAO")
public class BranchManagerDAOImpl implements BranchManagerDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public BranchManager branchManagerLogin(WSBranchManager branchManager) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BranchManager.class);
		criteria.createAlias("branch", "branch");
		criteria.add(Restrictions.eq("userName", branchManager.getUserName() ));
		criteria.add(Restrictions.eq("password", branchManager.getPassword()));
		criteria.add(Restrictions.eq("branch.branchName", branchManager.getBranchName()));
		return (BranchManager) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<BranchManager> getAllBranchManagerDeatils() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BranchManager.class);
		criteria.createAlias("branch", "branch",JoinType.LEFT_OUTER_JOIN);
		return criteria.list();
	}
	

	public void createBranchManager(BranchManager branchmanager) {
		sessionFactory.getCurrentSession().save(branchmanager);
	}

}
