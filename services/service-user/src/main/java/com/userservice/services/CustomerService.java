package com.userservice.services;

import com.dtos.customerDto.CustomerRegisterDto;
import com.userservice.entities.Customer;
import com.userservice.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public Customer register(CustomerRegisterDto customerRegisterDto) {
        Optional<Customer> customerOptional = customerRepository.findByEmailEqualsIgnoreCase(customerRegisterDto.email());
        if(customerOptional.isPresent()) {
            return null;
        }
        Customer customer = modelMapper.map(customerRegisterDto, Customer.class);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        Customer customerDB = customerRepository.save(customer);
        return customerDB;
    }


}
