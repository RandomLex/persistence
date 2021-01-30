package by.academy.persistence.model.examples;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Report {
    @EmbeddedId
    private ReportKey id;

    private String recipient;
}
