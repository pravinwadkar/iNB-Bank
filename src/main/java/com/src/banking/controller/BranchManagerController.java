package com.src.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.src.banking.entity.BranchManager;
import com.src.banking.service.BranchManagerSerice;

@RestController
public class BranchManagerController {
	
	@Autowired
	private BranchManagerSerice branchManagerSerice;
	
	@RequestMapping(value="/branchmanager", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public String createBranchManager(@RequestBody BranchManager branchmanager){
		
		branchManagerSerice.createBranchManager(branchmanager);
		
		return branchmanager.getId();
	}

}
