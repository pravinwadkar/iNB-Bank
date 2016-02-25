package com.inb.banking.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.inb.banking.dao.BranchManagerDAO;
import com.inb.banking.entity.BranchManager;
import com.inb.banking.rest.entity.WSBranchManager;
import com.inb.banking.service.BranchManagerSerice;
import com.inbbank.util.GenerateUUID;


@Service("branchManagerSerice")
public class BranchManagerServiceImpl implements BranchManagerSerice{
	
	@Autowired
	private BranchManagerDAO branchManagerDAO;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	
	@org.springframework.transaction.annotation.Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public WSBranchManager createBranchManager(BranchManager branchmanager) {
		branchmanager.setId(GenerateUUID.getRendomString());
		branchManagerDAO.createBranchManager(branchmanager);
		WSBranchManager wsBranchManager = mapper.map(branchmanager, WSBranchManager.class);
		return wsBranchManager;
	}

	@org.springframework.transaction.annotation.Transactional(propagation=Propagation.NOT_SUPPORTED,
	readOnly=true)
	public WSBranchManager branchManagerLogin(BranchManager branchManager) {
		BranchManager branchManager2 = branchManagerDAO.branchManagerLogin(branchManager);
		WSBranchManager wsBranchManager = mapper.map(branchManager2, WSBranchManager.class);
		return wsBranchManager;
	}


	
	@org.springframework.transaction.annotation.Transactional(propagation=Propagation.NOT_SUPPORTED,
	readOnly=true)
	public List<WSBranchManager> getAllBranchManagerDeatils() {
		List<BranchManager> branchManagers = branchManagerDAO.getAllBranchManagerDeatils();
		List<WSBranchManager> wsBranchManagers = new ArrayList<WSBranchManager>(branchManagers.size());
		for(BranchManager branchManager : branchManagers)
			wsBranchManagers.add(mapper.map(branchManager, WSBranchManager.class));
		return wsBranchManagers;
	}

}
