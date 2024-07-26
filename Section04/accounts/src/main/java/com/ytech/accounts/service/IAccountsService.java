package com.ytech.accounts.service;

import com.ytech.accounts.dto.CustomerDto;

import jakarta.validation.Valid;

public interface IAccountsService {

	/**
	 * 
	 * @param customerDto
	 */
	void createAccount(CustomerDto customerDto);
	/**
	 * 
	 * @param mobileNumber
	 * @return
	 */
	CustomerDto fetchAccount(String mobileNumber);
	
	/**
	 * 
	 * @param mobileNumber
	 * @return
	 */
	public boolean deleteAccount(String mobileNumber);
	
	boolean updateAccount(CustomerDto customerDto);
}
