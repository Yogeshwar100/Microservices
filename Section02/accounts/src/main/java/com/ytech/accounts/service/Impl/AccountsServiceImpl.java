package com.ytech.accounts.service.Impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.ytech.accounts.constants.AccountsConstants;
import com.ytech.accounts.dto.AccountsDto;
import com.ytech.accounts.dto.CustomerDto;
import com.ytech.accounts.entity.Accounts;
import com.ytech.accounts.entity.Customer;
import com.ytech.accounts.exception.CustomerAlreadyExistsException;
import com.ytech.accounts.exception.ResourceNotFoundException;
import com.ytech.accounts.mapper.AccountsMapper;
import com.ytech.accounts.mapper.CustomerMapper;
import com.ytech.accounts.repository.AccountRepository;
import com.ytech.accounts.repository.CustomerRepository;
import com.ytech.accounts.service.IAccountsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

	private AccountRepository accountsRepository;

	private CustomerRepository customerRepository;

	@Override
	public void createAccount(CustomerDto customerDto) {
		Customer customer = CustomerMapper.mapToCustomer(new Customer(), customerDto);
		Optional<Customer> optionalCustomer=customerRepository.findByMobileNumber(customerDto.getMobileNumber());
		if(optionalCustomer.isPresent()) {
			throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "+customerDto.getMobileNumber());
		}
		Customer savedCustomer=customerRepository.save(customer);
		accountsRepository.save(createNewAccount(savedCustomer));
	}

	private Accounts createNewAccount(Customer customer) {
		Accounts newAccount = new Accounts();
		newAccount.setCustomerId(customer.getCustomerId());
		long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
		newAccount.setAccountNumber(randomAccNumber);
		newAccount.setAccountType(AccountsConstants.SAVINGS);
		newAccount.setBranchAddress(AccountsConstants.ADDRESS);
		return newAccount;

	}

	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
		Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
				()->new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		Accounts accounts=accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				()->new ResourceNotFoundException("Account", "mobileNumber", customer.getCustomerId().toString()));
		CustomerDto customerDto=CustomerMapper.mapToCustomerDto(new CustomerDto(), customer);
		customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(new AccountsDto(), accounts));
		return customerDto;
	}

	@Override
	public boolean deleteAccount(String mobileNumber) {
		Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow
				(()->new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		accountsRepository.deleteByCustomerId(customer.getCustomerId());
		customerRepository.deleteById(customer.getCustomerId());
		return true;
	}
	
	 @Override
	    public boolean updateAccount(CustomerDto customerDto) {
	        boolean isUpdated = false;
	        AccountsDto accountsDto = customerDto.getAccountsDto();
	        if(accountsDto !=null ){
	            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
	                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
	            );
	            AccountsMapper.mapToAccounts(accounts, accountsDto);
	            accounts = accountsRepository.save(accounts);

	            Long customerId = accounts.getCustomerId();
	            Customer customer = customerRepository.findById(customerId).orElseThrow(
	                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
	            );
	            CustomerMapper.mapToCustomer(customer,customerDto);
	            customerRepository.save(customer);
	            isUpdated = true;
	        }
	        return  isUpdated;
	    }
}
