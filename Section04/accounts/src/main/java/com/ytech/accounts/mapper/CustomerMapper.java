package com.ytech.accounts.mapper;

import com.ytech.accounts.dto.CustomerDto;

import com.ytech.accounts.entity.Customer;
//modelmapper and mapStruct these are two external libraries to convert DTO to Entity and vice versa.
public class CustomerMapper {

	public static CustomerDto mapToCustomerDto(CustomerDto customerDto, Customer customer) {
		customerDto.setName(customer.getName());
		customerDto.setMobileNumber(customer.getMobileNumber());
		customerDto.setEmail(customer.getEmail());                                                                                   ;

		return customerDto;
	}

	public static Customer mapToCustomer(Customer customer, CustomerDto customerDto) {
		customer.setName(customerDto.getName());
		customer.setMobileNumber(customerDto.getMobileNumber());
		customer.setEmail(customerDto.getEmail());

		return customer;
	}

}
