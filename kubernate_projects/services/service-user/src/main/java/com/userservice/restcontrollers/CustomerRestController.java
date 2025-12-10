package com.userservice.restcontrollers;

import com.dtos.customerDto.CustomerRegisterDto;
import com.userservice.entities.Customer;
import com.userservice.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;

    @PostMapping("register")
    public CustomerRegisterDto register(@RequestBody @Valid CustomerRegisterDto customerRegisterDto ){
        return customerRegisterDto;
    }

}
