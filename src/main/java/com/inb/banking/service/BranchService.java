package com.inb.banking.service;

import java.util.List;

import com.inb.banking.entity.Branch;
import com.inb.banking.rest.entity.WSBranch;


/**
 * 
 * @author reddy_sd
 *
 */
public interface BranchService {
	
	List<WSBranch> getAllBranchDetails();

	WSBranch createBranch(Branch branch);

}
