package by.academy.persistence.model;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@With
@AllArgsConstructor
public class Title extends AbstractEntity {
    private String name;


    @Override
    public Title withId(Integer id) {
        super.setId(id);
        return this;
    }
}
