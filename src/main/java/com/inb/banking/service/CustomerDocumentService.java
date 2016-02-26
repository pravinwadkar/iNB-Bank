package com.inb.banking.service;

public interface CustomerDocumentService {

	byte[] getAddressProofDocumentById(String customerid);

    byte[] getAgeProofDocumentById(String customerid);

}
