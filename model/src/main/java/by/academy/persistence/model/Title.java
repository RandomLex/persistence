package by.academy.persistence.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Title extends AbstractEntity {
    private String name;

    public Title(int id, String name) {
        super(id);
        this.name = name;
    }
}
