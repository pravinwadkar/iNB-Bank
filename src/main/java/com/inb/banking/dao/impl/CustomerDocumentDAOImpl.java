package com.inb.banking.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inb.banking.dao.CustomerDocumentDAO;
import com.inb.banking.entity.CustDocument;

@Repository("customerDocumentDAO")
public class CustomerDocumentDAOImpl implements CustomerDocumentDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public CustDocument getAddressProofDocumentById(String customerid) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CustDocument.class);
		criteria.createAlias("customer", "customer",JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("customer.id", customerid));
		return (CustDocument) criteria.uniqueResult();
	}

	public CustDocument getAgeProofDocumentById(String customerid) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CustDocument.class);
		criteria.createAlias("customer", "customer",JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("customer.id", customerid));
		return (CustDocument) criteria.uniqueResult();
	}

}
