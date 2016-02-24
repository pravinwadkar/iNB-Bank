package com.src.banking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.src.banking.dao.BranchManagerDAO;
import com.src.banking.entity.BranchManager;
import com.src.banking.service.BranchManagerSerice;

@Service("branchManagerSerice")
public class BranchManagerServiceImpl implements BranchManagerSerice{
	
	@Autowired
	private BranchManagerDAO branchManagerDAO;
	
	
	@org.springframework.transaction.annotation.Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void createBranchManager(BranchManager branchmanager) {
		branchManagerDAO.createBranchManager(branchmanager);
	}

}
