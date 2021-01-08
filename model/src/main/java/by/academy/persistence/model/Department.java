package by.academy.persistence.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Department extends AbstractEntity {
    private String name;
    private City city;
    private List<Employee> employees = new ArrayList<>();
}
