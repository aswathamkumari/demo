package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customer;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	private final CustomerRepository customerRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
		this.customerRepository = customerRepository;
		this.modelMapper = modelMapper;
	}

	public CustomerDTO createCustomer(CustomerDTO customerDTO) {
		Customer customer = modelMapper.map(customerDTO, Customer.class);
		Customer savedCustomer = customerRepository.save(customer);
		return modelMapper.map(savedCustomer, CustomerDTO.class);
	}

	public CustomerDTO convertToDto(Customer customer) {
		// Create a new CustomerDTO object and map the properties from the Customer
		// entity
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(customer.getId());
		customerDTO.setName(customer.getName());
		customerDTO.setEmail(customer.getEmail());
		// Set other properties as needed

		return customerDTO;
	}

	public CustomerDTO getCustomerById(Long id) {
		final Long customerId = id; // Create a final variable to hold the id value

		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException(customerId));

		return convertToDto(customer);
	}

	public List<CustomerDTO> getAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		return customers.stream().map(customer -> modelMapper.map(customer, CustomerDTO.class))
				.collect(Collectors.toList());
	}

	public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		Customer existingCustomer = optionalCustomer.orElseThrow(() -> new CustomerNotFoundException(id));
		existingCustomer.setName(customerDTO.getName());
		existingCustomer.setEmail(customerDTO.getEmail());
		Customer updatedCustomer = customerRepository.save(existingCustomer);
		return modelMapper.map(updatedCustomer, CustomerDTO.class);
	}

	public void deleteCustomer(Long id) {
		if (!customerRepository.existsById(id)) {
			throw new CustomerNotFoundException(id);
		}
		customerRepository.deleteById(id);
	}
}
