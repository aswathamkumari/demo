package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CustomerDTO;

@Service
public interface CustomerService {
	CustomerDTO createCustomer(CustomerDTO customerDTO);

	CustomerDTO getCustomerById(Long id);

	List<CustomerDTO> getAllCustomers();

	CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

	void deleteCustomer(Long id);
}
