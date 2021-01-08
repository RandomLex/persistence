package by.academy.persistence.model;

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
    private List<Department> departments = new ArrayList<>();
}
