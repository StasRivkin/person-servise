package telran.java47.personservise.person.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode()
@Embeddable
public class Address implements Serializable {
    private static final long serialVersionUID = 1305108322409573329L;
    String city;
    String street;
    Integer building;
}
