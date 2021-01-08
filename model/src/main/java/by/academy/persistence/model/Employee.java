package by.academy.persistence.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
public class Employee extends AbstractEntity {
    private String name;
    private List<Department> department = new ArrayList<>();
    private int salary;
    private Title title;
}
