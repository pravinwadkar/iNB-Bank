package com.src.banking.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.src.banking.entity.Information;

/**
 * This url mapping can be used to provide different types of information. one
 * such type is about.
 * 
 * @author sharma_as
 *
 */
@RestController
@RequestMapping(value="/admin")
public class AdminController {

	@RequestMapping(value = "/login", method = RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Information getAboutData(@RequestBody Information information) {
//		Information information = new Information();
		information.setName("about the bank");
		information.setContents("This is the contents about the bank");
		System.out.println("Working admin ***********************************" + information.getName());
		return information;
	}

}
