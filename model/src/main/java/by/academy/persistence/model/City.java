package by.academy.persistence.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@With
@AllArgsConstructor
public class City extends AbstractEntity {
    private String name;
    private List<Department> departments = new ArrayList<>();

    @Override
    public City withId(Integer id) {
        super.setId(id);
        return this;
    }

    public City withDepartment(Department department) {
        departments.add(department);
        return this;
    }
}
