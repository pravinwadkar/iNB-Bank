/**
 * 
 */
package com.inb.banking.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inb.banking.dao.BranchDAO;
import com.inb.banking.entity.Branch;


/**
 * @author reddy_sd
 *
 */
@Repository("branchDAO")
public class BranchDAOImpl implements BranchDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Branch> getAllBranchDetails() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Branch.class);
		List<Branch> branchs = criteria.list();
		return branchs;
	}

	
	public void createBranch(Branch branch) {
		sessionFactory.getCurrentSession().save(branch);
	}


	public Branch getBranchDetailsByName(String branchName) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Branch.class);
		criteria.add(Restrictions.eq("branchName", branchName));
		return (Branch)criteria.uniqueResult();
	}

}
