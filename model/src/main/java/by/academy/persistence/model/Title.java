package by.academy.persistence.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Title extends AbstractEntity {
    private String name;

    public Title withId(Integer id) {
        setId(id);
        return this;
    }

    public Title withName(String name) {
        setName(name);
        return this;
    }
}
