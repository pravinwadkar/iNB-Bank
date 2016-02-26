package com.inb.banking.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.inb.banking.dao.BranchDAO;
import com.inb.banking.dao.BranchManagerDAO;
import com.inb.banking.entity.Branch;
import com.inb.banking.entity.BranchManager;
import com.inb.banking.rest.entity.WSBranchManager;
import com.inb.banking.service.BranchManagerSerice;
import com.inbbank.util.GenerateUUID;


@Service("branchManagerSerice")
public class BranchManagerServiceImpl implements BranchManagerSerice{
	
	@Autowired
	private BranchManagerDAO branchManagerDAO;
	
	@Autowired
	private BranchDAO branchDAO;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	
	@org.springframework.transaction.annotation.Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public WSBranchManager createBranchManager(WSBranchManager wsbranchmanager) {
		wsbranchmanager.setBranch(wsbranchmanager.getBranchPOJO());
		wsbranchmanager.setId(GenerateUUID.getRendomString());
		BranchManager branchmanager = mapper.map(wsbranchmanager, BranchManager.class);
		Branch branch = branchDAO.getBranchDetailsByName(branchmanager.getBranch().getBranchName());
		branchmanager.setBranch(branch);
		branchManagerDAO.createBranchManager(branchmanager);
		return wsbranchmanager;
	}

	@org.springframework.transaction.annotation.Transactional(propagation=Propagation.NOT_SUPPORTED,
	readOnly=true)
	public WSBranchManager branchManagerLogin(WSBranchManager branchManager) {
		BranchManager branchManager2 = branchManagerDAO.branchManagerLogin(branchManager);
		WSBranchManager wsBranchManager = null ;
		if(branchManager2 != null){
			wsBranchManager = mapper.map(branchManager2, WSBranchManager.class);
		}
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
