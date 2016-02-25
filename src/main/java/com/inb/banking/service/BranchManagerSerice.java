/**
 * 
 */
package com.inb.banking.service;

import java.util.List;

import com.inb.banking.entity.BranchManager;
import com.inb.banking.rest.entity.WSBranchManager;


public interface BranchManagerSerice {
	
	public WSBranchManager createBranchManager(BranchManager branchmanager);

	public WSBranchManager branchManagerLogin(BranchManager branchManager);

	public List<WSBranchManager> getAllBranchManagerDeatils();
	

}
