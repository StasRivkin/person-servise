package telran.java47.personservise.person.model;

import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Entity
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
}
