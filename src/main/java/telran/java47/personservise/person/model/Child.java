package telran.java47.personservise.person.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "child")
public class Child extends Person{
    private static final long serialVersionUID = 631556600949014496L;
    String kindergarten;

    public Child(Integer id, String name, LocalDate birthDate, Address address, String kindergarden) {
        super(id, name, birthDate, address);
        this.kindergarten = kindergarden;
    }

    public Child(String kindergarden) {
        this.kindergarten = kindergarden;
    }

}
