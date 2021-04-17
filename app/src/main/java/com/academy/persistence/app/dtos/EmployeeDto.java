package com.academy.persistence.app.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmployeeDto {
    private Integer id;
    private String name;
    private Integer salary;
    private List<DepartmentDto> departments;
}
