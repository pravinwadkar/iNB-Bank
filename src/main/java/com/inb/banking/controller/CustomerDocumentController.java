package com.inb.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inb.banking.service.CustomerDocumentService;

/**
 * CustomerDocumentController
 * @author reddy_sd
 *
 */
@RestController
@CrossOrigin
public class CustomerDocumentController {
	
	@Autowired
	private CustomerDocumentService customerDocumentService;
	
	/**
	 * This method used to get the addressproof document
	 * @param customerid
	 * @return
	 */
	@RequestMapping(value="/addressproofdocument/{customerid}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public byte[] getAddressProofDocumentById(@PathVariable("customerid")String customerid){
		return customerDocumentService.getAddressProofDocumentById(customerid);
	}
	
	/**
	 * This method used to get the ageproof document
	 * @param customerid
	 * @return
	 */
	@RequestMapping(value="/ageproofdocument/{customerid}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public byte[] getAgeProofDocumentById(@PathVariable("customerid")String customerid){
		return customerDocumentService.getAgeProofDocumentById(customerid);
	}

	
	
	
	

}
