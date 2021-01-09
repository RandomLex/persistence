package by.academy.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class City extends AbstractEntity {
    private String name;
    @JsonBackReference
    private List<Department> departments = new ArrayList<>();
    @JsonManagedReference
    public City withId(Integer id) {
        setId(id);
        return this;
    }

    public City withDepartment(Department department) {
        departments.add(department);
        return this;
    }

    public City withName(String name) {
        setName(name);
        return this;
    }
}
