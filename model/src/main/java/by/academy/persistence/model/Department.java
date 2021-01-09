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
public class Department extends AbstractEntity {
    private String name;
    @JsonManagedReference
    private City city;
    @JsonBackReference
    private List<Employee> employees = new ArrayList<>();

    public Department withId(Integer id) {
        setId(id);
        return this;
    }

    public Department withName(String name) {
        setName(name);
        return this;
    }

    public Department withCity(City city) {
        setCity(city);
        return this;
    }

    public Department withEmployee(Employee employee) {
        employees.add(employee);
        return this;
    }
}
