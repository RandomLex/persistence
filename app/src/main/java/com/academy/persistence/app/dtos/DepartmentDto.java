package com.academy.persistence.app.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentDto {
    private Integer id;
    private String name;
    private String city;
}
