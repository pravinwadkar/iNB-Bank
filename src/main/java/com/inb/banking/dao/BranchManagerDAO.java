package com.inb.banking.dao;

import java.util.List;

import com.inb.banking.entity.BranchManager;
import com.inb.banking.rest.entity.WSBranchManager;


public interface BranchManagerDAO {
	
	public void createBranchManager(BranchManager branchmanager);

	public BranchManager branchManagerLogin(WSBranchManager branchManager);

	public List<BranchManager> getAllBranchManagerDeatils();

}
