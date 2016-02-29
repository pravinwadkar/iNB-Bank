/**
 * 
 */
package com.inb.banking.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inb.banking.dao.BranchDAO;
import com.inb.banking.entity.Branch;
import com.inb.banking.rest.entity.WSBranch;
import com.inb.banking.service.BranchService;
import com.inbbank.util.GenerateUUID;


/**
 * @author reddy_sd
 *
 */
@Service("branchService")
public class BranchServiceImpl implements BranchService{
	
	@Autowired
	private BranchDAO branchDAO;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true,isolation=Isolation.READ_COMMITTED)
	public List<WSBranch> getAllBranchDetails() {
		List<Branch> branchs = branchDAO.getAllBranchDetails();
		List<WSBranch> wsBranchs = new ArrayList<WSBranch>(branchs.size());
		for(Branch branch : branchs)
			wsBranchs.add(mapper.map(branch, WSBranch.class));
		return wsBranchs;
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,isolation=Isolation.READ_COMMITTED)
	public WSBranch createBranch(Branch branch) {
		branch.setId(GenerateUUID.getRendomString());
		branchDAO.createBranch(branch);
		WSBranch branch2  = mapper.map(branch, WSBranch.class);
		return branch2;
	}

}
