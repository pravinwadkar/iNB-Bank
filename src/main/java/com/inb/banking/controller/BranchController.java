package com.inb.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inb.banking.entity.Branch;
import com.inb.banking.rest.entity.WSBranch;
import com.inb.banking.service.BranchService;


/**
 * Branch Manager Controller
 * @author SudarshanReddy
 *
 */
@RestController
@CrossOrigin
public class BranchController {
	
	@Autowired
	private BranchService branchService;
	
	/**
	 * It will give Entire Branch Details
	 * @return WSBranch Deatils
	 */
	@RequestMapping(value="/branch", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public List<WSBranch> getAllBranchDetails(){
		
		return branchService.getAllBranchDetails();
	}
	
	/**
	 * It will save the branch details
	 * @param branch
	 * @return
	 */
	@RequestMapping(value="/branch", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public WSBranch createBranch(@RequestBody Branch branch){
		WSBranch wsBranch = branchService.createBranch(branch);
		return wsBranch;
	}

}
