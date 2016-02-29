package com.inb.banking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inb.banking.dao.AdminDao;
import com.inb.banking.entity.Admin;
import com.inb.banking.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDao admindao;

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true,isolation=Isolation.READ_COMMITTED)
	public Admin autheticateAdmin(Admin admin) throws Exception{
		return admindao.findAdminByUsernameAndPassword(admin.getUserName(), admin.getPassword());
	}
}
