package com.dtos.customerDto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleDto implements Serializable {

    private Long rid;
    private String name;

}
