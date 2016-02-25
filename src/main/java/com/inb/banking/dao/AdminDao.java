package com.inb.banking.dao;


import com.inb.banking.entity.Admin;


public interface AdminDao {

	
 Admin findAdminByUsernameAndPassword(String username,String password);
		
}
