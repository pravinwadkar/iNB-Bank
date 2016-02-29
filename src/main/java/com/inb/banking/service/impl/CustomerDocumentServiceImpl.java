package com.inb.banking.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inb.banking.dao.CustomerDocumentDAO;
import com.inb.banking.entity.CustDocument;
import com.inb.banking.rest.entity.WSCustomerDocument;
import com.inb.banking.service.CustomerDocumentService;

@Service("customerDocumentService")
public class CustomerDocumentServiceImpl implements CustomerDocumentService{
	
	@Autowired
	private CustomerDocumentDAO customerDocumentDAO;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true,isolation=Isolation.READ_COMMITTED)
	public byte[] getAddressProofDocumentById(String customerid) {
		CustDocument custDocument = customerDocumentDAO.getAddressProofDocumentById(customerid);
		WSCustomerDocument wsCustomerDocument = null;
		if(custDocument != null){
			wsCustomerDocument = mapper.map(custDocument, WSCustomerDocument.class);
			return wsCustomerDocument.getImageaddress();
		}
		else
			return null;
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true,isolation=Isolation.READ_COMMITTED)
	public byte[] getAgeProofDocumentById(String customerid) {
		CustDocument custDocument = customerDocumentDAO.getAgeProofDocumentById(customerid);
		WSCustomerDocument wsCustomerDocument = null;
		if(custDocument != null){
			wsCustomerDocument = mapper.map(custDocument, WSCustomerDocument.class);
			return wsCustomerDocument.getImageage();
		}
		else
			return null;
	}

}
