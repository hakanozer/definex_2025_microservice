package com.dtos.customerDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Set;

public record CustomerRegisterDto(@NotNull @Size(min = 2, max = 100) @NotEmpty String name,
                                  @NotNull @Email @NotEmpty String email,
                                  @NotNull @Size(min = 4, max = 10) @NotEmpty String password,
                                  @NotNull @Size(min = 1, max = 3) Set<RoleDto> roles) implements Serializable {
}