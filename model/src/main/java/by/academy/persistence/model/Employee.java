package by.academy.persistence.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@With
@AllArgsConstructor
public class Employee extends AbstractEntity {
    private String name;
    private List<Department> department = new ArrayList<>();
    private int salary;
    private Title title;

    @Override
    public Employee withId(Integer id) {
        super.setId(id);
        return this;
    }
}
