package com.inb.banking.dao;

import java.util.List;

import com.inb.banking.entity.BranchManager;


public interface BranchManagerDAO {
	
	public void createBranchManager(BranchManager branchmanager);

	public BranchManager branchManagerLogin(BranchManager branchManager);

	public List<BranchManager> getAllBranchManagerDeatils();

}
