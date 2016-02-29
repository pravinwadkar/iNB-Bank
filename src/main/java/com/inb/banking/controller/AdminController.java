package com.inb.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inb.banking.entity.Admin;
import com.inb.banking.rest.entity.WSAdminLogout;
import com.inb.banking.service.AdminService;

/**
 * This url mapping can be used to provide different types of information. one
 * such type is about.
 * 
 * @author sharma_as
 *
 */
@RestController
@RequestMapping(value = "/admin")
@CrossOrigin//(origins = "http://localhost:8098")
public class AdminController {

	@Autowired
	private AdminService adminservice;

	private String logoutMsg = "{\"logoutMsg\" : \"Successfully Loged Out\"}";
	
	
	@RequestMapping(value = "/login", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Admin adminlogin(@RequestBody Admin admin) throws Exception{
		Admin adminData = null;
		adminData = adminservice.autheticateAdmin(admin);
		return adminData;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String adminlogout(@RequestBody WSAdminLogout adminlogout ) {
		return logoutMsg;
	}
	
	/*@RequestMapping(value = "/logout", method = RequestMethod.OPTIONS, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void adminlogoutOptions() {
		
	}*/

}
