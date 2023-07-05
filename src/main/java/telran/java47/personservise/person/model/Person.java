package telran.java47.personservise.person.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person implements Serializable {
    private static final long serialVersionUID = 717580607558946945L;
    @Id
    Integer id;
    @Setter
    String name;
    LocalDate birthDate;
    @Setter
    // @Embedded
    Address address;
    @JsonProperty("type")
    public String getType() {
        return this.getClass().getSimpleName().toLowerCase();
    }
}
