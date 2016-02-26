/**
 * 
 */
package com.inb.banking.service;

import java.util.List;

import com.inb.banking.entity.BranchManager;
import com.inb.banking.rest.entity.WSBranchManager;


public interface BranchManagerSerice {
	
	public WSBranchManager createBranchManager(WSBranchManager branchmanager);

	public WSBranchManager branchManagerLogin(WSBranchManager branchManager);

	public List<WSBranchManager> getAllBranchManagerDeatils();
	

}
