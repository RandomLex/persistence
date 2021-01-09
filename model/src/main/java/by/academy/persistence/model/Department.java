package by.academy.persistence.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@With
@AllArgsConstructor
public class Department extends AbstractEntity {
    private String name;
    private City city;
    private List<Employee> employees = new ArrayList<>();

    @Override
    public Department withId(Integer id) {
        super.setId(id);
        return this;
    }
}
