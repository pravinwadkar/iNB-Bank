package com.inb.banking.dao;

import com.inb.banking.entity.CustDocument;

public interface CustomerDocumentDAO {

	CustDocument getAddressProofDocumentById(String customerid);

	CustDocument getAgeProofDocumentById(String customerid);

}
