package com.inb.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inb.banking.entity.BranchManager;
import com.inb.banking.rest.entity.WSBranchManager;
import com.inb.banking.service.BranchManagerSerice;


/**
 * Branch Manager Controller
 * @author SudarshanReddy
 *
 */

@RestController
@CrossOrigin
public class BranchManagerController {
	
	@Autowired
	private BranchManagerSerice branchManagerSerice;
	
	@RequestMapping(value="/branchmanager", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public WSBranchManager createBranchManager(@RequestBody BranchManager branchmanager){
		WSBranchManager wsBranchManager = branchManagerSerice.createBranchManager(branchmanager);
		return wsBranchManager;
	}
	
	@RequestMapping(value="/branchmanager/login", method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public WSBranchManager branchManagerLogin(@RequestBody BranchManager branchManager){

		return branchManagerSerice.branchManagerLogin(branchManager);
	}
	
	@RequestMapping(value="/branchmanager", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public List<WSBranchManager> getAllBranchManagerDeatils(){
		
		return branchManagerSerice.getAllBranchManagerDeatils();
	}

}
