package com.inb.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.inb.banking.dao.AdminDao;
import com.inb.banking.entity.Admin;
import com.inb.banking.entity.Customer;

/**
 * This url mapping can be used to provide different types of information. one
 * such type is about.
 * 
 * @author sharma_as
 *
 */
@RestController
@RequestMapping(value = "/admin")
@CrossOrigin // (origins = "http://localhost:8098")
public class AdminController {

	@Autowired
	private AdminDao admindao;

	private Gson gson = new Gson();
	/*
	 * @RequestMapping(value = "/login", method =
	 * RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE, produces =
	 * MediaType.APPLICATION_JSON_VALUE) public Information
	 * getAboutData(@RequestBody Information information) { // Information
	 * information = new Information(); information.setName("about the bank");
	 * information.setContents("This is the contents about the bank");
	 * System.out.println("Working admin ***********************************" +
	 * information.getName()); return information; }
	 */

	@RequestMapping(value = "/login", method = RequestMethod.PUT, consumes = "application/json")
	public String adminlogin(@RequestBody Admin admin) {
		String retVal = null;
		Admin adminData = null;
		System.out.println(admin.getUserName());
		adminData = admindao.findAdminByUsernameAndPassword(admin.getUserName(), admin.getPassword());
		if (adminData != null) {
			retVal = gson.toJson(adminData);
		}
		System.out.println(admin.getPassword());
		return retVal;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.OPTIONS, consumes = "application/json")
	public void adminlogout() {
		
	}

}
