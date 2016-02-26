/**
 * 
 */
package com.inb.banking.dao;

import java.util.List;

import com.inb.banking.entity.Branch;


/**
 * @author reddy_sd
 *
 */
public interface BranchDAO {
	
	public List<Branch> getAllBranchDetails();

	public void createBranch(Branch branch);

	public Branch getBranchDetailsByName(String branchName);

}
