package by.academy.persistence.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private List<Department> departments = new ArrayList<>();
    private int salary;
    private Title title;

    public Employee withId(Integer id) {
        setId(id);
        return this;
    }

    public Employee withName(String name) {
        setName(name);
        return this;
    }

    public Employee withDepartment(Department department) {
        departments.add(department);
        return this;
    }

    public Employee withSalary(int salary) {
        setSalary(salary);
        return this;
    }

    public Employee withTitle(Title title) {
        setTitle(title);
        return this;
    }
}
