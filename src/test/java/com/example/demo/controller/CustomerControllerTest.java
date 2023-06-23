package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

	@Mock
	private CustomerService customerService;

	@InjectMocks
	private CustomerController customerController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void createCustomer_PositiveScenario() {
		// Arrange
		CustomerDTO customerDTO = new CustomerDTO("John Doe", "john@example.com");
		Customer createdCustomer = new Customer(1L, "John Doe", "john@example.com");
		when(customerService.createCustomer(customerDTO)).thenReturn(customerDTO);

		// Act
		ResponseEntity<CustomerDTO> response = customerController.createCustomer(customerDTO);

		// Assert
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(createdCustomer, response.getBody());
		verify(customerService, times(1)).createCustomer(customerDTO);
	}

	// Add more test cases as needed
}
